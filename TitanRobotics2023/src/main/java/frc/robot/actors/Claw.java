package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import frc.robot.data.PortMap;
import frc.robot.data.AprilTag;

public class Claw {
    private CANSparkMax clawMotor;
    public RelativeEncoder clawEncoder;
    private String clawState;
    private String clawStateAuto;
    private AprilTag aprilTag = new AprilTag();
    public Claw()
    {
        clawMotor = new CANSparkMax(PortMap.clawCANID.portNumber, MotorType.kBrushless);
        clawState = "closed";
        clawStateAuto = "closed";
        clawEncoder = clawMotor.getEncoder();
    }
    
    public void runClawMotor(double clawSpeed) //closes or opens the claw on the arm
    {
        clawMotor.set(clawSpeed);
    }
    
    public void clawEncoderAutoGrab(boolean autoGrabButtonPressed, boolean autoReleaseButtonPressed) //Automatically grabs and holds onto an object
    {
        //System.out.println(clawState);
        switch(clawState)
        {
            case "open":
                if(!autoGrabButtonPressed)
                {
                    clawMotor.set(0);
                }
                if(autoGrabButtonPressed)
                {
                    clawState = "grabbing";
                }
                System.out.println("Claw is Open");
            break;
            case "grabbing":
                clawMotor.set(0.1);
                if(autoGrabButtonPressed)
                {
                    clawState = "releasing";
                }
                if(clawEncoder.getPosition() == 0)//9 is a placeholder
                {
                    clawState = "closed";
                }
                System.out.println("Claw is Grabbing");
            break;
            case "releasing":
                clawMotor.set(-0.2);
                System.out.println(clawEncoder.getPosition());
                if(clawEncoder.getPosition() <= -5)
                {
                    clawState = "open";
                }
                System.out.println("Claw is Opening");
            break;
            case "closed":
                if(!autoGrabButtonPressed)
                {
                    clawMotor.set(0.0);//This needs to be higher if placing preloaded piece on higher rungs
                }
                if(autoGrabButtonPressed && clawState == "closed")
                {
                    clawState = "releasing";
                }
            break;
        }
    }
    public void clawEncoderAutoGrabAutonomous() //Automatically grabs and holds onto an object
    {
        System.out.println(clawStateAuto);
        System.out.println(clawEncoder);
        switch(clawStateAuto)
        {
            case "open":
                clawMotor.set(0);
                if(aprilTag.GetId() == 1)
                {
                    clawStateAuto = "grabbing";
                }
                System.out.println("Claw is Open");
            break;
            case "grabbing":
                clawMotor.set(0.1);
                if(aprilTag.GetId() == 2)
                {
                    clawStateAuto = "releasing";
                }
                if(clawEncoder.getPosition() >= 2)//9 is a placeholder
                {
                    clawStateAuto = "closed";
                }
                System.out.println("Claw is Grabbing");
            break;
            case "releasing":
                clawMotor.set(-0.2);
                System.out.println(clawEncoder.getPosition());
                if(clawEncoder.getPosition() <= -2)
                {
                    clawStateAuto = "open";
                }
                System.out.println("Claw is Opening");
            break;
            case "closed":
                clawMotor.set(0.0);//This needs to be higher if placing preloaded piece on higher rungs
                if(aprilTag.GetId() == 2)
                {
                    clawStateAuto = "releasing";
                }
            break;
        }
    }
}
