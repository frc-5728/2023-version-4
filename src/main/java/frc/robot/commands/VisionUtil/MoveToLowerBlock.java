package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.*;

public class MoveToLowerBlock extends ParallelCommandGroup {

    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;
    private final Arm arm;

    private final double displacementCone = 0.2;
    private final int elevatorHeight = 5;
    private final int idBlock;

    private final Drawer drawer;
    private final float clawTime = 1f;
    private final int drawerSpeed = -1;
    private final int armSpeed = 1;

    public MoveToLowerBlock(AprilTagSubsystem atSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator,
            Drawer drawer, Arm arm, int idBlock) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;
        this.drawer = drawer;
        this.arm = arm;
        this.idBlock = idBlock;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                new DrawerTimed(drawer, drawerSpeed),
                    new TimedClaw(arm, armSpeed, clawTime),
                    new TurnMove(atSubsystem, driveTrain, this.displacementCone, this.idBlock)

            );
            // parallel command to raise elevator arm

            // sequential command to release cone

        } else {
            System.out.println("No target");
        }

        addRequirements(atSubsystem);
        addRequirements(elevator);
        addRequirements(hatch);
        addRequirements(driveTrain);
        addRequirements(arm);
        addRequirements(drawer);

    }

}