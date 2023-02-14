package frc.robot.data;

public enum PortMap {
    GAMEPAD_Xbox(0), //swap with GAMEPAD_Flight port, 1, if driverstation port is swapped
    GAMEPAD_Flight(1), //swap with GAMEPAD_Xbox port, 0, if driverstation port is swapped
    FRONTRIGHT(3),
    REARRIGHT(0),
    TURNTABLE(-1),
    SHOOTER(-1),
    FRONTLEFT(2),
    REARLEFT(1),
    SIDEMOTOR(9),
    ALTERNATEMOTOR(8);

    public int portNumber;
    private PortMap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
