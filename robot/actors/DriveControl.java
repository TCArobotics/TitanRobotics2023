package frc.robot.actors;

import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.Timer;

//This class handles all drive control actions

public class DriveControl
{
    private final MotorController motorFrontLeft;
    private final MotorController motorRearLeft;
    private final MotorController motorFrontRight;
    private final MotorController motorRearRight;
    private double currentTime = Timer.getFPGATimestamp();

    public DriveControl()
    {
        motorFrontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motorRearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motorFrontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motorRearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
    }
    
    public void xboxTankDrive(double xboxLeftY, double xboxRightY, double xboxLeftX, double xboxRightX) //Moves the sets of wheels based on respective inputs //comment out the motors here if using flightTankDrive
    {
        motorFrontLeft.set(-xboxLeftY + (0.5 * xboxRightX));  //subtract 0.02 here from leftY for YaLikeJazz
        motorRearLeft.set(-xboxLeftY + (0.5 * xboxRightX)); //add 0.02 here to leftY for YaLikeJazz
        motorFrontRight.set(xboxLeftY + (0.5 * xboxRightX)); //add 0.015 here to right_Y for And-You
        motorRearRight.set(xboxLeftY + (0.5 * xboxRightX)); //add 0.015 here to right_Y forAnd-You
    }    

    public void driveForward() //Use if charge pad is right in front of starting position
    {
        if(currentTime <= 3)
        {
            motorFrontLeft.set(0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0.5); //add 0.015 here to right_Y forAnd-You
        }
    }
    public void driveForwardAndLeft() //Clue's in the name
    {
        if(currentTime <= 3)
        {
            motorFrontLeft.set(0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0.5); //add 0.015 here to right_Y forAnd-You
        }
        if(3 < currentTime && currentTime <= 4)
        {
            motorFrontLeft.set(0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(-0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(-0.5); //add 0.015 here to right_Y forAnd-You
        }
        if(4 < currentTime && currentTime <=6)
        {
            motorFrontLeft.set(0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0.5); //add 0.015 here to right_Y forAnd-You
        }
    }

    public void driveForwardAndRight() //Clue's in the name
    {
        if(currentTime <= 3)
        {
            motorFrontLeft.set(0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0.5); //add 0.015 here to right_Y forAnd-You
        }
        if(3 < currentTime && currentTime <= 4)
        {
            motorFrontLeft.set(-0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(-0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0.5); //add 0.015 here to right_Y forAnd-You
        }
        if(4 < currentTime && currentTime <=6)
        {
            motorFrontLeft.set(0.5);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0.5); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0.5); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0.5); //add 0.015 here to right_Y forAnd-You
        }
        if(6 < currentTime)
        {
            motorFrontLeft.set(0);  //subtract 0.02 here from leftY for YaLikeJazz
            motorRearLeft.set(0); //add 0.02 here to leftY for YaLikeJazz
            motorFrontRight.set(0); //add 0.015 here to right_Y for And-You
            motorRearRight.set(0); //add 0.015 here to right_Y forAnd-You
        }
    }

}