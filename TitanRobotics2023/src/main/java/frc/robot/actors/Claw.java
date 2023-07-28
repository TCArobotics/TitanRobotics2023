package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import frc.robot.data.PortMap;
import frc.robot.data.AprilTag;
import edu.wpi.first.wpilibj.Timer;

public class Claw {
    private CANSparkMax clawMotor;
    public RelativeEncoder clawEncoder;
    private String clawState;
    private Boolean zeroSet = false;
    private String clawStateAuto;
    private AprilTag aprilTag = new AprilTag();
    public double startTimeClaw;
    public boolean startTimeGotClaw = false;
    
    public Claw()
    {
        clawMotor = new CANSparkMax(PortMap.clawCANID.portNumber, MotorType.kBrushless);
        clawState = "releasing";
        zeroSet = false;
        clawStateAuto = "closed";
        clawEncoder = clawMotor.getEncoder();
    }

    public double timeClaw()
    {
        if (startTimeGotClaw == false)
        {
            startTimeClaw = (Timer.getFPGATimestamp());
            startTimeGotClaw = true;
        }
        return (Timer.getFPGATimestamp() - startTimeClaw);
    }
    
    public void runClawMotor(double clawSpeed) //closes or opens the claw on the arm
    {
        clawMotor.set(clawSpeed);
    }
    
    public void clawEncoderAutoGrab(boolean autoGrabButtonPressed, boolean autoReleaseButtonPressed) //Automatically grabs and holds onto an object
    {
        //System.out.println ("time" + timeClaw());
        //System.out.println (zeroSet);
        
        //System.out.println(clawState);
        switch(clawState)
        {
            case "open":
                if(autoGrabButtonPressed)
                {
                    clawState = "grabbing";
                }
                //System.out.println("Claw is Open");
            break;
            case "grabbing":
                clawMotor.set(0.15);
                if(autoGrabButtonPressed)
                {
                    clawState = "releasing";
                }
                if(clawEncoder.getPosition() >= 20.0 && zeroSet == true)//9 is a placeholder
                {
                    clawMotor.set(0.025);
                    clawState = "closed";
                }
                //System.out.println("Claw is Grabbing");
            break;
            case "releasing":
                if(zeroSet)
                {
                    clawMotor.set(-0.3);
                }
                //System.out.println(clawEncoder.getPosition());
                if(timeClaw() <= 1.5)
                {
                    clawMotor.set(-0.1);
                    clawEncoder.setPosition(0.0);
                }
                if(timeClaw() > 1.5)
                {
                    zeroSet = true;
                }
                if(clawEncoder.getPosition() <= 10.0 && zeroSet)
                {
                    clawMotor.set(-0.025);
                    clawState = "open";
                }
                if(autoGrabButtonPressed)
                {
                    clawState = "grabbing";
                }
                //System.out.println("Claw is Opening");
            break;
            case "closed":
                if(autoGrabButtonPressed)
                {
                    clawState = "releasing";
                }
            break;
        }
    }
   /*  public void clawEncoderAutoGrabAutonomous() //Automatically grabs and holds onto an object
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
    }*/
}