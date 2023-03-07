package frc.robot.data;

public enum PortMap {
    GAMEPAD_Xbox(0), //swap with GAMEPAD_Flight port, 1, if driverstation port is swapped
    GAMEPAD_Flight(1), //swap with GAMEPAD_Xbox port, 0, if driverstation port is swapped

    FRONTRIGHT(1), //And_You is 3
    REARRIGHT(0),//And_You is 0
    FRONTLEFT(3),//And_You is 2
    REARLEFT(2),//And_You is (1?)
    ARMPIVOTMOTOR(4),//Does not exist for And_You
    armCANID(6), //Id is 10 for And_You; Id is 6 for Ya_Like_Jazz
    clawCANID(21); //motor does not exist on And_You; Id2 is 21 for Ya_Like_Jazz

    public int portNumber;
    private PortMap(int portValue)
    {
        this.portNumber = portValue;
    }
}
