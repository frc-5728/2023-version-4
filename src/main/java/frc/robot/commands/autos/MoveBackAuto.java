package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.drive.MoveToDistance;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.arm.*;
import frc.robot.subsystems.Drawer;

import frc.robot.commands.drive.MoveToDistance;

public class MoveBackAuto extends ParallelCommandGroup {

    public final Hatch hatch;
    public final DriveTrain driveTrain;
    public final double displacement;
    public final double elevatorPos = 1;
    public final Elevator elevator;
    
    public MoveBackAuto(Hatch hatch, DriveTrain driveTrain, Elevator elevator, double displacement) {

        this.hatch = hatch;
        this.driveTrain = driveTrain;
        this.displacement = displacement;
        this.elevator = elevator;

        addCommands(
                
            Commands.run(() -> hatch.set(false), hatch),
            new MoveToDistance(driveTrain, displacement),
            new MoveElevator(elevator, elevatorPos)
            // drawer

        );

        addRequirements(hatch);
        addRequirements(driveTrain);
        addRequirements(elevator);

    }
    
}
