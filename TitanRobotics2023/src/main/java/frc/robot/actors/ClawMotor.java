package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
public class ClawMotor{
   
    private static final int deviceId = 10;
    private CANSparkMax m_motor;
    
    public ClawMotor()
    {
        m_motor = new CANSparkMax(deviceId, MotorType.kBrushless);
    }
    public void RunMotor()
    {
        m_motor.set(0.3);
    }







}