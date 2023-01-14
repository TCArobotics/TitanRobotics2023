package frc.robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class DriveControl 
{
    private final MotorController frontRightMotor;
    private final MotorController frontLeftMotor;
    private final MotorController backRightMotor;
    private final MotorController backLeftMotor;
    public boolean isAlreadyCalled= false;
    public boolean isDriveOn = false;

    public DriveControl()
    {
        this.frontRightMotor = new PWMVictorSPX(3);
        this.frontLeftMotor = new PWMVictorSPX(1);
        this.backRightMotor = new PWMVictorSPX(0);
        this.backLeftMotor = new PWMVictorSPX(2);
        
    }
    //calls all of the functions inside in robot.java.
    public void Execute()
    {
        this.timedDrive(1,5);
    }

    //Gets the wheels spining on the robot. No set time.
    public void basicDrive(double speed)
    {
        this.frontRightMotor.set(speed);
        this.frontLeftMotor.set(speed);
        this.backRightMotor.set(speed);
        this.backLeftMotor.set(speed);
    }

    //Timed drive, will run for x amount of seconds.
    public void timedDrive(double speed, double runtime)
    {
        if(!isAlreadyCalled)//makes sure the function isn't continuously called
        {
            double startTime = Timer.getFPGATimestamp();//records the time when the function is called.
            isAlreadyCalled = true;
            while(Timer.getFPGATimestamp() <= startTime + runtime)// while the current time is less than the time the function needs to run for plus the start time.
            {
                if(!isDriveOn)
                {
                this.basicDrive(speed);
                isDriveOn = true;
                }
            }
            this.basicDrive(0);
            isDriveOn = false;
        }
    }
}
