// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drawer;

public class TimedDrawer extends CommandBase {
  private final Drawer drawer;
  private final double time;
  private final double speed;

  private final Timer timer = new Timer();
  
  /** Creates a new TimedDrawer. */
  public TimedDrawer(Drawer drawer, double time, double speed) {
    this.drawer = drawer;
    this.time = time;
    this.speed = speed;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drawer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drawer.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drawer.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() >= time;
  }
}
