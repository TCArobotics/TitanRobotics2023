package frc.robot.data;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.XboxController.Axis;
//import edu.wpi.first.wpilibj.GenericHID;      
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

//This class process all gamepad inputs into a form usable by TeleopControl


public class GamePad
{
    private XboxController xboxController;
    private XboxController flightController;
    private HashMap<ButtonMap, Double> buttons;
    private double debouncePeriod = 0.25; //The time before a button is allowed to be pressed again in seconds
    public GamePad()
    {
        this.xboxController = new XboxController(PortMap.GAMEPAD_Xbox.portNumber);
        this.flightController = new XboxController(PortMap.GAMEPAD_Flight.portNumber);
        this.buttons = new HashMap<ButtonMap, Double>();
        init();
    }

    /**
     * 
     */
    private void init()
    {
        for(ButtonMap i : ButtonMap.values())
        {
            buttons.put(i, Timer.getFPGATimestamp()); //Fill the dictionary with an item for each button with the current time
        }
    }

    public boolean getButton(ButtonMap button) //Input a ButtonMap button name and receive whether it is pressed (Debounced)
    {
        if(!xboxController.getRawButton(button.value))
        {
            return false;
        }
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - buttons.get(button) > this.debouncePeriod)
        {
            buttons.replace(button, currentTime);
            return true;
        }
        return false;
    }
    public double getStick(ButtonMap stickAxis) //Input the ButtonMap name and axis and receive its value, double between -1 and 1
    {
        switch(stickAxis)
        {
            case Flight_STICK_X:
                return flightController.getRawAxis(0); //gives the value from -1 to 1 for the specified axis (shown in driverstation for controller when controller is plugged in)
            case Flight_STICK_Y:
                return flightController.getRawAxis(1);
            case Flight_STICK_Z:
                return flightController.getRawAxis(2);
            case Flight_SLIDER:
                return flightController.getRawAxis(3);
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