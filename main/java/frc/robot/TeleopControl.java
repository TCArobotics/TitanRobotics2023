package frc.robot;

import frc.robot.actors.Claw;
import frc.robot.actors.Arm;
import frc.robot.controller.FlightJoystick;
import frc.robot.controller.XboxGamePad;
import frc.robot.actors.DriveControl;
import frc.robot.data.ButtonMap;


/*This class controls all robot functions during Teleop.
Its major role his determining what abstract actions the robot should be taking
before offloading tasks to individual handlers such as Drive Control.*/

public class TeleopControl
{
    private final DriveControl driveControl;
    private final Arm arm;
    private final Claw claw;
    private final XboxGamePad xboxGamePad;
    private final FlightJoystick flightJoystick;
    private double flightSliderYInput;

    public TeleopControl(DriveControl driveControl, Claw claw, Arm arm)
    {
        this.driveControl = driveControl;
        this.arm = arm;
        this.claw = claw;
        this.xboxGamePad = new XboxGamePad();
        this.flightJoystick = new FlightJoystick();
    }

    public void clawControl() //controls the closing and opening of the claw (buttons 5 and 6 on flight controller)
    {
        boolean button5pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON5);
        boolean button6pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON6);
        flightSliderYInput = flightJoystick.getStick(ButtonMap.FlightSLIDER);
        //System.out.println("ClawControl runs");
        //System.out.println(flightSliderYInput);
        if(flightSliderYInput < 0.8)
        {
            if(button5pressed)
            {
                this.claw.runClawMotor(-0.2);
            // System.out.println("ClawisClosing");
            }
            else if(button6pressed)
            {
                this.claw.runClawMotor(0.2);
            // System.out.println("ClawisOpening");
            }
            else 
            {
                this.claw.runClawMotor(0);
            }
        }
    }

    public void armControl() //controls the retraction of the arm (buttons 3 and 4 on flight controller)
    {
        boolean button3pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON3);
        boolean button4pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON4);
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

    public void autoArmControl()
    {
        boolean button7pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON7);
        boolean button8pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON8);
        this.arm.automaticArmRetract(button7pressed, button8pressed);
    }

    public void armPivotControl() //controls the pivoting of the arm (Flight controller stick y-axis)
    {
        double flightYInput = flightJoystick.getStick(ButtonMap.FlightSTICKY);
        this.arm.runPivotMotor(-0.4 * flightYInput);
    }

    public void driveTrain() //Controls the drive train--triggers only ONE execution line //comment out the unused drivecontrol version (this.driveControl.flightTankDrive or this.driveControl.xboxTankDrive)
    {
        double xboxLeftStickYInput = xboxGamePad.getStick(ButtonMap.XboxLEFTSTICKY);
        double xboxRightStickYInput = xboxGamePad.getStick(ButtonMap.XboxRIGHTSTICKY);
        double xboxLeftStickXInput = xboxGamePad.getStick(ButtonMap.XboxLEFTSTICKX);
        double xboxRightStickXInput = xboxGamePad.getStick(ButtonMap.XboxRIGHTSTICKX);

    
        this.driveControl.xboxTankDrive(xboxLeftStickYInput, xboxRightStickYInput, xboxLeftStickXInput, xboxRightStickXInput);
    }

    public void autoCaptureGamePiece()
    {
        //System.out.println("AutoCapturePiece works");
        flightSliderYInput = flightJoystick.getStick(ButtonMap.FlightSLIDER);
        if(flightSliderYInput >= 0.8)
        {
            this.claw.clawEncoderAutoGrab(flightJoystick.getButtonFlightPressed(ButtonMap.FlightBUTTON1), flightJoystick.getButtonFlightPressed(ButtonMap.FlightBUTTON2));
        }
    }

    public void armDescentControl()
    {
        boolean button9pressed = flightJoystick.getButtonFlightPressedDebounceOff(ButtonMap.FlightBUTTON9);
        arm.armSlowDescent(button9pressed);
    }

    public void executeTeleop() //Called in Robot.teleopPeriodic(), Contains a single function for each major system on the robot
    {
        this.driveTrain();
        this.clawControl();
        this.armPivotControl();
        this.armControl();
       // this.autoArmControl();
        this.autoCaptureGamePiece();
       // this.armDescentControl();
    }
}
