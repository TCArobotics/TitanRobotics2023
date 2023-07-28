package frc.robot.actors;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.data.PortMap;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.Encoder;

public class Arm    //contains all arm related motors (pivot, and retract(/extend) arm)
{   
    private final MotorController motorPivot;
    Encoder pivotEncoder;
    private static final double cpr = 5; 
    public CANSparkMax armMotor;
    boolean extended;
    boolean extending;
    boolean retracted;
    boolean retracting;
    public RelativeEncoder armEncoder;
    private double armPosition;
    private String state;
    private boolean button2alreadypressed = false;
    public double armStopDescentPowerSubtractor = 0.0;
    
    public Arm()
    {
        armMotor = new CANSparkMax(PortMap.armCANID.portNumber, MotorType.kBrushless);
        motorPivot = new PWMVictorSPX(PortMap.ARMPIVOTMOTOR.portNumber);
        armEncoder = armMotor.getEncoder();
        state = "retracted";
        pivotEncoder = new Encoder(0,1);
    }

    public void runPivotMotor(double pivotSpeed) //pivots the arm up or down
    {
        motorPivot.set(pivotSpeed - 0.07 - armStopDescentPowerSubtractor);
    }

    public void runArmMotor(double retractSpeed) //retracts or extends the arm
    {
        armMotor.set(retractSpeed);
    }

    public void automaticArmRetract(boolean retractButtonPressed, boolean extendButtonPressed)
    {
        armPosition = armEncoder.getPosition();
        switch(state)
        {
            case "extended":
                armMotor.set(0);
                if(retractButtonPressed)
                {
                    state = "retracting";
                }
                //System.out.println("Arm is Extended");
                break;
            case "extending":
                armMotor.set(0.3);
                if(armPosition > 7)
                {
                    state = "extended";
                }
               // System.out.println("Arm is Extending");
                break;
            case "retracting":
                armMotor.set(-.3);
                if(armPosition <= 0)
                {
                    state = "retracted";
                }
               // System.out.println("Arm is Retracting");
                break;
            case "retracted":
                armMotor.set(0);
                if(extendButtonPressed)
                {
                    state ="extending";
                }
               // System.out.println("Arm is Retracted");
                break;
        }
    }
    public void armStopDescent(boolean armStopDescentButtonPressed)
    {
        pivotEncoder.setDistancePerPulse(1/cpr);
        //System.out.println(pivotEncoder.getDistance());
        if(!button2alreadypressed && armStopDescentButtonPressed)
        {
           // motorPivot.set(-0.1);
           armStopDescentPowerSubtractor = 0.1;
            button2alreadypressed = true;
        }
        if(button2alreadypressed && armStopDescentButtonPressed)
        {
            armStopDescentPowerSubtractor = 0.0;
            motorPivot.set(0.0);
        }
    }

    public void armSlowDescent(boolean slowDescentButtonPressed)
    {
        pivotEncoder.setDistancePerPulse(1/cpr);
        //System.out.println(pivotEncoder.getDistance());
        if(pivotEncoder.getDistance() > 180 && pivotEncoder.getDistance() < 500 && slowDescentButtonPressed) //500 is also a placeholder
        {
            motorPivot.set(-0.1);
        }
    }
}