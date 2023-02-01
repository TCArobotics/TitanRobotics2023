package frc.robot.actors;

//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

//This class handles all drive control actions

public class DriveControl
{
    private final MotorController motor_frontLeft;
    private final MotorController motor_rearLeft;
    private final MotorController motor_frontRight;
    private final MotorController motor_rearRight;
    //private final MotorControllerGroup motorGroup_left;
    //private final MotorControllerGroup motorGroup_right;
    //private final DifferentialDrive robotDrive;

    public DriveControl()
    {
        motor_frontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motor_rearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motor_frontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motor_rearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
        //motorGroup_left = new MotorControllerGroup(motor_frontLeft, motor_rearLeft);
        //motorGroup_right = new MotorControllerGroup(motor_frontRight, motor_rearRight);
        //robotDrive = new DifferentialDrive(motorGroup_left, motorGroup_right);
    }

    public void tankDrive(double Y, double Z, double slider) //Moves the sets of wheels based on respective inputs
    {
        //motor_frontLeft.set(Y); //subtract 0.02 here from leftY for YaLike Jazz
        //motor_rearLeft.set(-Y); //add 0.02 here from leftY for YaLikeJazz
        //motor_frontRight.set(Y);
        //motor_rearRight.set(Y);
       
        motor_frontLeft.set((-Y + Z) * slider);  //subtract 0.02 here from leftY for YaLike Jazz, slider is speed
        motor_rearLeft.set((-Y + Z) * slider); //add 0.02 here from leftY for YaLikeJazz
        motor_frontRight.set((Y + Z) * slider);
        motor_rearRight.set((Y + Z) * slider);
        // System.out.println(rightY);
        //System.out.println(leftY);


    }
    public void arcadeCurvatureDrive(double speed, double turn) //Moves the sets of wheels with a speed and turning ratio
    {
        if(Math.abs(speed) > 0.1) //When the speed input is low enough, the robot switches to arcade, enabling turns in place
        {
            //robotDrive.curvatureDrive(speed, turn, false);
        }
        else
        {
            //robotDrive.arcadeDrive(0, turn);
        }
    }
}