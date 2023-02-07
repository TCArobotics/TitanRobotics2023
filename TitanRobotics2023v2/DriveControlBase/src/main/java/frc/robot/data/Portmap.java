package frc.robot.data;

public enum Portmap 
{
    GAMEPAD(0),
    FRONTRIGHT(3),
    FRONTLEFT(2),
    BACKRIGHT(0),
    BACKLEFT(1),
    CLAW(-1),
    ARM(-1);
    
    public int portNumber;
    private Portmap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
