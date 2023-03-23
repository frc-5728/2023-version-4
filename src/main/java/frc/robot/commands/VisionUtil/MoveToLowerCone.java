package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.ReflectiveTapeSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.arm.MoveElevator;

public class MoveToLowerCone extends ParallelCommandGroup {
    
    private final ReflectiveTapeSubsystem rtSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;

    private final double displacementCone = 0.2;
    private final double elevatorHeight = 4;

    public MoveToLowerCone(ReflectiveTapeSubsystem rtSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator) {

        this.rtSubsystem = rtSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;

        if (rtSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveElevator(elevator, elevatorHeight),
                // Move Drawer in all the way
                new TurnMove(rtSubsystem, driveTrain, this.displacementCone)

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(rtSubsystem);
        addRequirements(elevator);
        addRequirements(hatch);
        addRequirements(driveTrain);

    }

}
