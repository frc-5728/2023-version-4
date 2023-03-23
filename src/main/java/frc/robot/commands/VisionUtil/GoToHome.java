package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class GoToHome extends SequentialCommandGroup {
    
    private final AprilTagSubsystem atSubsystem;
    private final DriveTrain driveTrain;

    private final double displacement = 0.2;
    private final int idHome = 3;

    public GoToHome(AprilTagSubsystem atSubsystem, DriveTrain driveTrain) {

        this.atSubsystem = atSubsystem;
        this.driveTrain = driveTrain;

        if (atSubsystem.hasTarget) {

            // Change values to correct units

            addCommands(
                
                new TurnMove(atSubsystem, driveTrain, this.displacement, this.idHome)

            );
           
        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);

    }

}