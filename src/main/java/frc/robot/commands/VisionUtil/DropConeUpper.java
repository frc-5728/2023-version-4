package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.ReflectiveTapeSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.VisionUtil.MoveToUpperCone;

public class DropConeUpper extends SequentialCommandGroup {
    
    private final ReflectiveTapeSubsystem rtSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;

    public DropConeUpper(ReflectiveTapeSubsystem rtSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator) {

        this.rtSubsystem = rtSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;

        if (rtSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveToUpperCone(rtSubsystem, driveTrain, hatch, elevator),
                Commands.run(() -> hatch.set(true), hatch)

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(rtSubsystem);

    }

}
