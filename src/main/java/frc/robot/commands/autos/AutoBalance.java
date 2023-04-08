// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoBalance extends PIDCommand {
  /** Creates a new AutoBalance. */
  public AutoBalance(DriveTrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(0.008, 0, 0.013),
        // This should return the measurement
        () -> driveTrain.gyro.getPitch(),
        // This should return the setpoint (can also be a constant)
        () -> 3.390000057220459,
        // This uses the output
        output -> {
          // Use the output here

          // System.out.println("autobalance output: " + output);
          SmartDashboard.putNumber("ab pid", output);
          driveTrain.setSpeed(-MathUtil.clamp(output, -0.1, 0.1));
        });
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    getController().setTolerance(1);
    
    addRequirements(driveTrain);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}