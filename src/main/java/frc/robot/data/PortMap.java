package frc.robot.data;

public enum PortMap {
    GAMEPAD_Xbox(0), //swap with GAMEPAD_Flight port, 1, if driverstation port is swapped
    GAMEPAD_Flight(1), //swap with GAMEPAD_Xbox port, 0, if driverstation port is swapped

    FRONTRIGHT(1), //And_You is 3
    REARRIGHT(0),//And_You is 0
    FRONTLEFT(3),//And_You is 2
    REARLEFT(2),//And_You is (1?)
    ARMPIVOTMOTOR(4);//Does not exist for And_You

    public int portNumber;
    private PortMap(int _portNumber)
    {
        this.portNumber = _portNumber;
    }
}
