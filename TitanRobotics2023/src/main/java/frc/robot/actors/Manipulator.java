package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.data.PortMap;

public class Manipulator{
    private final MotorController motor_pivot;
    private static final int deviceId = 6;
    private CANSparkMax clawMotor;
    private CANSparkMax retractMotor;
    private static final int deviceId2 = 21;
    
    public Manipulator()
    {
        clawMotor = new CANSparkMax(deviceId, MotorType.kBrushless);
        retractMotor = new CANSparkMax(deviceId2, MotorType.kBrushless);
        motor_pivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
    }
    public void RunClawMotor(double clawSpeed)
    {
        clawMotor.set(clawSpeed);
    }
    public void RunPivotMotor(double pivotSpeed)
    {
        motor_pivot.set(pivotSpeed);
    }
    public void RunRetractMotor(double retractSpeed)
    {
        retractMotor.set(retractSpeed);
    }





}