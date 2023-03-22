package frc.robot.data;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.data.PortMap;




public class GyroDrive
{
    static final double kOffGyroThresholdBalanceAngle = 4;
    static final double kOnGyroThresholdBalanceAngle = 4;
    boolean autoBalanceXMode;
    boolean autoBalanceYMode;  
    double yAxisRate;
    double xAxisRate;
    double pitchAngleDegrees = 0;
    double rollAngleDegrees;
    double stateOfBalance = yAxisRate;
    AHRS ahrs;

    public GyroDrive(AHRS ahrs)
    {
        this.ahrs = ahrs;
    }
    
    

    public double autoBalance()
    {
        //X is forward/backward

        pitchAngleDegrees = ahrs.getPitch();
        //rollAngleDegrees = ahrs.getPitch();
        //System.out.println(pitchAngleDegrees);
        if (!autoBalanceXMode && (Math.abs(pitchAngleDegrees) >= Math.abs(kOffGyroThresholdBalanceAngle))) {
            autoBalanceXMode = true;
        } else if (autoBalanceXMode && (Math.abs(pitchAngleDegrees) <= Math.abs(kOnGyroThresholdBalanceAngle))) {
            autoBalanceXMode = false;
        }
       /*  if (!autoBalanceYMode && (Math.abs(pitchAngleDegrees) >= Math.abs(kOffGyroThresholdBalanceAngle))) {
            autoBalanceYMode = true;
        } else if (autoBalanceYMode && (Math.abs(pitchAngleDegrees) <= Math.abs(kOnGyroThresholdBalanceAngle))) {
            autoBalanceYMode = false;
        }*/

        if (autoBalanceXMode) {
            double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
            xAxisRate = Math.sin(pitchAngleRadians) * 1;
        }
        else 
        {
        xAxisRate = 0;
        }
        /*if (autoBalanceYMode) {
            double rollAngleRadians = rollAngleDegrees * (Math.PI / 180.0);
            yAxisRate = Math.sin(rollAngleRadians) * -1;
            
        }
        */
        
        return xAxisRate;


    }

    //public double ChargeBalance(PortMap navAxis)
    //{


   // }

}
