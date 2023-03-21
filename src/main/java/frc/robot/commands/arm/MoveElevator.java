package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class MoveElevator extends CommandBase {
    private final Elevator elevator;

    public MoveElevator(Elevator elevator, double position) {
        this.elevator = elevator;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(elevator);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return elevator.upper.get() || elevator.lower.get();
    }
}
