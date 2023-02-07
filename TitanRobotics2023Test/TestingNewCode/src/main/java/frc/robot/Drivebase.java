package frc.robot;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class Drivebase 
{
    private final MotorController motor_frontleft;
    private final MotorController motor_frontright;
    private final MotorController motor_backright;
    private final MotorController motor_backleft;

    private static MecanumDrive robotDrive;
    public Drivebase()
    {
        motor_frontleft = new PWMVictorSPX(0);
        motor_frontright = new PWMVictorSPX(0);
        motor_backleft = new PWMVictorSPX(0 );
        motor_backright = new PWMVictorSPX(0);
        robotDrive = new MecanumDrive(motor_frontleft, motor_backleft, motor_frontright, motor_backright);
    }

    public static void execute()
    {
        mecanumDrive(0, 0, 0);
    }

    public static void mecanumDrive(double yspeed, double xspeed, double zrotation)
    {
        robotDrive.driveCartesian(yspeed, xspeed, zrotation);
    }
}
