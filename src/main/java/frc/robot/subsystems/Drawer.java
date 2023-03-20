package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drawer extends SubsystemBase {
    private final DigitalInput outer = new DigitalInput(0);
    private final DigitalInput inner = new DigitalInput(1);

    public boolean getOuter() {
        return outer.get();
    }

    public boolean getInner() {
        return inner.get();
    }

    public Drawer() {
    }

}
