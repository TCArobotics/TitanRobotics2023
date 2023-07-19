

import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import frc.robot.data.PortMap;

import java.util.concurrent.CountedCompleter;

import Math;

public class SwerveDrive {

    tuple vector = {0,0}; 



    public void SwerveMove(tuple velocity, tuple angularVelocity)
    {
        
    }

    public void SwerveModule(tuple velocity, double angularVelocity, tuple ControlVector)
    {
        angleVector;
        targetVector;

        angleVector[0] =  ControlVector[0]*angularVelocity;
        angleVector[1] = ControlVector[1]*angularVelocity;
    }
}

