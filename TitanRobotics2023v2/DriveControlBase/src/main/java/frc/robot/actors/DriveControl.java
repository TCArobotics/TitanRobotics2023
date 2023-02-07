package frc.robot.actors;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import frc.robot.data.Portmap;



public class DriveControl 
{
    private final MotorController frontRightMotor;
    private final MotorController frontLeftMotor;
    private final MotorController backRightMotor;
    private final MotorController backLeftMotor;
    

    public boolean isAlreadyCalled= false;
    public boolean isDriveOn = false; 
    // private final Timer matchTime;
    //private double CurrentTime;

    

    public DriveControl()
    {
        frontRightMotor = new PWMVictorSPX(3);
        frontLeftMotor = new PWMVictorSPX(1);
        backRightMotor = new PWMVictorSPX(0);
        backLeftMotor = new PWMVictorSPX(2);
        backRightMotor.setInverted(true);
        frontRightMotor.setInverted(true);


        
        
        // matchTime = new Timer();
        //CurrentTime = matchTime.get();
    }
    //calls all of the functions inside in robot.java.
    // public void Execute()
    // {
       
    // }

    // public void timerReset()
    // {
    //     matchTime.reset();
    //     matchTime.start();
    // }

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
        this.backRightMotor.set(speed);
        this.frontLeftMotor.set(speed);
        this.backLeftMotor.set(speed);
    }


    //Timed drive, will run for x amount of seconds.
    
}
