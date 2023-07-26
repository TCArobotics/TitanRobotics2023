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
	public double driftIntegral = 0.0;
	public double driftDerivative = 0.0;
	public double driftThreshold = 2.0;
	double lastError = 0.0;
	AHRS ahrs;

	public static double kP = 0.1; //proportional constant
	public static double kD = 0.01; //derivative constant
	public static double kI = 0.1; //integral constant

	public FixDrift(AHRS ahrs)
	{
		this.ahrs = ahrs;
	}

	public double DriftCorrectionEtc(double desiredAngle) 
	{
		gyroAngle = ahrs.getYaw();
	
		driftError = desiredAngle - gyroAngle;
		driftDerivative = driftError - lastError;
		lastError = driftError;
		driftIntegral += driftError;

		if(driftError <= driftThreshold)
		{
			driftIntegral = 0.0;
		}
		
		double turnRadians = ((kP * driftError) + (kI * driftIntegral) + (kD * driftDerivative)) * (Math.PI / 180.0);
		return Math.sin(turnRadians) * 0.5;
	}
}