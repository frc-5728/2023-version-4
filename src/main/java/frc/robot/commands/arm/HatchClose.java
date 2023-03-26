package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hatch;

public class HatchClose extends CommandBase {
  private final Hatch hatch;
  
  /** Creates a new HatchCommand. */
  public HatchClose(Hatch hatch) {
    this.hatch = hatch;    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hatch);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hatch.set(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !hatch.get();
  }
}
