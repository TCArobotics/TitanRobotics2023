package frc.robot.data;

import frc.robot.actors.DriveControl;
import frc.robot.actors.Manipulator;
import frc.robot.data.GamePad;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.AutonomousControl.AutonomousControl;

public class Dashboard
{
    //private final Manipulator manipulator;
    
    public Dashboard()//Manipulator manipulator)
    {
        //this.manipulator = manipulator;
    }

    private void DashboardEncoderPositions()
    {
        //SmartDashboard.putNumber("Claw Encoder Position", manipulator.clawEncoder.getPosition());
        //SmartDashboard.putNumber("Claw Encoder Velocity", manipulator.clawEncoder.getVelocity());
        //SmartDashboard.putNumber("Retract Encoder Position", manipulator.retractEncoder.getPosition());
        //SmartDashboard.putNumber("Retract Encoder Velocity", manipulator.retractEncoder.getVelocity());
        SmartDashboard.putBoolean("Claw Closed", false);
        SmartDashboard.putBoolean("Claw Open", true);
    }

    public void execute()
    {
        this.DashboardEncoderPositions();
    }
}