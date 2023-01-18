package frc.robot;

import frc.robot.actors.DriveControl;
import frc.robot.data.ButtonMap;
import frc.robot.data.GamePad;

public class TeleopControl 
{
    
    private final DriveControl driveControl;
    private final GamePad gamePad;

    public TeleopControl()
    {
         driveControl = new DriveControl();
         gamePad = new GamePad();
    }

    public void Execute()
    {
        this.DriveTrain();
    }

    public void DriveTrain()
    {
        double LeftStickY = gamePad.getStick(ButtonMap.STICK_LEFTY);
        double RightStickY = gamePad.getStick(ButtonMap.STICK_RIGHTY);

        this.driveControl.tankDrive(RightStickY, LeftStickY);
    }


}
