package frc.robot.data;

import frc.robot.actors.DriveControl;
import frc.robot.actors.Manipulator;
import frc.robot.data.GamePad;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard
{
    private Manipulator manipulator;

    public Dashboard()
    {
        manipulator = new Manipulator();
    }
    public void execute() 
    {
        SmartDashboard.putNumber("Claw Encoder Position", manipulator.clawEncoder.getPosition());
        SmartDashboard.putNumber("Claw Encoder Velocity", manipulator.clawEncoder.getVelocity());
        SmartDashboard.putNumber("Retract Encoder Position", manipulator.retractEncoder.getPosition());
        SmartDashboard.putNumber("Retract Encoder Velocity", manipulator.retractEncoder.getVelocity());
    }
}