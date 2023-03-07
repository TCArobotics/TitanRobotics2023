package frc.robot.controller;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.data.ButtonMap;
import frc.robot.data.PortMap;

import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

//This class process all gamepad inputs into a form usable by TeleopControl


public class FlightGamePad
{
   
    private XboxController flightController;
    
    private HashMap<ButtonMap, Double> buttons;
    private double debouncePeriod = 0.1; //The time before a button is allowed to be pressed again in seconds
    public FlightGamePad()
    {
        this.flightController = new XboxController(PortMap.GAMEPAD_Flight.portNumber);
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
    
    public boolean getButtonFlightPressed(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; has debounce (time before button can output true again)
    {
        double currentTime = Timer.getFPGATimestamp();
        if(currentTime - buttons.get(buttonName) > this.debouncePeriod)
        {
            buttons.replace(buttonName, currentTime);
            return flightController.getRawButton(buttonName.value);
        }
        else
        {
            return false;
        }
    }

    
    public boolean getButtonFlightPressedDebounceOff(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; does not have debounce (allows for motors to be triggered by press and hold until button is released)
    {
        return flightController.getRawButton(buttonName.value);
    }

    public double getStick(ButtonMap stickAxis) //Input the ButtonMap name and axis and receive its value, double between -1 and 1
    {
        switch(stickAxis)
        {
            case Flight_STICK_X:
                return flightController.getRawAxis(0); //gives the value from -1 to 1 for the specified axis (axis shown in driverstation for controller when controller is plugged in)
            case Flight_STICK_Y:
                return flightController.getRawAxis(1);
            case Flight_STICK_Z:
                return flightController.getRawAxis(2);
            case Flight_SLIDER:
                return flightController.getRawAxis(3);
            default:
                return 0;
                
        }
    }
}