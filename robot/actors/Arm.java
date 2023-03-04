package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.data.PortMap;
import com.revrobotics.RelativeEncoder;

public class Arm    //contains all arm related motors (pivot, and retract(/extend) arm)
{   
    private final MotorController motor_pivot;
    private CANSparkMax armMotor;
    
    public Arm()
    {
        armMotor = new CANSparkMax(PortMap.armCANID.portNumber, MotorType.kBrushless);
        motor_pivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
    }

    public void runPivotMotor(double pivotSpeed) //pivots the arm up or down
    {
        motor_pivot.set(pivotSpeed);
    }

    public void runArmMotor(double retractSpeed) //retracts or extends the arm
    {
        armMotor.set(retractSpeed);
    }
}