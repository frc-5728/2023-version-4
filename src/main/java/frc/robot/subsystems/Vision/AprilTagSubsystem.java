package frc.robot.subsystems.Vision;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.math.util.Units;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.common.hardware.VisionLEDMode;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AprilTagSubsystem extends SubsystemBase {

    // Constants such as camera and target height stored.
    final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(28);
    final double TARGET_HEIGHT_METERS = Units.inchesToMeters(105);
    // Angle between horizontal and the camera.
    final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(29.7);// 29.5

    private double yawVal = 0;
    private double pitchVal = 0;
    private double areaVal = 0;
    public boolean hasTarget = false;
    private int targetID;

    PhotonCamera camera = new PhotonCamera("photonvision");

    // Code in periodic should only be to get camera data, checking position and
    // moving should be done every execute
    // Get data from camera
    // To put camera to screen, there is an option in smart dashboard to add
    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        var result = this.camera.getLatestResult();
        if (result.hasTargets()) {
            this.yawVal = result.getBestTarget().getYaw();
            this.pitchVal = result.getBestTarget().getPitch();
            this.areaVal = result.getBestTarget().getArea();
            this.hasTarget = true;
            this.targetID = result.getBestTarget().getFiducialId();

            SmartDashboard.putNumber("Yaw Value", yawVal);
            SmartDashboard.putNumber("Pitch Value", pitchVal);
            SmartDashboard.putNumber("Area Value", areaVal);
            SmartDashboard.putBoolean("Has a Target", hasTarget);
            SmartDashboard.putNumber("Target ID: ", targetID);

        } else {
            this.hasTarget = false;
        }
    }

    public double getRange() {
        double range = PhotonUtils.calculateDistanceToTargetMeters(
                CAMERA_HEIGHT_METERS,
                TARGET_HEIGHT_METERS,
                CAMERA_PITCH_RADIANS,
                Units.degreesToRadians(pitchVal)
        );

        double rangeInInches = Units.metersToInches(range);

        SmartDashboard.putNumber("Camera Distance", rangeInInches);

        return range;
        
    }

    public double getYaw() {

        return yawVal;

    }

}