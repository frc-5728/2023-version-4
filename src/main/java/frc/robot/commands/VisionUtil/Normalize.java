package frc.robot.commands.VisionUtil;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.drive.MoveToDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.*;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.Drawer;
import frc.robot.commands.drive.MoveToDistance;

public class Normalize extends ParallelCommandGroup {

    public final Hatch hatch;
    public final DriveTrain driveTrain;
    public final int elevatorPos = 1;
    public final Elevator elevator;
    private final Drawer drawer;
    private final float clawTime = 1.5f;
    private final int drawerSpeed = -1;
    private final Arm arm;
    private final int armSpeed = -1;

    public Normalize(Hatch hatch, DriveTrain driveTrain, Elevator elevator, Drawer drawer, Arm arm) {

        this.hatch = hatch;
        this.driveTrain = driveTrain;
        this.elevator = elevator;
        this.drawer = drawer;
        this.arm = arm;

        addCommands(
                new HatchClose(hatch),
                new TimedClaw(arm, armSpeed, clawTime),
                new DrawerTimed(drawer, drawerSpeed),
                new ElevatorTimedDown(elevator, 0.9f)

        );

        addRequirements(hatch);
        addRequirements(driveTrain);
        addRequirements(elevator);
        addRequirements(drawer);
        addRequirements(arm);

    }

}
