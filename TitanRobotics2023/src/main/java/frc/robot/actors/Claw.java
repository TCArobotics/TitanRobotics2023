package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import frc.robot.data.PortMap;

public class Claw {
    private CANSparkMax clawMotor;
    public RelativeEncoder clawEncoder;
    private String clawState;
    public Claw()
    {
        clawMotor = new CANSparkMax(PortMap.clawCANID.portNumber, MotorType.kBrushless);
        clawEncoder = clawMotor.getEncoder();
        clawState = "closed";
    }
    
    public void runClawMotor(double clawSpeed) //closes or opens the claw on the arm
    {
        clawMotor.set(clawSpeed);
       // System.out.println("ClawFunctionRuns");
    }
    
    public void clawEncoderAutoGrab(boolean autoGrabButtonPressed, boolean autoReleaseButtonPressed) //Automatically grabs and holds onto an object
    {
        switch(clawState)
        {
            case "open":
                clawMotor.set(0);
                if(autoGrabButtonPressed)
                {
                    clawState = "grabbing";
                }
                //System.out.println("Claw is Open");
            break;
            case "grabbing":
                clawMotor.set(-0.2);
                if(autoReleaseButtonPressed)
                {
                    clawState = "releasing";
                }
                if(clawEncoder.getPosition() > 9)//9 is a placeholder
                {
                    clawState = "closed";
                }
                //System.out.println("Claw is Grabbing");
            break;
            case "releasing":
                clawMotor.set(0.03);
                if(clawEncoder.getPosition() <= 0)
                {
                    clawState = "open";
                }
                //System.out.println("Claw is Opening");
            break;
            case "closed":
                clawMotor.set(0.0);//This needs to be higher if placing preloaded piece on higher rungs
                if(autoReleaseButtonPressed)
                {
                    clawState = "releasing";
                }
            break;
        }
    }
}
