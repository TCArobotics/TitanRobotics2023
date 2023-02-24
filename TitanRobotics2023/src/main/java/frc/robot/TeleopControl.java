package frc.robot;

import frc.robot.actors.Manipulator;
import frc.robot.actors.DriveControl;
import frc.robot.data.ButtonMap;
import frc.robot.data.GamePad;


/*This class controls all robot functions during Teleop
It's major role his determining what abstract actions the robot should be taking
Before offloading tasks to individual handlers such as Drive Control*/

public class TeleopControl
{
    private final DriveControl driveControl;
    private final Manipulator manipulator;
    private final GamePad gamePad;


    public TeleopControl(DriveControl driveControl, Manipulator manipulator)
    {
        this.driveControl = driveControl;
        this.manipulator = manipulator;
        this.gamePad = new GamePad();
    }
    public void manipulatorClawControl()
    {
        boolean button5pressed = gamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_5);
        boolean button6pressed = gamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_6);
        if(button5pressed)
        {
            this.manipulator.RunClawMotor(-0.2);
        }
        else if(button6pressed)
        {
            this.manipulator.RunClawMotor(0.2);
        }
        else
        {
            this.manipulator.RunClawMotor(0);
        }

    }
    public void manipulatorRetractControl()
    {
        boolean button3pressed = gamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_3);
        boolean button4pressed = gamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_4);
        if(button3pressed)
        {
            this.manipulator.RunRetractMotor(-0.5);
        }
        else if(button4pressed)
        {
            this.manipulator.RunRetractMotor(0.5);
        }
        else
        {
            this.manipulator.RunRetractMotor(0);
        }

    }
    public void manipulatorPivotControl()
    {
        double Flight_YInput = gamePad.getStick(ButtonMap.Flight_STICK_Y);

        this.manipulator.RunPivotMotor(-0.4 * Flight_YInput);
    }

    public void driveTrain() //Controls the drive train--triggers only ONE execution line
    {
        double Flight_YInput = gamePad.getStick(ButtonMap.Flight_STICK_Y);
        double Flight_ZInput = gamePad.getStick(ButtonMap.Flight_STICK_Z);
        double Flight_Slider = gamePad.getStick(ButtonMap.Flight_SLIDER);

        double Xbox_LEFT_STICK_YInput = gamePad.getStick(ButtonMap.Xbox_LEFT_STICK_Y);
        double Xbox_RIGHT_STICK_YInput = gamePad.getStick(ButtonMap.Xbox_RIGHT_STICK_Y);

        //this.driveControl.flightTankDrive(Flight_YInput, Flight_ZInput, ((-Flight_Slider +1) / 2)); //EXECUTION LINE
        this.driveControl.xboxTankDrive(Xbox_LEFT_STICK_YInput, Xbox_RIGHT_STICK_YInput);
    }
   /* public void alternateMotor()
    {
        this.driveControl.xboxTankDrive(Xbox_LEFT_STICK_YInput, Xbox_RIGHT_STICK_YInput);
    }
    */


    public void XboxButtonsTest()
    {
       if (gamePad.getButtonXboxPressed(ButtonMap.Xbox_A))
       {
        System.out.println("AAAAAAAHHHHHHH"); 
       }       
    }

    public void execute() //Called in Robot.teleopPeriodic(), Contains a single function for each major system on the robot
    {
        this.driveTrain();
        //this.alternateMotor();
        this.XboxButtonsTest();
        this.manipulatorClawControl();
        this.manipulatorPivotControl();
        this.manipulatorRetractControl();
    }

    /*public void testRobot() {
    this.driveControl.flightTankDrive
    public void driveTrain() //Controls the drive train--triggers only ONE execution line
    {
        double leftInput = gamePad.getStick(ButtonMap.STICK_LEFTX);
        double rightInput = gamePad.getStick(ButtonMap.STICK_RIGHTY);

        this.driveControl.tankDrive(leftInput, rightInput); //EXECUTiON LINE
    }*/
}
