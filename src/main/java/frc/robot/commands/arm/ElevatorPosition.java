// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.OtherConstants;
import frc.robot.Constants.Store;
import frc.robot.subsystems.Elevator;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ElevatorPosition extends PIDCommand {
  /** Creates a new ElevatorPosition. */
  public ElevatorPosition(Elevator elevator, int position) {
    super(
        // The controller that the command will use
        new PIDController(1, 0, 0.5),
        // This should return the measurement
        () -> elevator.encoder.getPosition(),
        // This should return the setpoint (can also be a constant)
        () -> position * Store.ELEVATOR_ENCODER_START_POSITION / OtherConstants.NUMBER_OF_POSITIONS_ELEVATOR,
        // This uses the output
        output -> {
          // Use the output here
          System.out.println("elevator pos pid: " + output);
          elevator.set(-MathUtil.clamp(output, -0.8, 0.8));
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    addRequirements(elevator);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
