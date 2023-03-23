package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.arm.MoveElevator;

public class MoveToLowerBlock extends ParallelCommandGroup {
    
    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;

    private final double displacementCone = 0.2;
    private final double elevatorHeight = 4;
    private final int idBlock = 5;

    public MoveToLowerBlock(AprilTagSubsystem atSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveElevator(elevator, elevatorHeight),
                // Move Drawer in all the way
                new TurnMove(atSubsystem, driveTrain, this.displacementCone, this.idBlock)

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);

    }

}