package frc.robot.data;

import frc.robot.actors.DriveControl;
import frc.robot.actors.Arm;
import frc.robot.actors.Claw;
import frc.robot.controller.FlightJoystick;
import frc.robot.controller.XboxGamePad;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.AutonomousControl.AutonomousControl;

public class Dashboard
{
    public Dashboard()
    {
       
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

    public void executeDashboard()
    {
        this.DashboardEncoderPositions();
    }
}