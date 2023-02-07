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

    Flight_STICK_X(-1),
    Flight_STICK_Y(-2),
    Flight_STICK_Z(-3),
    Flight_SLIDER(-4),

    Xbox_LEFT_STICK_X(-5),
    Xbox_LEFT_STICK_Y(-6),
    Xbox_RIGHT_STICK_X(-7),
    Xbox_RIGHT_STICK_Y(-8);

    

    public int value;
    private ButtonMap(int _value)
    {
        this.value = _value;
    }
}