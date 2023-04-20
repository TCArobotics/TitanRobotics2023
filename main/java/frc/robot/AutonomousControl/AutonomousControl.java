package frc.robot.AutonomousControl;
import frc.robot.actors.DriveControl;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.actors.Arm;
import frc.robot.actors.Claw;
import frc.robot.data.GyroDrive;
import frc.robot.data.AutoOptions;

public class AutonomousControl 
{
    private final DriveControl driveControl;
    public double xAxisRate;
    public GyroDrive GyroObject;
    public double startTime;
    public boolean startTimeGot = false;
    private final Arm arm;
    private Claw claw;
    public boolean turningRight = false;
    public boolean turningLeft = false;
    public boolean extending = false;
    public boolean retracting = false;
    public boolean clawOpening = false;
    public boolean clawClosing = false;
    public boolean drivingBackward = false;
    public boolean drivingForward = false;
    public boolean stopping = false;
    public boolean balancing = false;
    private AutoOptions autoOptions;
    AHRS ahrs; 
    boolean zeroed = false;
    double delay;

    public AutonomousControl(DriveControl driveControl, Arm arm, Claw claw, AHRS ahrs)
    {
        this.driveControl = driveControl;
        this.arm = arm;
        this.claw = claw;
        GyroObject = new GyroDrive(ahrs);
        this.autoOptions = new AutoOptions();
        this.ahrs = ahrs;
    }

    public void AutoStuffForDashboard()
    {
        SmartDashboard.putBoolean("Turning Right", turningRight);
        SmartDashboard.putBoolean("Turning Left", turningLeft);
        SmartDashboard.putBoolean("Extending", extending);
        SmartDashboard.putBoolean("Retracting", retracting);
        SmartDashboard.putBoolean("Claw Opening", clawOpening);
        SmartDashboard.putBoolean("Claw Closing", clawClosing);
        SmartDashboard.putBoolean("Driving Forward", drivingForward);
        SmartDashboard.putBoolean("Driving Backward", drivingBackward);
        SmartDashboard.putBoolean("Stopping", stopping);
        SmartDashboard.putBoolean("Balancing", balancing);
    }

    public double time()
    {
        if (startTimeGot == false)
        {
            startTime = (Timer.getFPGATimestamp());
            startTimeGot = true;
        }
        return (Timer.getFPGATimestamp() - startTime);
    }

    public void ForwardAndStop()
    {
        //System.out.println(time());
        if (time() > delay && time() <= 1.85 + delay)
        {
            driveControl.plugInToTankDrive(0.45, 0.0);
            drivingForward = true;
            stopping = false;
        }
        else if(!turningRight && !turningLeft)
        {
            driveControl.plugInToTankDrive(-0.02, 0.0);
            stopping = true;
            drivingForward = false;
            //System.out.println("Stopping");
        }
    }
    public void ForwardAndStopOverRamp()
    {
        //System.out.println(time());
        if (time() > delay && time() <= 3.5 + delay)
        {
            driveControl.plugInToTankDrive(0.45, 0.0);
            drivingForward = true;
            stopping = false;
        }
        else if(!turningRight && !turningLeft)
        {
            driveControl.plugInToTankDrive(-0.02, 0.0);
            stopping = true;
            drivingForward = false;
            //System.out.println("Stopping");
        }
    }

    public void AutoBalanceAuto()
    {
        xAxisRate = GyroObject.autoBalance();
        //System.out.println("gyro");
        driveControl.plugInToTankDrive(-xAxisRate, 0.0);
    }
    
    public void ForwardAndBack()
    {
        
        double forwardTime = 3.0 + delay; //change the numerical values according to how long you want the phase to last
        double backwardsTime = 1.5 + forwardTime; //2.2
        //System.out.println(time());
        if (time() > delay && time() <= forwardTime)
        {
            driveControl.plugInToTankDrive(0.45, 0.0);
            drivingForward = true;
            stopping = false;
        }

        if ((time() > forwardTime) && (time() <= backwardsTime))
        {
            driveControl.plugInToTankDrive(-0.45, 0.0);
            drivingBackward = true;
            stopping = false;
        }

        if(time() > backwardsTime)
        {
            //stopping = true;
            balancing = true;
            //drivingForward = false;
            //System.out.println("Stopping");
         this.AutoBalanceAuto();
        }
    }

    public void autoTurn()
    {
        if (time() >= 0 && time() <= 5.0)
        {
            driveControl.plugInToTankDrive(0.0, 90);
            turningRight = true;
            drivingForward = false;
            stopping = false;
        }
        else
        {
            turningRight = false;
        }
    }

    public void retractorAuto()
    {
        this.arm.runArmMotor(0.0);
    }

    public void clawAuto()
    {
        this.claw.runClawMotor(0.15);
    }

    public void pivotAuto(double AutoPivotSpeed)
    {
        this.arm.runPivotMotor(AutoPivotSpeed);
    }
    
    public void executeAutonomous(String autoSelected, double delay)
    {
        this.delay = delay;
        if (zeroed = false)
        {
            ahrs.zeroYaw();
            zeroed = true;
        }
       // System.out.println(time());
        this.AutoStuffForDashboard();
        //this.AutoBalanceAuto();
        if(autoSelected == "Raise Arm") //unused
        {
            this.pivotAuto(-0.25);
        }
        if(autoSelected == this.autoOptions.LeaveCommunityOption) //Functional and important
        {
            this.ForwardAndBack();
            this.pivotAuto(-0.08);
           // System.out.println("Nice");
        }
        if(autoSelected == this.autoOptions.ninetydegreeTurn) //not functional yet
        {
            this.autoTurn();
        }

        if(autoSelected == this.autoOptions.MobilityOption) //needs testing
        {
            this.ForwardAndStop();
        }

        if(autoSelected == this.autoOptions. MobilityOverRampOption) //Functional and important
        {
            this.ForwardAndStopOverRamp();
        }
    }
}
