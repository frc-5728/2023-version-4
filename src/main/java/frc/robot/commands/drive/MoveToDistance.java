// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MoveToDistance extends PIDCommand {
  private final double kP = 0.0003;
  private final double kI = 0;
  private final double kD = 0;

  /** Creates a new Move. */
  public MoveToDistance(DriveTrain driveTrain, double distance) {
    super(
        // The controller that the command will use
        new PIDController(0.06, 0.01, 0.1),
        // This should return the measurement
        () -> driveTrain.leftEncoder.getPosition(),
        // This should return the setpoint (can also be a constant)
        () -> distance + driveTrain.leftEncoder.getPosition(),
        // This uses the output
        output -> {
          // Use the output here
          driveTrain.setSpeed(MathUtil.clamp(output, -0.5, 0.5));
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveTrain);

    driveTrain.resetEncoders();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}


// package frc.robot.commands.drive;

// import com.revrobotics.CANSparkMax.ControlType;

// import edu.wpi.first.networktables.GenericEntry;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.DriveTrain;

// public class MoveToDistance extends CommandBase {
//     private final DriveTrain driveTrain;
//     private final double distance;

//     private final double kP = 0.0003;
//     private final double kI = 0;
//     private final double kD = 0;
//     private final double kFF = 0;

//     private final ShuffleboardTab tab = Shuffleboard.getTab("Drive");
//     private final GenericEntry pEntry = tab.add(getName() + " P", kP).getEntry();
//     private final GenericEntry iEntry = tab.add(getName() + " I", kI).getEntry();
//     private final GenericEntry dEntry = tab.add(getName() + " D", kD).getEntry();
//     private final GenericEntry ffEntry = tab.add(getName() + " FF", kFF).getEntry();

//     public MoveToDistance(DriveTrain driveTrain, double distance) {
//         this.driveTrain = driveTrain;
//         this.distance = distance;

//         driveTrain.leftEncoder.setPosition(0);
//         driveTrain.rightEncoder.setPosition(0);

//         addRequirements(driveTrain);
//     }

//     @Override
//     public void execute() {
//         driveTrain.leftPID.setP(pEntry.getDouble(kP));
//         driveTrain.leftPID.setI(iEntry.getDouble(kI));
//         driveTrain.leftPID.setD(dEntry.getDouble(kD));
//         driveTrain.leftPID.setFF(ffEntry.getDouble(kFF));

//         driveTrain.leftPID.setReference(distance, ControlType.kPosition);

//         driveTrain.rightPID.setP(pEntry.getDouble(kP));
//         driveTrain.rightPID.setI(iEntry.getDouble(kI));
//         driveTrain.rightPID.setD(dEntry.getDouble(kD));
//         driveTrain.rightPID.setFF(ffEntry.getDouble(kFF));

//         driveTrain.rightPID.setReference(distance, ControlType.kPosition);
//     }

//     @Override
//     public boolean isFinished() {
//         return driveTrain.leftEncoder.getPosition() == distance && driveTrain.rightEncoder.getPosition() == distance;
//     }
// }
