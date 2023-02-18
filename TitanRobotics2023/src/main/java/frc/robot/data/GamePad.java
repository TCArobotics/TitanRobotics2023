package frc.robot.data;

import edu.wpi.first.wpilibj.XboxController;

//This class process all gamepad inputs into a form usable by TeleopControl


public class GamePad
{
    private XboxController xboxController;
    public GamePad()
    {
        this.xboxController = new XboxController(PortMap.GAMEPAD.portNumber);
    }

    public double getStick(ButtonMap stickAxis) //Input the ButtonMap name and axis and receive its value, double between -1 and 1
    {
        switch(stickAxis)
        {
            case STICK_LEFTX:
                return xboxController.getRawAxis(0);
            case STICK_LEFTY:
                return xboxController.getRawAxis(1);
            case STICK_RIGHTX:
                return xboxController.getRawAxis(4);
            case STICK_RIGHTY:
                return xboxController.getRawAxis(5);  
            default:
                return 0;
                
        }
    }
}