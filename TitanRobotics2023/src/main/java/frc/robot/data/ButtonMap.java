package frc.robot.data;

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
    RSTICK(9),
    LSTICK(10),

    STICK_LEFTX(11),
    STICK_LEFTY(12),
    STICK_RIGHTX(13),
    STICK_RIGHTY(14);

    public int value;
    private ButtonMap(int _value)
    {
        this.value = _value;
    }
}
