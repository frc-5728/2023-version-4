package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Hatch;
import frc.robot.subsystems.Elevator;
import frc.robot.commands.VisionUtil.MoveToUpperBlock;
import frc.robot.subsystems.*;
import frc.robot.commands.VisionUtil.Normalize;
import frc.robot.commands.arm.*;


public class DropBlockUpperMiddle extends SequentialCommandGroup {
    
    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;
    private final Elevator elevator;
    private final Hatch hatch;
    private final Arm arm;
    private final Drawer drawer;
    private final int idBlock = 2;

    public DropBlockUpperMiddle(AprilTagSubsystem atSubsystem, DriveTrain driveTrain, Hatch hatch, Elevator elevator, Arm arm, Drawer drawer) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;
        this.hatch = hatch;
        this.elevator = elevator;
        this.arm = arm;
        this.drawer = drawer;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new MoveToUpperBlock(atSubsystem, driveTrain, hatch, elevator, drawer, arm, idBlock),
                new TimedHatchToggle(hatch, 0.5),
                new Normalize(hatch, driveTrain, elevator, drawer, arm)     

            );
            // parallel command to raise elevator arm
           
            // sequential command to release cone

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);
        addRequirements(driveTrain);
        addRequirements(hatch);
        addRequirements(elevator);
        addRequirements(drawer);
        addRequirements(arm);

    }

}