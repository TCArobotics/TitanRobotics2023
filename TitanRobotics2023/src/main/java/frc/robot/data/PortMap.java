package frc.robot.data;

public enum PortMap {
    GAMEPAD_Xbox(1), //swap with GAMEPAD_Flight port, 1, if driverstation port is swapped
    GAMEPAD_Flight(0), //swap with GAMEPAD_Xbox port, 0, if driverstation port is swapped

    FRONTRIGHT(1), //and you is 3
    REARRIGHT(0),//0
    FRONTLEFT(3),//2
    REARLEFT(2),//
    ARMPIVOTMOTOR(4);

    public int portNumber;
    private PortMap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
