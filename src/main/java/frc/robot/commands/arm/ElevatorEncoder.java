// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drawer;
import frc.robot.subsystems.Elevator;

public class ElevatorEncoder extends CommandBase {
  /** Creates a new ElevatorEncoder. */
  private final Elevator elevator;
  
  public ElevatorEncoder(Elevator elevator) {
    this.elevator = elevator;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    elevator.set(-0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevator.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elevator.lower.get();
  }
}
