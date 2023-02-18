package frc.robot.data;

public enum PortMap {
    GAMEPAD(0),
    FRONTRIGHT(3),
    REARRIGHT(0),
    FRONTLEFT(2),
    REARLEFT(1);

    public int portNumber;
    private PortMap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
