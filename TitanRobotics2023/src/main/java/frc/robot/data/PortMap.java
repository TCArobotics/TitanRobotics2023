package frc.robot.data;

public enum PortMap {
    GAMEPADXBOX(0), //swap with GAMEPADFLIGHT port, 1, if driverstation port is swapped
    GAMEPADFLIGHT(1), //swap with GAMEPADXBOX port, 0, if driverstation port is swapped
    FRONTRIGHT(1), //And_You is 3, Menoteus is 1
    REARRIGHT(0),//And_You is 0, Menoteus is 0
    FRONTLEFT(3),//And_You is 2, Menoteus is 3
    REARLEFT(2),//And_You is 1, Menoteus in 2
    ARMPIVOTMOTOR(4),
    armCANID(21), //Id is 10 for And_You; Id is 21 for Ya_Like_Jazz
    clawCANID(6); //motor does not exist on And_You; Id2 is 6 for Ya_Like_Jazz


    public int portNumber;
    private PortMap(int portValue)
    {
        this.portNumber = portValue;
    }
}
