package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    DigitalInput outer = new DigitalInput(0);
    DigitalInput inner = new DigitalInput(1);

    public Arm() {
    }
}
