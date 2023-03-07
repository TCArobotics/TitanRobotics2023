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
    public CANSparkMax armMotor;
    boolean extended;
    boolean extending;
    boolean retracted;
    boolean retracting;
    private RelativeEncoder armEncoder;
    private double armPosition;
    private double armVelocity;
    private String state;

    
    public Arm()
    {
        armMotor = new CANSparkMax(PortMap.armCANID.portNumber, MotorType.kBrushless);
        motor_pivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
        armEncoder = armMotor.getEncoder();
        state = "retracted";
    }

    public void runPivotMotor(double pivotSpeed) //pivots the arm up or down
    {
        motor_pivot.set(pivotSpeed);
    }

    public void runArmMotor(double retractSpeed) //retracts or extends the arm
    {
        armMotor.set(retractSpeed);
    }

    public void automaticArmRetract(boolean RetractButtonPressed, boolean ExtendButtonPressed)
    {
        armPosition = armEncoder.getPosition();
        // armVelocity = armEncoder.getVelocity();

        System.out.println("Arm Position: " + armPosition );
        System.out.println("Retract Button: " + RetractButtonPressed );
        System.out.println("Extend Button: " + ExtendButtonPressed );
        System.out.println("State: " + state );
        switch(state)
        {
            case "extended":
                armMotor.set(0);
                if(RetractButtonPressed)
                {
                    state = "retracting";
                }
                break;
            case "extending":
                armMotor.set(0.3);
                if(armPosition > 7)
                {
                    state = "extended";
                }
                break;
            case "retracting":
                armMotor.set(-.3);
                if(armPosition < 0.5)
                {
                    state = "retracted";
                }
                break;
            case "retracted":
                armMotor.set(0);
                if(ExtendButtonPressed)
                {
                    state ="extending";
                }
                break;
        }
    }
}