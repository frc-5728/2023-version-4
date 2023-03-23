package frc.robot.commands.drive;

import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class MoveToDistance extends CommandBase {
    private final DriveTrain driveTrain;
    private final double distance;

    private final double kP = 0.0003;
    private final double kI = 0;
    private final double kD = 0;
    private final double kFF = 0;
    
    private final ShuffleboardTab tab = Shuffleboard.getTab("Drive");
    private final GenericEntry pEntry = tab.add(getName() + " P", kP).getEntry();
    private final GenericEntry iEntry = tab.add(getName() + " I", kI).getEntry();
    private final GenericEntry dEntry = tab.add(getName() + " D", kD).getEntry();
    private final GenericEntry ffEntry = tab.add(getName() + " FF", kFF).getEntry();

    public MoveToDistance(DriveTrain driveTrain, double distance) {
        this.driveTrain = driveTrain;
        this.distance = distance;

        driveTrain.leftEncoder.setPosition(0);
        driveTrain.rightEncoder.setPosition(0);

        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        driveTrain.leftPID.setP(pEntry.getDouble(kP));
        driveTrain.leftPID.setI(iEntry.getDouble(kI));
        driveTrain.leftPID.setD(dEntry.getDouble(kD));
        driveTrain.leftPID.setFF(ffEntry.getDouble(kFF));

        driveTrain.leftPID.setReference(distance, ControlType.kPosition);

        driveTrain.rightPID.setP(pEntry.getDouble(kP));
        driveTrain.rightPID.setI(iEntry.getDouble(kI));
        driveTrain.rightPID.setD(dEntry.getDouble(kD));
        driveTrain.rightPID.setFF(ffEntry.getDouble(kFF));

        driveTrain.rightPID.setReference(distance, ControlType.kPosition);
    }
}
