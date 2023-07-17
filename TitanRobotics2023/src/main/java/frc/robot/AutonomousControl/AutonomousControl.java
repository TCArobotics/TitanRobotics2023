package frc.robot.AutonomousControl;
import frc.robot.actors.DriveControl;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.actors.Manipulator;

public class AutonomousControl 
{
    private final DriveControl driveControl;
    public double startTime;
    public boolean startTimeGot = false;
    private final Manipulator manipulator;
    public boolean turningRight = false;
    public boolean turningLeft = false;
    public boolean extending = false;
    public boolean retracting = false;
    public boolean clawOpening = false;
    public boolean clawClosing = false;
    public boolean drivingBackward = false;
    public boolean drivingForward = false;
    public boolean stopping = false;

    public AutonomousControl(DriveControl driveControl, Manipulator manipulator)
    {
        this.driveControl = driveControl;
        this.manipulator = manipulator;
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
        System.out.println(time());
        if (time() <= 1.85)
        {
            driveControl.plugInToTankDrive(0.45, 0.0);
            drivingForward = true;
            stopping = false;
        }
        else if(!turningRight && !turningLeft)
        {
            driveControl.plugInToTankDrive(0.0, 0.0);
            stopping = true;
            drivingForward = false;
            System.out.println("Stopping");
        }
    }
    
    public void ForwardAndBack()
    {
        System.out.println(time());
        if (time() <= 2.775)
        {
            driveControl.plugInToTankDrive(0.45, 0.0);
            drivingForward = true;
            stopping = false;
        }
        if ((time() > 2.775) && (time() <= 4.625))
        {
            driveControl.plugInToTankDrive(-0.45, 0.0);
            drivingBackward = true;
            stopping = false;
        }
        else if(!turningRight && !turningLeft && time() > 4.625)
        {
            driveControl.plugInToTankDrive(0.0, 0.0);
            stopping = true;
            drivingForward = false;
            System.out.println("Stopping");
        }
    }


    public void autoTurn()
    {
        if (time() >= 2.5 && time() <= 3.5)
        {
            driveControl.plugInToTankDrive(0.0, 0.75);
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
        this.manipulator.RunRetractMotor(0.0);
    }

    public void clawAuto()
    {
        this.manipulator.RunClawMotor(0.0);
    }

    public void pivotAuto()
    {
        this.manipulator.RunPivotMotor(0.0);
    }
    

    public void execute(String autoSelected)
    {
        System.out.println(time());
        this.AutoStuffForDashboard();
        switch(autoSelected)
        {
            case "Leave Community":
                this.ForwardAndBack();
                System.out.println("Nice");
            break;

            case "Raise Arm":
                this.pivotAuto();
            break;

            case "Turn":
                this.autoTurn();
            break;
        }
    }
}
