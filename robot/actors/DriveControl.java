package frc.robot.actors;

import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

//This class handles all drive control actions

public class DriveControl
{
    private final MotorController motorFrontLeft;
    private final MotorController motorRearLeft;
    private final MotorController motorFrontRight;
    private final MotorController motorRearRight;

    public DriveControl()
    {
        motorFrontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motorRearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motorFrontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motorRearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
    }

    public void flightTankDrive(double flightY, double flightZ, double flightSLider) //Moves the sets of wheels based on respective inputs //comment out the motors here if using xboxTankDrive
    {
        //motorFrontLeft.set((-flightY + flightZ) * flightSLider);  //subtract 0.02 here from leftY for YaLikeJazz, slider is speed
        //.set((-flightY + flightZ) * flightSLider); //add 0.02 here to leftY for YaLikeJazz
        //motorFrontRight.set((flightY + flightZ + 0.015) * flightSLider); //add 0.015 here for And-You
        //motorRearRight.set((flightY + flightZ + 0.015) * flightSLider); //add 0.015 here for And-You
    }    
    public void xboxTankDrive(double xboxLeftY, double xboxRightY, double xboxLeftX, double xboxRightX) //Moves the sets of wheels based on respective inputs //comment out the motors here if using flightTankDrive
    {
        motorFrontLeft.set(-xboxLeftY + (0.5 * xboxRightX));  //subtract 0.02 here from leftY for YaLikeJazz
        motorRearLeft.set(-xboxLeftY + (0.5 * xboxRightX)); //add 0.02 here to leftY for YaLikeJazz
        motorFrontRight.set(xboxLeftY + (0.5 * xboxRightX)); //add 0.015 here to right_Y for And-You
        motorRearRight.set(xboxLeftY + (0.5 * xboxRightX)); //add 0.015 here to right_Y forAnd-You
    }    

}