package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.arm.MoveElevator;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.*;

public class MoveToLowerBlock extends ParallelCommandGroup {
    
    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;

    private final double displacementCone = 0.2;
    private final double elevatorHeight = 4;
    private final int idBlock = 5;

    private final Drawer drawer;
    private final int drawerTime = 4;
    private final int drawerSpeed = -1;

    public MoveToLowerBlock(AprilTagSubsystem atSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator, Drawer drawer) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;
        this.drawer = drawer;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveElevator(elevator, elevatorHeight),
                new DrawerTimed(drawer, drawerTime, drawerSpeed),
                new TurnMove(atSubsystem, driveTrain, this.displacementCone, this.idBlock)

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);
        addRequirements(elevator);
        addRequirements(hatch);
        addRequirements(driveTrain);

    }

}