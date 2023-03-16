package frc.robot.data;

public enum PortMap {
    GAMEPAD_Xbox(1), //swap with GAMEPAD_Flight port, 1, if driverstation port is swapped
    GAMEPAD_Flight(0), //swap with GAMEPAD_Xbox port, 0, if driverstation port is swapped

    FRONTRIGHT(1), //And_You is 3, Ya_Like_Jazz is 1
    REARRIGHT(0),//And_You is 0, Ya_Like_Jazz is 0
    FRONTLEFT(3),//And_You is 2, Ya_Like_Jazz is 3
    REARLEFT(2),//And_You is 1, Ya_Like_Jazz is 2
    ARMPIVOTMOTOR(4),//Does not exist for And_You

    clawCANID(6),
    retractorCANID(27);


    public int portNumber;
    private PortMap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
