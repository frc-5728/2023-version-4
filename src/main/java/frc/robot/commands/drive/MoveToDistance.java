package frc.robot.commands.drive;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveToDistance extends CommandBase {
    private final DriveTrain driveTrain;
    private final double distance;

    private final ShuffleboardTab tab = Shuffleboard.getTab("Drive");
    private final GenericEntry pEntry = tab.add(getName() + " P", 0).getEntry();
    private final GenericEntry iEntry = tab.add(getName() + " I", 0).getEntry();
    private final GenericEntry dEntry = tab.add(getName() + " D", 0).getEntry();
    private final GenericEntry ffEntry = tab.add(getName() + " FF", 0).getEntry();

    public MoveToDistance(DriveTrain driveTrain) {
        this(driveTrain, 0);
    }

    public MoveToDistance(DriveTrain driveTrain, double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;

        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.leftPID.setP(pEntry.getDouble(0.0003));
        driveTrain.leftPID.setI(iEntry.getDouble(0.0000001));
        driveTrain.leftPID.setD(dEntry.getDouble(0.0001));
        driveTrain.leftPID.setFF(ffEntry.getDouble(0.000156));

        driveTrain.leftPID.setReference(distance, ControlType.kPosition);

        driveTrain.rightPID.setP(pEntry.getDouble(0.0003));
        driveTrain.rightPID.setI(iEntry.getDouble(0.0000001));
        driveTrain.rightPID.setD(dEntry.getDouble(0.0001));
        driveTrain.rightPID.setFF(ffEntry.getDouble(0.000156));

        driveTrain.rightPID.setReference(distance, ControlType.kPosition);
    }
}
