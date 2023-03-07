package frc.robot.controller;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.data.ButtonMap;
import frc.robot.data.PortMap;
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

//This class process all gamepad inputs into a form usable by TeleopControl


public class XboxGamePad
{
    private XboxController xboxController;
    
    private HashMap<ButtonMap, Double> buttons;
    private double debouncePeriod = 0.1; //The time before a button is allowed to be pressed again in seconds
    
    public XboxGamePad()
    {
        this.xboxController = new XboxController(PortMap.GAMEPAD_Xbox.portNumber);
        this.buttons = new HashMap<ButtonMap, Double>();
        init();
    }
    
    private void init()
    {
        for(ButtonMap i : ButtonMap.values())
        {
            buttons.put(i, Timer.getFPGATimestamp());
        }
    }

    public boolean getButtonXboxPressed(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; has debounce (time before button can output true again)
    {
    
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - buttons.get(buttonName) > this.debouncePeriod)
        {
            buttons.replace(buttonName, currentTime);
            
            return xboxController.getRawButton(buttonName.value);
        }
        else
        {
            return false;
        }
    }
    
    public boolean getButtonXboxPressedDebounceOff(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; does not have debounce (allows for motors to be triggered by press and hold until button is released)
    {
        return xboxController.getRawButton(buttonName.value);
    }

    public double getStick(ButtonMap stickAxis) //Input the ButtonMap name and axis and receive its value, double between -1 and 1
    {
        switch(stickAxis)
        {
            case Xbox_LEFT_STICK_X:
                return xboxController.getRawAxis(0);
            case Xbox_LEFT_STICK_Y:
                return xboxController.getRawAxis(1);
            case Xbox_RIGHT_STICK_X:
                return xboxController.getRawAxis(4); 
            case Xbox_RIGHT_STICK_Y:
                return xboxController.getRawAxis(5); 
            default:
                return 0;
                
        }
    }
}
