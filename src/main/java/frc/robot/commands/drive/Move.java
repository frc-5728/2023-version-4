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
public class Move extends PIDCommand {
  private final ShuffleboardTab tab = Shuffleboard.getTab(getName());
  private final double kP = 0.0003;
  private final double kI = 0;
  private final double kD = 0;

  private final GenericEntry pEntry = tab.add(getName() + " P", kP).getEntry();
  private final GenericEntry iEntry = tab.add(getName() + " I", kI).getEntry();
  private final GenericEntry dEntry = tab.add(getName() + " D", kD).getEntry();

  /** Creates a new Move. */
  public Move(DriveTrain driveTrain, double distance) {
    super(
        // The controller that the command will use
        new PIDController(0.06, 0.01, 0.1),
        // This should return the measurement
        () -> driveTrain.leftEncoder.getPosition(),
        // This should return the setpoint (can also be a constant)
        () -> distance,
        // This uses the output
        output -> {
          // Use the output here
          driveTrain.setSpeed(MathUtil.clamp(output, -0.5, 0.5));
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    addRequirements(driveTrain);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
