package frc.robot;


import frc.robot.actors.DriveControl;
import frc.robot.data.ButtonMap;
import frc.robot.data.GamePad;

//This class controls all robot functions during Teleop
//It's major role his determining what abstract actions the robot should be taking
//Before offloading tasks to individual handlers such as Drive Control

public class TeleopControl
{
    private final DriveControl driveControl;
    private final GamePad gamePad;


    public TeleopControl()
    {
        driveControl = new DriveControl();
        gamePad = new GamePad();
    }

    public void driveTrain() //Controls the drive train--triggers only ONE execution line
    {
        double YInput = gamePad.getStick(ButtonMap.STICK_Y);
        double ZInput = gamePad.getStick(ButtonMap.STICK_Z);
        double Slider = gamePad.getStick(ButtonMap.SLIDER);

        this.driveControl.tankDrive(YInput, ZInput, (-Slider +1) / 2); //EXECUTION LINE
    }

    public void execute() //Called in Robot.teleopPeriodic(), Contains a single function for each major system on the robot
    {
        this.driveTrain();
    }

   // public void testRobot() {
        //this.driveControl.tankDrive(-0.5, -0.5);
    //}

    //public void driveTrain() //Controls the drive train--triggers only ONE execution line
    //{
      //  double leftInput = gamePad.getStick(ButtonMap.STICK_LEFTX);
        //double rightInput = gamePad.getStick(ButtonMap.STICK_RIGHTY);

        //this.driveControl.tankDrive(leftInput, rightInput); //EXECUTiON LINE
    //}
}
