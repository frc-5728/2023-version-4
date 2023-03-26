// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hatch;

public class TimedHatchToggle extends CommandBase {
  private final Hatch hatch;
  private final double time;
  private final Timer timer = new Timer();

  /** Creates a new TimedHatchToggle. */
  public TimedHatchToggle(Hatch hatch, double time) {
    this.hatch = hatch;
    this.time = time;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hatch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();

    hatch.toggle();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hatch.toggle();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= time;
  }
}
