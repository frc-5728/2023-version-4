package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.VisionUtil.MoveToLowerBlock;

public class DropBlockLower extends SequentialCommandGroup {
    
    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;

    public DropBlockLower(AprilTagSubsystem atSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveToLowerBlock(atSubsystem, driveTrain, hatch, elevator),
                Commands.run(() -> hatch.set(true), hatch)

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);
        addRequirements(driveTrain);
        addRequirements(hatch);
        addRequirements(elevator);

    }

}