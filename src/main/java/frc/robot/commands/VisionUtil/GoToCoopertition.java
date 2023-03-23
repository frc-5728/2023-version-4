package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToCoopertition extends SequentialCommandGroup {
    
    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;

    private final double displacement = 0.2;
    private final int idCoop = 2;

    public GoToCoopertition(AprilTagSubsystem atSubsystem, DriveTrain driveTrain) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                // new TurnMove(atSubsystem, driveTrain, this.displacement, this.idCoop)

            );
           
        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);
        addRequirements(driveTrain);

    }

}