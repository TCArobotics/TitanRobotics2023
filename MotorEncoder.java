package frc.robot;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

public class MotorEncoder {
    private static final int deviceId = 10;
    private RelativeEncoder m_encoder;
    private CANSparkMax m_motor;
    
    public MotorEncoder()
    {
        m_motor = new CANSparkMax(deviceId, MotorType.kBrushless);
        m_motor.set(0.3);
        SmartDashboard.putNumber("Encoder Position", m_encoder.getPosition());
        SmartDashboard.putNumber("Encoder Velocity", m_encoder.getVelocity());
        m_encoder = m_motor.getEncoder();
    }
}
