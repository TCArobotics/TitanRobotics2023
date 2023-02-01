package frc.robot;
import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
//This class controls all robot functions during the Autonomous period

public class AutonomousControl
{
    private final MotorController motor_frontLeft;
    private final MotorController motor_rearLeft;
    private final MotorController motor_frontRight;
    private final MotorController motor_rearRight;
    public AutonomousControl()
    {
        motor_frontLeft = new PWMVictorSPX(PortMap.FRONTLEFT.portNumber);
        motor_rearLeft = new PWMVictorSPX(PortMap.REARLEFT.portNumber);
        motor_frontRight = new PWMVictorSPX(PortMap.FRONTRIGHT.portNumber);
        motor_rearRight = new PWMVictorSPX(PortMap.REARRIGHT.portNumber);
    }

    public void execute() //Called Periodically during Autonomous
    {
        motor_frontLeft.set(-0.5);
        motor_rearLeft.set(-0.5);
        motor_frontRight.set(0.5);
        motor_rearRight.set(0.5);
    }
}