package frc.robot;
import edu.wpi.first.apriltag.AprilTagDetection;

public class AprilTag {
    /*
    private AprilTagDetection aprilTagDetection;
    public AprilTag(){
        public aprilTagDetection AprilTagDetection(AprilTag Classic 26h5 (30 tags), 1,)
    }
    */
    public void GetId(){
        double tagid = LimelightHelpers.getFiducialID("");
        System.out.println(tagid);
    }
}
