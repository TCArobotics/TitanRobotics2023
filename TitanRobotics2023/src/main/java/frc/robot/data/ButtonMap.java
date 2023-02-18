package frc.robot.data;

// This Enum stores values for all gamepad related inputs
// -1 indicates a imaginary or placeholder value
//Reference a value like so: ButtonMap.A.value

public enum ButtonMap
{
    A(1),
    B(2),
    X(3),
    Y(4),
    LB(5),
    RB(6),
    BACK(7),
    START(8),
    LSTICK(9),
    RSTICK(10),

    STICK_LEFTX(-1),
    STICK_LEFTY(-2),
    STICK_RIGHTX(-3),
    STICK_RIGHTY(-4);

    public int value;
    private ButtonMap(int _value)
    {
        this.value = _value;
    }
}