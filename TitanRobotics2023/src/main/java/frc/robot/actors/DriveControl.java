package frc.robot.actors;

import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

//This class handles all drive control actions

public class DriveControl
{
    private final MotorController motor_frontLeft;
    private final MotorController motor_rearLeft;
    private final MotorController motor_frontRight;
    private final MotorController motor_rearRight;

    public DriveControl()
    {
        motor_frontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motor_rearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motor_frontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motor_rearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
    }

    public void flightTankDrive(double Flight_Y, double Flight_Z, double Flight_slider) //Moves the sets of wheels based on respective inputs //comment out the motors here if using xboxTankDrive
    {
        //motor_frontLeft.set((-Flight_Y + Flight_Z) * Flight_slider);  //subtract 0.02 here from leftY for YaLikeJazz, slider is speed
        //motor_rearLeft.set((-Flight_Y + Flight_Z) * Flight_slider); //add 0.02 here to leftY for YaLikeJazz
        //motor_frontRight.set((Flight_Y + Flight_Z + 0.015) * Flight_slider); //add 0.015 here for And-You
        //motor_rearRight.set((Flight_Y + Flight_Z + 0.015) * Flight_slider); //add 0.015 here for And-You
    }  

    public void xboxTankDrive(double Xbox_left_Y, double Xbox_right_Y, double Xbox_left_X, double Xbox_right_X) //Moves the sets of wheels based on respective inputs //comment out the motors here if using flightTankDrive
    {
        motor_frontLeft.set((-Xbox_left_Y + (0.5 * Xbox_right_X)) * 1.024);  //subtract 0.02 here from leftY for YaLikeJazz
        motor_rearLeft.set((-Xbox_left_Y + (0.5 * Xbox_right_X)) * 1.024); //add 0.02 here to leftY for YaLikeJazz
        motor_frontRight.set(Xbox_left_Y + (0.5 * Xbox_right_X)); //add 0.015 here to right_Y for And-You
        motor_rearRight.set(Xbox_left_Y + (0.5 * Xbox_right_X)); //add 0.015 here to right_Y forAnd-You
    }    

    public void plugInToTankDrive(double Yspeed, double Turn)
    {
        motor_frontLeft.set((Yspeed + Turn) * 1.364);  //subtract 0.02 here from leftY for YaLikeJazz
        motor_rearLeft.set((Yspeed + Turn) * 1.364); //add 0.02 here to leftY for YaLikeJazz
        motor_frontRight.set(-Yspeed + Turn); //add 0.015 here to right_Y for And-You
        motor_rearRight.set(-Yspeed + Turn); //add 0.015 here to right_Y forAnd-You
    }
}