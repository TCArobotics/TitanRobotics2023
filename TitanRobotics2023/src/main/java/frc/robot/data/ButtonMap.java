package frc.robot.data;

// This Enum stores values for all gamepad related inputs
// -1 indicates a imaginary or placeholder value
//Reference a value like so: ButtonMap.A.value

public enum ButtonMap
{
    Flight_BUTTON_1(1),

    Xbox_A(1),
    Xbox_B(2),
    Xbox_X(3),
    Xbox_Y(4),
    Xbox_LB(5),
    Xbox_RB(6),
    Xbox_BACK(7),
    Xbox_START(8),
    Xbox_LSTICK(9),
    Xbox_RSTICK(10),

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