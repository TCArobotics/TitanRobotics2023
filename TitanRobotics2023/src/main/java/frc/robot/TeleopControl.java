package frc.robot;


import frc.robot.actors.DriveControl;
import frc.robot.actors.NeoMotor;
import frc.robot.data.ButtonMap;
import frc.robot.data.GamePad;


/*This class controls all robot functions during Teleop
It's major role his determining what abstract actions the robot should be taking
Before offloading tasks to individual handlers such as Drive Control*/

public class TeleopControl
{
    private final DriveControl driveControl;
    private final GamePad gamePad;
    private final NeoMotor neoMotor;


    public TeleopControl()
    {
        driveControl = new DriveControl();
        gamePad = new GamePad();
        neoMotor = new NeoMotor();
    }

    public void driveTrain() //Controls the drive train--triggers only ONE execution line
    {
        double Flight_YInput = gamePad.getStick(ButtonMap.Flight_STICK_Y);
        double Flight_ZInput = gamePad.getStick(ButtonMap.Flight_STICK_Z);
        double Flight_Slider = gamePad.getStick(ButtonMap.Flight_SLIDER);

        

        this.driveControl.flightTankDrive(Flight_YInput, Flight_ZInput, ((-Flight_Slider +1) / 2)); //EXECUTION LINE
        //this.driveControl.tankDrive(Xbox_LEFT_STICK_YInput, Xbox_RIGHT_STICK_YInput);
    }
    public void alternateMotor()
    {
        double Xbox_LEFT_STICK_YInput = gamePad.getStick(ButtonMap.Xbox_LEFT_STICK_Y);
        double Xbox_RIGHT_STICK_YInput = gamePad.getStick(ButtonMap.Xbox_RIGHT_STICK_Y);

        this.driveControl.tankDrive(Xbox_LEFT_STICK_YInput, Xbox_RIGHT_STICK_YInput);
    }

    public void NeoMotorSystem()
    {
        double Xbox_LEFT_STICK_YInput = gamePad.getStick(ButtonMap.Xbox_LEFT_STICK_Y);
        double Xbox_RIGHT_STICK_YInput = gamePad.getStick(ButtonMap.Xbox_RIGHT_STICK_Y);
        //double Xbox_LEFT_STICK_XInput = gamePad.getStick(ButtonMap.Xbox_LEFT_STICK_X);
        //double Xbox_RIGHT_STICK_XInput = gamePad.getStick(ButtonMap.Xbox_RIGHT_STICK_X);

        this.neoMotor.neoMotorMovement(Xbox_LEFT_STICK_YInput);
    }

    public void execute() //Called in Robot.teleopPeriodic(), Contains a single function for each major system on the robot
    {
        this.driveTrain();
        this.alternateMotor();
        this.NeoMotorSystem();
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
