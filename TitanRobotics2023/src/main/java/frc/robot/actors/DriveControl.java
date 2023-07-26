package frc.robot.actors;

import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.Timer;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.data.GyroDrive;
//import edu.wpi.first.wpilibj.Encoder;

//This class handles all drive control actions

public class DriveControl
{
    private final MotorController motorFrontLeft;
    private final MotorController motorRearLeft;
    private final MotorController motorFrontRight;
    private final MotorController motorRearRight;
    public Timer autonomousTimer;
    AHRS ahrs;
    public GyroDrive GyroObject;
    FixDrift fixDrift;
    double teleopDriftCorrectYaw = 0.0;
    double teleopDriftCorrect = 0.0;
    boolean positionGot = false;
    
    //public Encoder driveControlEncoderRight = new Encoder(1,0);
    //public Encoder driveControlEncoderLeft = new Encoder(3,2);

    public DriveControl(AHRS ahrs)
    {
        motorFrontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motorRearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motorFrontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motorRearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
        autonomousTimer = new Timer();
        this.ahrs = ahrs;
        fixDrift = new FixDrift(ahrs);
        
    }

    public void xboxTankDrive(double xboxLeftY, double xboxRightY, double xboxLeftX, double xboxRightX) //Moves the sets of wheels based on respective inputs //comment out the motors here if using flightTankDrive
    {
        motorFrontLeft.set((-xboxLeftY + (0.35 * xboxRightX)));// + teleopDriftCorrect);  //subtract 0.02 here from leftY for Menoetius
        motorRearLeft.set((-xboxLeftY + (0.35 * xboxRightX)));// + teleopDriftCorrect); //add 0.02 here to leftY for Menoetius
        motorFrontRight.set((xboxLeftY + (0.35 * xboxRightX)));// + teleopDriftCorrect); //add 0.015 here to rightY for And-You
        motorRearRight.set((xboxLeftY + (0.35 * xboxRightX)));// + teleopDriftCorrect); //add 0.015 here to rightY forAnd-You
        if (Math.abs(xboxRightX) <= 0.1 && !positionGot)
        {
           teleopDriftCorrectYaw = ahrs.getYaw();
           positionGot = true;
        }
        if (Math.abs(xboxRightX) > 0.1)
        {
            teleopDriftCorrect = 0.0;
            positionGot = false;
        }
        if (Math.abs(xboxRightX) <= 0.1)
        {
            teleopDriftCorrect = fixDrift.DriftCorrectionEtc(teleopDriftCorrectYaw);
        }
    }  

    public void AutoBalTeleopButtonA()
    {
       double autoBalVar = GyroObject.autoBalance();
        motorFrontLeft.set(autoBalVar);
        motorRearLeft.set(autoBalVar);
        motorFrontRight.set(autoBalVar);
        motorRearRight.set(autoBalVar);
       
    }
      
    public void plugInToTankDrive(double Yspeed, double turn)
    {
        double Turn = fixDrift.DriftCorrectionEtc(turn);
        motorFrontLeft.set((Yspeed + Turn) * 1);  //subtract 0.02 here from leftY for YaLikeJazz
        motorRearLeft.set((Yspeed + Turn) * 1); //add 0.02 here to leftY for YaLikeJazz
        motorFrontRight.set((-Yspeed + Turn) * 1); //add 0.015 here to right_Y for And-You
        motorRearRight.set((-Yspeed + Turn) * 1); //add 0.015 here to right_Y forAnd-You
    }

   /*  public void encoderDriveTrain() {
        driveControlEncoderLeft.setDistancePerPulse(Math.PI*6/360);
        driveControlEncoderRight.setDistancePerPulse(Math.PI*6/360);
        double distanceDrivenByRight = driveControlEncoderRight.getDistance() * -1;
        double distanceDrivenByLeft = driveControlEncoderLeft.getDistance();
        motorFrontRight.setInverted(true);
        motorRearRight.setInverted(true);
        //System.out.println(distanceDrivenByLeft);
        //System.out.println(distanceDrivenByRight);
        if (distanceDrivenByLeft <= 143 || distanceDrivenByRight <= 143)
        {
            motorFrontLeft.set(0.5);
            motorFrontRight.set(0.5);
            motorRearLeft.set(0.5);
            motorRearRight.set(0.5);
           // System.out.println("moving");
        }
        if (distanceDrivenByLeft > 80.125 || distanceDrivenByRight > 80.125) 
        {
            motorFrontLeft.set(-0.5);
            motorFrontRight.set(-0.5);
            motorRearLeft.set(-0.5);
            motorRearRight.set(-0.5);
            //System.out.println("backwards");
        }
        if  (distanceDrivenByLeft == 80.125 || distanceDrivenByRight == 80.125) {
            motorFrontLeft.set(0);
            motorFrontRight.set(0);
            motorRearLeft.set(0);
            motorRearRight.set(0);
            //System.out.println("stop");
        }
        
    }*/
}
