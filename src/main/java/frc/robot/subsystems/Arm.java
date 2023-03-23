package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.Constants.LimitSwitches;
import frc.robot.Constants.Store;

public class Arm extends SubsystemBase {
    TalonSRX motor = new TalonSRX(CANIDs.ARM);

    private final DigitalInput limitSwitch = new DigitalInput(LimitSwitches.ARM_DIO);

    private final ShuffleboardTab tab = Shuffleboard.getTab("JoySticks");
    private final GenericEntry positionEntry = tab.add("Drawer Position", motor.getSelectedSensorPosition()).getEntry();

    // private final GenericEntry kPEntry = tab.add("Arm kP", 0.0).getEntry();
    // private final GenericEntry kIEntry = tab.add("Arm kI", 0.0).getEntry();
    // private final GenericEntry kDEntry = tab.add("Arm kD", 0.0).getEntry();
    // private final GenericEntry kFEntry = tab.add("Arm kF", 0.0).getEntry();

    private final Joystick joystick;

    public Arm(Joystick joystick) {
        this.joystick = joystick;

        motor.configFactoryDefault();
    }

    public void setSpeed(double speed) {
        if (!limitSwitch.get() && speed < 0) {
            motor.set(TalonSRXControlMode.PercentOutput, 0);
            return;
        }

        motor.set(TalonSRXControlMode.PercentOutput, -speed);
    }

    public void setPosition(double position) {
        motor.set(TalonSRXControlMode.Position, position);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        positionEntry.setDouble(motor.getSelectedSensorPosition());

        // motor.config_kP(0, kPEntry.getDouble(0.0));
        // motor.config_kI(0, kIEntry.getDouble(0.0));
        // motor.config_kD(0, kDEntry.getDouble(0.0));
        // motor.config_kF(0, kFEntry.getDouble(0.0));
    }
}
