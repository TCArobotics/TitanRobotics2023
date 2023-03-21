package frc.robot.actors;

import com.kauailabs.navx.frc.AHRS;
import frc.robot.data.PortMap;
import frc.robot.actors.DriveControl;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class FixDrift 
{
	public double driftError;
	public double gyroAngle;
	public double driftIntegral;
	public double driftDerivative;
	public double driftCorrection;
	public double driftThreshold;

	public static double kP = 1.0; //proportional constant
	public static double kD = 0.0; //derivative constant
	public static double kI = 0.0; //integral constant

	public FixDrift(double desiredAngle, double power) 
	{
		//gyroAngle = gyro;
		driftError = desiredAngle - gyroAngle;
		//derivative = angular velocity
		driftIntegral += driftError;
		driftCorrection = (kP * driftError) + (kI * driftIntegral) + (kD * driftDerivative); //PID controller

		if(driftError <= driftThreshold)
		{
			driftIntegral = 0.0;
		}

		

	}
}