package frc.robot;

public enum PortMap 
{
FrontRight(0),
BackRight(1),
FrontLeft(3),
BackLeft(4);

public int portNumber;
private PortMap(int _portNumber)
{
    this.portNumber = _portNumber;
}



}
