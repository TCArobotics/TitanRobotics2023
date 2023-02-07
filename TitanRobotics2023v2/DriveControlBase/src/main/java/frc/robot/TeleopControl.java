package frc.robot;

import frc.robot.actors.DriveControl;
import frc.robot.data.ButtonMap;
import frc.robot.data.GamePad;
import edu.wpi.first.wpilibj.Timer;



public class TeleopControl 
{
    
    private final DriveControl driveControl;
    private final GamePad gamePad;
    public final Timer matchTime;


    public TeleopControl()
    {
        driveControl = new DriveControl();
        gamePad = new GamePad();
        matchTime = new Timer();
    }

    

    public void Execute()
    {
        this.timeTest();
    }

    public void TimerReset()
    {
        // matchTime.reset();
        matchTime.start();
    }
    public void DriveTrain()
    {
        double LeftStickY = gamePad.getStick(ButtonMap.STICK_LEFTY);
        double RightStickY = gamePad.getStick(ButtonMap.STICK_RIGHTY);

        

        this.driveControl.tankDrive(RightStickY, LeftStickY);
    }

    public void timeTest()
    {
        if(gamePad.getButton(ButtonMap.A))
        {
            this.TimedDrive();
        }
    }

    public void TimedDrive()
    {
        if(matchTime.get() >= 2)
        {
            driveControl.basicDrive(0);

        }
        else
        {
            driveControl.basicDrive(.5);
        }

         // if(matchTime.hasElapsed(runtime + CurrentTime))// while the current time is less than the time the function needs to run for plus the start time.
            // {
            //     this.basicDrive(0);
            //     isDriveOn = false;
            // }
            // else
            // {
            //     if(isDriveOn = false)
            //     {
            //         this.basicDrive(speed);
            //         isDriveOn = true;

            //     }
            // }
    }

}
