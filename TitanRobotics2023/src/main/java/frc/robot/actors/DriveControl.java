package frc.robot.actors;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.data.ButtonMap;
import frc.robot.data.GamePad;

public class DriveControl 
{
    private final MotorController frontRightMotor;
    private final MotorController frontLeftMotor;
    private final MotorController backRightMotor;
    private final MotorController backLeftMotor;
    public boolean isAlreadyCalled= false;
    public boolean isDriveOn = false; 
    private final Timer matchTime;
    private double CurrentTime;

    

    public DriveControl()
    {
        this.frontRightMotor = new PWMVictorSPX(3);
        this.frontLeftMotor = new PWMVictorSPX(1);
        this.backRightMotor = new PWMVictorSPX(0);
        this.backLeftMotor = new PWMVictorSPX(2); 
        matchTime = new Timer();
        CurrentTime = matchTime.get();
    }
    //calls all of the functions inside in robot.java.
    public void Execute()
    {
       
    }


    public void tankDrive(double RSPEEDY, double LSPEEDY)
    {
        this.frontRightMotor.set(RSPEEDY);
        this.backRightMotor.set(RSPEEDY);
        this.frontLeftMotor.set(LSPEEDY);
        this.backLeftMotor.set(LSPEEDY);
    }
    //Gets the wheels spining on the robot. No set time.
    public void basicDrive(double speed)
    {
        this.frontRightMotor.set(speed);
        this.frontLeftMotor.set(speed);
        this.backRightMotor.set(-speed);
        this.backLeftMotor.set(-speed);
    }

    //Timed drive, will run for x amount of seconds.
    public void timedDrive(double speed, double runtime)
    {

            if(matchTime.hasElapsed(runtime + CurrentTime))// while the current time is less than the time the function needs to run for plus the start time.
            {
                this.basicDrive(0);
                isDriveOn = false;
            }
            else
            {
                if(isDriveOn = false)
                {
                    this.basicDrive(speed);
                    isDriveOn = true;

                }
            }
            
        
    }
}
