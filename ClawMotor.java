package frc.robot;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
public class ClawMotor{
   
    private static final int deviceId = 10;
    private CANSparkMax m_motor;
    
    public ClawMotor()
    {
        m_motor = new CANSparkMax(deviceId, MotorType.kBrushless);
        m_motor.set(0.3);
    }








}