package frc.robot.actors;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.data.PortMap;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class NeoMotor 
{
    private final MotorController Motor_Neo;
    
    public NeoMotor()
    {
        Motor_Neo = new PWMSparkMax(PortMap.SIDEMOTOR.portNumber);
    }
    public void neoMotorMovement(double NeoSpeed)
    {
        Motor_Neo.set(NeoSpeed);

    }
}
