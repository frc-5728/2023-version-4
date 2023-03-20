package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;

public class Arm extends SubsystemBase {
    TalonSRX motor = new TalonSRX(CANIDs.ARM);

    DigitalInput outer = new DigitalInput(0);
    DigitalInput inner = new DigitalInput(1);

    private final ShuffleboardTab tab = Shuffleboard.getTab("JoySticks");

    public Arm() {
        motor.configFactoryDefault();
    }

    public void set(double speed) {
        motor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public void setPosition(double position) {
        motor.set(TalonSRXControlMode.Position, position);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (outer.get() || inner.get()) {
            motor.set(TalonSRXControlMode.PercentOutput, 0);
        }
    }
}
