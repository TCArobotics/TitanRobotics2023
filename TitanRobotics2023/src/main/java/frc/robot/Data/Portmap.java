package frc.robot.data;

public enum Portmap 
{
    GAMEPAD(9),
    FRONTRIGHT(3),
    FRONTLEFT(2),
    BACKRIGHT(0),
    BACKLEFT(1),
    CLAW(9),
    ARM(9);
    
    public int portNumber;
    private Portmap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
