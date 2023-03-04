package frc.robot;
import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
//This class controls all robot functions during the Autonomous period

public class AutonomousControl
{
    private final MotorController motorFrontLeft;
    private final MotorController motorRearLeft;
    private final MotorController motorFrontRight;
    private final MotorController motorRearRight;
    public AutonomousControl()
    {
        motorFrontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motorRearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motorFrontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motorRearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
    }

    public void execute() //Called Periodically during Autonomous
    {
        motorFrontLeft.set(-0.5);
        motorRearLeft.set(-0.5);
        motorFrontRight.set(0.5);
        motorRearRight.set(0.5);
    }
}