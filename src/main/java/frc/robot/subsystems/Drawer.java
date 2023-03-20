package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;

public class Drawer extends SubsystemBase {
    TalonSRX motor = new TalonSRX(CANIDs.DRAWER);

    private final DigitalInput outer = new DigitalInput(0);
    private final DigitalInput inner = new DigitalInput(1);

    private final ShuffleboardTab tab = Shuffleboard.getTab("JoySticks");

    public Drawer() {
        motor.configFactoryDefault();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (getOuter() || getInner()) {
            motor.set(TalonSRXControlMode.PercentOutput, 0);
        }
    }

    public void set(double speed) {
        motor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public boolean getOuter() {
        return outer.get();
    }

    public boolean getInner() {
        return inner.get();
    }

}
