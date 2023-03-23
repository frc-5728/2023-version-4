package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.drive.MoveToDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.*;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.Drawer;
import frc.robot.commands.arm.DrawerTimed;

import frc.robot.commands.drive.MoveToDistance;

public class MoveBackAuto extends ParallelCommandGroup {

    public final Hatch hatch;
    public final DriveTrain driveTrain;
    public final double displacement;
    public final double elevatorPos = 1;
    public final Elevator elevator;
    private final Drawer drawer;
    private final int drawerTime = 4;
    private final int drawerSpeed = -1;
    private final Arm arm;
    private final int armSpeed = -1;
    
    public MoveBackAuto(Hatch hatch, DriveTrain driveTrain, Elevator elevator, Drawer drawer, double displacement, Arm arm) {

        this.hatch = hatch;
        this.driveTrain = driveTrain;
        this.displacement = displacement;
        this.elevator = elevator;
        this.drawer = drawer;
        this.arm = arm;

        addCommands(
                
            Commands.run(() -> hatch.set(false), hatch),
            new TimedClaw(arm, armSpeed),
            new DrawerTimed(drawer, drawerTime, drawerSpeed),
            new MoveToDistance(driveTrain, displacement),
            new MoveElevator(elevator, elevatorPos)
            // drawer

        );

        addRequirements(hatch);
        addRequirements(driveTrain);
        addRequirements(elevator);
        addRequirements(drawer);

    }
    
}
