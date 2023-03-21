package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drawer;

public class MoveDrawer extends CommandBase {
    private final Drawer drawer;

    public MoveDrawer(Drawer drawer, double position) {
        this.drawer = drawer;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drawer);
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean isFinished() {
        return drawer.outer.get() || drawer.inner.get();
    }
}
