package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.data.PortMap;

public class Claw {
    private CANSparkMax clawMotor;
    private RelativeEncoder clawEncoder;
    private boolean gotem;
    private boolean grabbing;

    public Claw()
    {
        clawMotor = new CANSparkMax(PortMap.clawCANID.portNumber, MotorType.kBrushless);
        clawEncoder = clawMotor.getEncoder();
    }
    public void runClawMotor(double clawSpeed) //closes or opens the claw on the arm
    {
        clawMotor.set(clawSpeed);
    }
    public void clawEncoderAutoGrab(boolean FlightButton1Pressed, boolean FlightButton6Pressed)
    {
        SmartDashboard.putNumber("Encoder Position", clawEncoder.getPosition());
        SmartDashboard.putNumber("Encoder Velocity", clawEncoder.getVelocity());
        System.out.println(clawEncoder.getPosition());
        if(clawEncoder.getPosition() >= 0)
        {
            gotem = false;
            grabbing = false;
        }
        if(FlightButton1Pressed && !gotem && !FlightButton6Pressed)
        {
            /*Auto retract/extend arm stuff
             */
        
            clawMotor.set(-0.03);
            grabbing = true;
        }
        if(clawEncoder.getVelocity() <= 0.5 && grabbing && !FlightButton6Pressed)
        {
            clawMotor.set(-0.2);
            gotem = true;
        }
    }
}
