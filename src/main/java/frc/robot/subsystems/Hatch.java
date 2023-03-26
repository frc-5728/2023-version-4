package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OtherConstants;

public class Hatch extends SubsystemBase {
    Solenoid solenoid = new Solenoid(PneumaticsModuleType.REVPH, OtherConstants.SOLENOID_CHANNEL);
    private boolean isOpen = false;

    public void set(boolean isOpen) {
        this.isOpen = isOpen;
        solenoid.set(this.isOpen);
    }

    public boolean get() {
        return isOpen;
    }

    public void toggle() {
        if (isOpen) {
            isOpen = false;
        } else {
            isOpen = true;
        }
        solenoid.set(isOpen);
    }
}
