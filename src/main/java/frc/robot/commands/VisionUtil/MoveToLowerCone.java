package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.ReflectiveTapeSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.*;

public class MoveToLowerCone extends ParallelCommandGroup {

    private final ReflectiveTapeSubsystem rtSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;
    private final Arm arm;

    private final Drawer drawer;
    private final float clawTime = 1f;
    private final int drawerSpeed = -1;
    private final int armSpeed = 1;

    private final double displacementCone = 1;
    private final int elevatorHeight = 5;

    public MoveToLowerCone(ReflectiveTapeSubsystem rtSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator,
            Drawer drawer, Arm arm) {

        this.rtSubsystem = rtSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;
        this.drawer = drawer;
        this.arm = arm;

        if (rtSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(

                    new DrawerTimed(drawer, drawerSpeed),
                    new TimedClaw(arm, armSpeed, clawTime),
                    new TurnMove(rtSubsystem, driveTrain, this.displacementCone)

            );
            // parallel command to raise elevator arm

            // sequential command to release cone

        } else {
            System.out.println("No target");
        }

        addRequirements(rtSubsystem);
        addRequirements(elevator);
        addRequirements(hatch);
        addRequirements(driveTrain);
        addRequirements(arm);
        addRequirements(drawer);

    }

}
