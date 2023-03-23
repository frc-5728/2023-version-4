package frc.robot.subsystems.Vision;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.math.util.Units;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.*;
import java.util.*;

import java.util.function.Supplier;

public class AprilTagSubsystem extends SubsystemBase {

    // Constants such as camera and target height stored.
    final double CAMERA_HEIGHT_METERS = Units.inchesToMeters(68);
    final double TARGET_HEIGHT_METERS = Units.inchesToMeters(15);
    // Angle between horizontal and the camera.
    final double CAMERA_PITCH_RADIANS = Units.degreesToRadians(60);// 29.5

    List<PhotonTrackedTarget> targets;
    private List<Double> yawVals = new ArrayList<Double>();
    private List<Double> pitchVals = new ArrayList<Double>();
    private List<Double> areaVals = new ArrayList<Double>();
    public boolean hasTarget = false;
    public List<Integer> targetIDs = new ArrayList<Integer>();

    PhotonCamera camera = new PhotonCamera("aprilTagCam");

    // Code in periodic should only be to get camera data, checking position and
    // moving should be done every execute
    // Get data from camera
    // To put camera to screen, there is an option in smart dashboard to add
    @Override
    public void periodic() {

        // This method will be called once per scheduler run
        var result = this.camera.getLatestResult();

        if (result.hasTargets()) {

            this.targets.clear();
            this.yawVals.clear();
            this.pitchVals.clear();
            this.areaVals.clear();
            this.targetIDs.clear();

            this.targets = result.getTargets();

            for (int i = 0; i < targets.size(); i++) {

                this.yawVals.add(targets.get(i).getYaw());
                this.pitchVals.add(targets.get(i).getPitch());
                this.areaVals.add(targets.get(i).getArea());
                this.targetIDs.add(targets.get(i).getFiducialId());

            }

            this.hasTarget = true;

        } else {
            this.hasTarget = false;
        }
    }

    public double getRange(int targetID) {
        double range = PhotonUtils.calculateDistanceToTargetMeters(
                CAMERA_HEIGHT_METERS,
                TARGET_HEIGHT_METERS,
                CAMERA_PITCH_RADIANS,
                Units.degreesToRadians(this.pitchVals.get(targetID))
        );

        double rangeInInches = Units.metersToInches(range);

        SmartDashboard.putNumber("Camera Distance", rangeInInches);

        return range;
        
    }

    public double getYaw(int targetID) {

        return this.yawVals.get(targetID);

    }

}