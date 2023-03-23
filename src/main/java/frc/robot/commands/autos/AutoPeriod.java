package frc.robot.commands.autos;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.autos.*;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.Hatch;

public class AutoPeriod extends SequentialCommandGroup {

    private final DriveTrain driveTrain;
    private final Hatch hatch;

    private final double dist1 = -8.0;
    private final double dist2 = 8.0;

    public AutoPeriod(DriveTrain driveTrain, Hatch hatch) {

        this.driveTrain = driveTrain;
        this.hatch = hatch;

        addCommands(
              
            // move drawer out
            Commands.run(() -> hatch.set(true), hatch),
            new MoveToDistance(driveTrain, dist1),
            new MoveToDistance(driveTrain, dist2),
            new AutoBalance(driveTrain)

        );
        // parallel command to raise elevator arm
        // sequential command to release cone

        addRequirements(driveTrain);
        addRequirements(hatch);

    }
        
}