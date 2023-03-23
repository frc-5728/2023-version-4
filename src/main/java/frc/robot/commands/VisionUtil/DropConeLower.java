package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.ReflectiveTapeSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.VisionUtil.MoveToLowerCone;
import frc.robot.subsystems.*;
import frc.robot.commands.VisionUtil.Normalize;

public class DropConeLower extends SequentialCommandGroup {
    
    private final ReflectiveTapeSubsystem rtSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;
    private final Arm arm;
    private final Drawer drawer;

    public DropConeLower(ReflectiveTapeSubsystem rtSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator, Arm arm, Drawer drawer) {

        this.rtSubsystem = rtSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;
        this.arm = arm;
        this.drawer = drawer;

        if (rtSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveToLowerCone(rtSubsystem, driveTrain, hatch, elevator, drawer, arm),
                Commands.run(() -> hatch.set(true), hatch),
                new Normalize(hatch, driveTrain, elevator, drawer, arm)     

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(rtSubsystem);
        addRequirements(driveTrain);
        addRequirements(hatch);
        addRequirements(elevator);
        addRequirements(drawer);
        addRequirements(arm);

    }

}
