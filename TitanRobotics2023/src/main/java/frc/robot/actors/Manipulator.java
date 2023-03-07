package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.data.PortMap;
import com.revrobotics.RelativeEncoder;

public class Manipulator    //contains all arm related motors (claw, pivot, and retract(/extend) arm)
{   
    private final MotorController motor_pivot;
    //private static int deviceId; //Id is 10 for And_You; Id is 6 for Ya_Like_Jazz
    //public CANSparkMax clawMotor;
    public CANSparkMax retractMotor;
    //private static int deviceId2; //motor does not exist on And_You; Id2 is 21 for Ya_Like_Jazz; Id2 is 27 for Kazoo
    public RelativeEncoder clawEncoder;
    public RelativeEncoder retractEncoder;
    public boolean gotem;
    public boolean grabbing;
    
    public Manipulator()
    {
        //deviceId = 6; //Id is 10 for And_You; Id is 6 for Ya_Like_Jazz
        //deviceId2 = 27; //motor does not exist on And_You; Id2 is 21 for Ya_Like_Jazz; Id2 is 27 for Kazoo
        //clawMotor = new CANSparkMax(PortMap.clawCANID.portNumber, MotorType.kBrushless);
        retractMotor = new CANSparkMax(PortMap.retractorCANID.portNumber, MotorType.kBrushless);
        motor_pivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
        //clawEncoder = clawMotor.getEncoder();
    }

    public void RunClawMotor(double clawSpeed) //closes or opens the claw on the arm
    {
       // clawMotor.set(clawSpeed);
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
        //System.out.println(clawEncoder.getPosition());
        //if(clawEncoder.getPosition() >= 0)
        {
            gotem = false;
            grabbing = false;
        }

        if(FlightButton1Pressed && !gotem && !FlightButton6Pressed)
        {
            /*Auto retract/extend arm stuff here*/
            //clawMotor.set(-0.03);
            grabbing = true;
        }

        if(clawEncoder.getVelocity() <= 0.5 && grabbing && !FlightButton6Pressed)
        {
           // clawMotor.set(-0.2);
            gotem = true;
        }
    }
}