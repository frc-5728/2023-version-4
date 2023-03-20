package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TimedOutputDrive extends CommandBase {
    private final DriveTrain driveTrain;
    private final double output;
    private final double timeout;
    private final Timer timer = new Timer();

    public TimedOutputDrive(DriveTrain driveTrain, double output, double timeout) {
        this.driveTrain = driveTrain;
        this.output = output;
        this.timeout = timeout;
        timer.reset();
        timer.start();

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(driveTrain);
    }

    @Override
    public void initialize() {
        driveTrain.setSpeed(output);
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.setSpeed(0);
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return timer.get() >= timeout;
    }
}
