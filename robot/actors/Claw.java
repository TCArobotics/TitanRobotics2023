package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
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
    public void clawEncoderAutoGrab(boolean FlightButton1Pressed, boolean FlightButton6Pressed) //Automatically grabs and holds onto an object
    {
        if(clawEncoder.getPosition() >= 0)
        {
            gotem = false;
            grabbing = false;
        }
        if(FlightButton1Pressed && !gotem && !FlightButton6Pressed)
        {
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
