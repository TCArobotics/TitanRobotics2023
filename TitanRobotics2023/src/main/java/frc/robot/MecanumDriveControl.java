package frc.robot;

import frc.robot.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class MecanumDriveControl
{
   
public MecanumDrive(
MotorController frontLeftMotor,
MotorController rearLeftMotor,
MotorController frontRightMotor,
MotorController rearRightMotor
)
public DriveControl()
{
    frontLeftMotor = new PWMVictorSPX(PortMap.FrontLeft.portNumber);
    rearLeftMotor = new PWMVictorSPX(PortMap.BackLeft.portNumber);
    frontRightMotor = new PWMVictorSPX(PortMap.FrontRight.portNumber);
    rearRightMotor = new PWMVictorSPX(PortMap.BackRight.portNumber);
   
}
}


