package frc.robot;

import frc.robot.actors.Claw;
import frc.robot.actors.Arm;
import frc.robot.controller.FlightGamePad;
import frc.robot.controller.XboxGamePad;
import frc.robot.actors.DriveControl;
import frc.robot.data.ButtonMap;


/*This class controls all robot functions during Teleop
Its major role his determining what abstract actions the robot should be taking
Before offloading tasks to individual handlers such as Drive Control*/

public class TeleopControl
{
    private final DriveControl driveControl;
    private final Arm arm;
    private final Claw claw;
    private final XboxGamePad xboxGamePad;
    private final FlightGamePad flightGamePad;


    public TeleopControl(DriveControl driveControl, Claw claw, Arm arm)
    {
        this.driveControl = driveControl;
        this.arm = arm;
        this.claw = claw;
        this.xboxGamePad = new XboxGamePad();
        this.flightGamePad = new FlightGamePad();

    }
    public void clawControl() //controls the closing and opening of the claw (buttons 5 and 6 on flight controller)
    {
        boolean button5pressed = flightGamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_5);
        boolean button6pressed = flightGamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_6);
        if(button5pressed)
        {
            this.claw.runClawMotor(-0.2);
        }
        else if(button6pressed)
        {
            this.claw.runClawMotor(0.2);
        }
        else
        {
            this.claw.runClawMotor(0);
        }

    }
    public void armRetractControl() //controls the retraction of the arm (buttons 3 and 4 on flight controller)
    {
        boolean button3pressed = flightGamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_3);
        boolean button4pressed = flightGamePad.getButtonFlightPressedDebounceOff(ButtonMap.Flight_BUTTON_4);
        if(button3pressed)
        {
            this.arm.runArmMotor(-0.5);
        }
        else if(button4pressed)
        {
            this.arm.runArmMotor(0.5);
        }
        else
        {
            this.arm.runArmMotor(0);
        }

    }
    public void armPivotControl() //controls the pivoting of the arm (Flight controller stick y-axis)
    {
        double flightYInput = flightGamePad.getStick(ButtonMap.Flight_STICK_Y);

        this.arm.runPivotMotor(-0.4 * flightYInput);
    }

    public void driveTrain() //Controls the drive train--triggers only ONE execution line //comment out the unused drivecontrol version (this.driveControl.flightTankDrive or this.driveControl.xboxTankDrive)
    {
        double xboxLeftStickYInput = xboxGamePad.getStick(ButtonMap.Xbox_LEFT_STICK_Y);
        double xboxRightStickYInput = xboxGamePad.getStick(ButtonMap.Xbox_RIGHT_STICK_Y);
        double xboxLeftStickXInput = xboxGamePad.getStick(ButtonMap.Xbox_LEFT_STICK_X);
        double xboxRightStickXInput = xboxGamePad.getStick(ButtonMap.Xbox_RIGHT_STICK_X);

        //this.driveControl.flightTankDrive(flightYInput, flightZInput, ((-flightSlider +1) / 2)); //EXECUTION LINE
        this.driveControl.xboxTankDrive(xboxLeftStickYInput, xboxRightStickYInput, xboxLeftStickXInput, xboxRightStickXInput);
    }

    public void autoCaptureGamePiece()
    {
        claw.clawEncoderAutoGrab(flightGamePad.getButtonFlightPressed(ButtonMap.Flight_BUTTON_1), flightGamePad.getButtonFlightPressed(ButtonMap.Flight_BUTTON_5));
    }
    public void xboxButtonsTest() //test of the xbox button "A"
    {
       if (xboxGamePad.getButtonXboxPressed(ButtonMap.Xbox_A))
       {
        System.out.println("AAAAAAAHHHHHHH"); 
       }       
    }

    public void execute() //Called in Robot.teleopPeriodic(), Contains a single function for each major system on the robot
    {
        this.driveTrain();
        this.clawControl();
        this.armPivotControl();
        this.armRetractControl();
        // this.AutoCaptureGamePiece();
    }
}
