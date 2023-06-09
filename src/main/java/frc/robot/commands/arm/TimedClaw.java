// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class TimedClaw extends CommandBase {
  private final Arm arm;
  private final double speed;
  private final float time;

  private final Timer timer = new Timer();
  
  /** Creates a new TimedClaw. */
  public TimedClaw(Arm arm, double speed, float time) {
    this.arm = arm;
    this.speed = speed;
    this.time = time;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
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
    arm.setSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.setSpeed(0);
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {    
    return timer.get() >= time;
  }
}
