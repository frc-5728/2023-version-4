package frc.robot.commands.VisionUtil;

import frc.robot.subsystems.Vision.ReflectiveTapeSubsystem;
import frc.robot.subsystems.Vision.AprilTagSubsystem;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.TurnLeft;
import frc.robot.commands.drive.MoveToDistance;

public class TurnMove extends SequentialCommandGroup {

    public TurnMove(ReflectiveTapeSubsystem rtSubsystem, DriveTrain driveTrain, double displacement) {

        if (rtSubsystem.hasTarget) {

            addCommands(

                new TurnLeft(driveTrain, Units.radiansToDegrees(rtSubsystem.getYaw())),
                new MoveToDistance(driveTrain, rtSubsystem.getRange() - displacement)

            );

            // Change values to correct units

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(rtSubsystem);

    }

    public TurnMove(AprilTagSubsystem atSubsystem, DriveTrain driveTrain, double displacement, int id) {

        if (atSubsystem.hasTarget) {

            int idIndex = -1;

            for (int i = 0; i < atSubsystem.targetIDs.size(); i++) {

                if ( id == atSubsystem.targetIDs.get(i) ) {
                    idIndex = i;
                }

            }

            if (idIndex != -1) {

                addCommands(

                    new TurnLeft(driveTrain, Units.radiansToDegrees(atSubsystem.getYaw(idIndex))),
                    new MoveToDistance(driveTrain, atSubsystem.getRange(idIndex) - displacement)

                );

            }
            else {
                System.out.println("No target");
            }
            
            // Change values to correct units

        }
        else {
            System.out.println("No target");
        }
        
        addRequirements(atSubsystem);
        addRequirements(driveTrain);

    }

}