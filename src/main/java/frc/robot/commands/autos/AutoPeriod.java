package frc.robot.commands.autos;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.autos.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Drawer;
import frc.robot.subsystems.*;

public class AutoPeriod extends SequentialCommandGroup {

    private final DriveTrain driveTrain;
    private final Hatch hatch;
    private final Elevator elevator;
    private final Drawer drawer;
    private final Arm arm;

    private final double dist1 = 4.2672;
    private final double dist2 = 1.2192;
    private final int drawerTime = 4;
    private final int drawerSpeed = 1;
    private final int armSpeed = 1;

    public AutoPeriod(DriveTrain driveTrain, Hatch hatch, Elevator elevator, Drawer drawer, Arm arm) {

        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;
        this.drawer = drawer;
        this.arm = arm;

        addCommands(
              
            // move drawer out
            new TimedClaw(arm, armSpeed),
            new DrawerTimed(drawer, drawerTime, drawerSpeed),
            Commands.run(() -> hatch.set(true), hatch),
            new MoveBackAuto(hatch, driveTrain, elevator, drawer, this.dist1, arm),
            new MoveToDistance(driveTrain, dist2),
            new AutoBalance(driveTrain)

        );
        // parallel command to raise elevator arm
        // sequential command to release cone

        addRequirements(driveTrain);
        addRequirements(hatch);
        addRequirements(arm);
        addRequirements(elevator);
        addRequirements(drawer);

    }
        
}