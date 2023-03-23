// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drawer;

public class DrawerTimed extends CommandBase {
  private final Drawer drawer;
  private final int time;
  private final int speed;
  private final Timer timer = new Timer();
  
  /** Creates a new DrawerTimed. */
  public DrawerTimed(Drawer drawer, int time, int speed) {
    this.drawer = drawer;
    this.time = time;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drawer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    drawer.setSpeed(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() == time;
  }
}
