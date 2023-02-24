package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.data.PortMap;

public class Manipulator    //contains all arm related motors (claw, pivot, and retract(/extend) arm)
{   
    private final MotorController motor_pivot;
    private static final int deviceId = 6; //Id is 10 for And_You; Id is 6 for Ya_Like_Jazz
    private CANSparkMax clawMotor;
    private CANSparkMax retractMotor;
    private static final int deviceId2 = 21; //motor does not exist on And_You; Id2 is 21 for Ya_Like_Jazz
    
    public Manipulator()
    {
        clawMotor = new CANSparkMax(deviceId, MotorType.kBrushless);
        retractMotor = new CANSparkMax(deviceId2, MotorType.kBrushless);
        motor_pivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
    }

    public void RunClawMotor(double clawSpeed) //closes or opens the claw on the arm
    {
        clawMotor.set(clawSpeed);
    }

    public void RunPivotMotor(double pivotSpeed) //pivots the arm up or down
    {
        motor_pivot.set(pivotSpeed);
    }

    public void RunRetractMotor(double retractSpeed) //retracts or extends the arm
    {
        retractMotor.set(retractSpeed);
    }





}