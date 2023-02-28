package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.data.PortMap;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Manipulator    //contains all arm related motors (claw, pivot, and retract(/extend) arm)
{   
    private final MotorController motor_pivot;
    private static final int deviceId = 6; //Id is 10 for And_You; Id is 6 for Ya_Like_Jazz
    private CANSparkMax clawMotor;
    private CANSparkMax retractMotor;
    private static final int deviceId2 = 21; //motor does not exist on And_You; Id2 is 21 for Ya_Like_Jazz
    private RelativeEncoder claw_encoder;
    private boolean gotem;
    private boolean grabbing;
    
    public Manipulator()
    {
        clawMotor = new CANSparkMax(deviceId, MotorType.kBrushless);
        retractMotor = new CANSparkMax(deviceId2, MotorType.kBrushless);
        motor_pivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
        claw_encoder = clawMotor.getEncoder();
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

    public void Claw_Motor_withEncoder(boolean FlightButton1Pressed, boolean FlightButton6Pressed)
    {
        SmartDashboard.putNumber("Encoder Position", claw_encoder.getPosition());
        SmartDashboard.putNumber("Encoder Velocity", claw_encoder.getVelocity());
        System.out.println(claw_encoder.getPosition());
        if(claw_encoder.getPosition() >= 0)
        {
            gotem = false;
            grabbing = false;
        }

        if(FlightButton1Pressed && !gotem && !FlightButton6Pressed)
        {
            /*Auto retract/extend arm stuff here*/
            clawMotor.set(-0.03);
            grabbing = true;
        }

        if(claw_encoder.getVelocity() <= 0.5 && grabbing && !FlightButton6Pressed)
        {
            clawMotor.set(-0.2);
            gotem = true;
        }
    }
}