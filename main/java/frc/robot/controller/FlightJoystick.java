package frc.robot.controller;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.data.ButtonMap;
import java.util.HashMap;
import edu.wpi.first.wpilibj.Timer;

public class FlightJoystick {
    Joystick flightJoystick = new Joystick(1);
    private double debouncePeriod = 0.1; //The time before a button is allowed to be pressed again in seconds
    private HashMap<ButtonMap, Double> buttons;

    public FlightJoystick()
    {
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
            return flightJoystick.getRawButton(buttonName.value);
        }
        else
        {
            return false;
        }
    }

    public boolean getButtonFlightPressedDebounceOff(ButtonMap buttonName) //Input the ButtonMap name and receive if button is pressed, boolean true or false; does not have debounce (allows for motors to be triggered by press and hold until button is released)
    {
        return flightJoystick.getRawButton(buttonName.value);
    }

    public double getStick(ButtonMap stickAxis) //Input the ButtonMap name and axis and receive its value, double between -1 and 1
    {
        switch(stickAxis)
        {
            case FlightSTICKX:
                return flightJoystick.getRawAxis(0); //gives the value from -1 to 1 for the specified axis (axis shown in driverstation for controller when controller is plugged in)
            case FlightSTICKY:
                return flightJoystick.getRawAxis(1);
            case FlightSTICKZ:
                return flightJoystick.getRawAxis(2);
            case FlightSLIDER:
                return flightJoystick.getRawAxis(3);
            default:
                return 0;
                
        }
    }
}
