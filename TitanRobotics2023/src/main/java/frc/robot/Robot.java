// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.data.AutoOptions;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.actors.Claw;
import frc.robot.actors.Arm;
import frc.robot.actors.DriveControl;
import frc.robot.data.Dashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.AutonomousControl.AutonomousControl;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.data.AprilTag;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public String m_autoSelected;
  
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final SendableChooser<String> delayChooser = new SendableChooser<>();
  private final SendableChooser<String> dropPieceChooser = new SendableChooser<>();
  Thread m_visionThread;

  private Arm arm;
  private Claw claw;
  private DriveControl driveControl;
  private Dashboard dashboard;
  private TeleopControl teleopControl;
  private AutonomousControl autonomousControl;
  private AutoOptions autoOptions;
  private AprilTag aprilTag;
  AHRS ahrs;
  double delayTime; //time before auto code starts
  boolean dropPieceChosenOption; 
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    ahrs = new AHRS(SPI.Port.kMXP);   
    autoOptions = new AutoOptions();
   // m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
   // m_chooser.addOption("My Auto", kCustomAuto);
    delayChooser.addOption(this.autoOptions.waitTime0second, this.autoOptions.waitTime0second);
    delayChooser.addOption(this.autoOptions.waitTime1second, this.autoOptions.waitTime1second);
    delayChooser.addOption(this.autoOptions.waitTime2second, this.autoOptions.waitTime2second);
    delayChooser.addOption(this.autoOptions.waitTime3second, this.autoOptions.waitTime3second);
    delayChooser.addOption(this.autoOptions.waitTime4second, this.autoOptions.waitTime4second);
    delayChooser.addOption(this.autoOptions.waitTime5second, this.autoOptions.waitTime5second);
    delayChooser.addOption(this.autoOptions.waitTime6second, this.autoOptions.waitTime6second);
    delayChooser.addOption(this.autoOptions.waitTime7second, this.autoOptions.waitTime7second);
    
    m_chooser.addOption(this.autoOptions.LeaveCommunityOption, this.autoOptions.LeaveCommunityOption);
    m_chooser.addOption(this.autoOptions.ninetydegreeTurn, this.autoOptions.ninetydegreeTurn);
    m_chooser.addOption(this.autoOptions.DoNothingOption, this.autoOptions.DoNothingOption);
    m_chooser.addOption(this.autoOptions.MobilityOption, this.autoOptions.MobilityOption);
    m_chooser.addOption(this.autoOptions. MobilityOverRampOption, this.autoOptions. MobilityOverRampOption);
   
    dropPieceChooser.addOption(this.autoOptions.dropPieceOn, this.autoOptions.dropPieceOn);
    dropPieceChooser.addOption(this.autoOptions.dropPieceOff, this.autoOptions.dropPieceOff);

    //m_chooser.addOption("plz work2", kCustomAuto2);
    SmartDashboard.putData("Auto choices", m_chooser);
    SmartDashboard.putData("Delay Before Autonomous", delayChooser);
    SmartDashboard.putData("Drop Piece During Auto", dropPieceChooser);

    driveControl = new DriveControl(ahrs);
    claw = new Claw();
    arm = new Arm();
    teleopControl = new TeleopControl(driveControl, claw, arm, ahrs); //manipulator);
    dashboard = new Dashboard();//manipulator);
    autonomousControl = new AutonomousControl(driveControl, arm, claw, ahrs);
    aprilTag = new AprilTag();


    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1); //0=default; 1=off; 2=blinking; 3 = on
    //limelight:12v/2a, switch:5v/500ma

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    
    m_visionThread =
        new Thread(
            () -> {
              // Get the UsbCamera from CameraServer
              UsbCamera camera = CameraServer.startAutomaticCapture();
              // Set the resolution
              camera.setResolution(320, 240);

              // Get a CvSink. This will capture Mats from the camera
              CvSink cvSink = CameraServer.getVideo();
              // Setup a CvSource. This will send images back to the Dashboard
              CvSource outputStream = CameraServer.putVideo("Rectangle", 320, 240);

              // Mats are very memory expensive. Lets reuse this Mat.
              Mat mat = new Mat();

              // This cannot be 'true'. The program will never exit if it is. This
              // lets the robot stop this thread when restarting robot code or
              // deploying.
              while (!Thread.interrupted()) {
                // Tell the CvSink to grab a frame from the camera and put it
                // in the source mat.  If there is an error notify the output.
                if (cvSink.grabFrame(mat) == 0) {
                  // Send the output the error.
                  outputStream.notifyError(cvSink.getError());
                  // skip the rest of the current iteration
                  continue;
                }
                // Put a rectangle on the image
                Imgproc.rectangle(
                    mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                // Give the output stream a new image to display
                outputStream.putFrame(mat);
              }
            });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  }
  //CameraServer.startAutomaticCapture();

  /**
   * This function is called every 20 ms, no matter the mode. Use this  for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
    dashboard.executeDashboard();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    ahrs.zeroYaw(); 
    if (delayChooser.getSelected() == this.autoOptions.waitTime0second)
    {
      delayTime = 0.0;
    }

    if (delayChooser.getSelected() == this.autoOptions.waitTime1second)
    {
      delayTime = 1.0;
    }

    if (delayChooser.getSelected() == this.autoOptions.waitTime2second)
    {
      delayTime = 2.0;
    }

    if (delayChooser.getSelected() == this.autoOptions.waitTime3second)
    {
      delayTime = 3.0;
    }

    if (delayChooser.getSelected() == this.autoOptions.waitTime4second)
    {
      delayTime = 4.0;
    }

    if (delayChooser.getSelected() == this.autoOptions.waitTime5second)
    {
      delayTime = 5.0;
    }
    
    if (delayChooser.getSelected() == this.autoOptions.waitTime6second)
    {
      delayTime = 6.0;
    }
    
    if (delayChooser.getSelected() == this.autoOptions.waitTime7second)
    {
      delayTime = 7.0;
    }


    if (dropPieceChooser.getSelected() == this.autoOptions.dropPieceOn)
    {
      dropPieceChosenOption = true;
    }

    if (dropPieceChooser.getSelected() == this.autoOptions.dropPieceOff)
    {
      dropPieceChosenOption = false;
    }
    

    m_autoSelected = m_chooser.getSelected();
    driveControl.autonomousTimer.reset();
    driveControl.autonomousTimer.start();
    //m_autoSelected = SmartDashboard.getString("Auto choices", kCustomAuto);
    System.out.println("Auto selected: " + m_autoSelected); 
  }
  
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() 
  {
    this.arm.runPivotMotor(0);
    //aprilTag.GetId();
    //System.out.println(aprilTag.GetId());
    //claw.clawEncoderAutoGrabAutonomous();
    autonomousControl.executeAutonomous(m_autoSelected, delayTime, dropPieceChosenOption);
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    arm.armEncoder.setPosition(0.0);
    claw.clawEncoder.setPosition(0.0);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() { 
    teleopControl.executeTeleop();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
