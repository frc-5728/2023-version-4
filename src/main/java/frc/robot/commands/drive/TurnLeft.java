// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drive;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnLeft extends PIDCommand {
  /** Creates a new TurnLeft. */
  public TurnLeft(DriveTrain driveTrain, double angle) {
    super(
        // The controller that the command will use
        new PIDController(0.0003, 0, 0.01),
        // This should return the measurement
        () -> driveTrain.gyro.getYaw(),
        // This should return the setpoint (can also be a constant)
        () -> angle,
        // This uses the output
        output -> {
          // Use the output here
          double outputClamped = MathUtil.clamp(output, -0.15, 0.15);
          driveTrain.setLeftSpeed(outputClamped);
          driveTrain.setRightSpeed(-outputClamped);
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveTrain);

    getController().setTolerance(0.5);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
