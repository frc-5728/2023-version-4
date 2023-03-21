package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.Constants.Store;

public class Drawer extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(CANIDs.DRAWER);

    public final DigitalInput outer = new DigitalInput(0);
    public final DigitalInput inner = new DigitalInput(1);

    private final ShuffleboardTab tab = Shuffleboard.getTab("JoySticks");
    private final GenericEntry positionEntry = tab.add("Drawer Position", motor.getSelectedSensorPosition()).getEntry();
    private final GenericEntry kPEntry = tab.add("Drawer kP", 0.0).getEntry();
    private final GenericEntry kIEntry = tab.add("Drawer kI", 0.0).getEntry();
    private final GenericEntry kDEntry = tab.add("Drawer kD", 0.0).getEntry();
    private final GenericEntry kFEntry = tab.add("Drawer kF", 0.0).getEntry();

    public Drawer() {
        motor.configFactoryDefault();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        positionEntry.setDouble(motor.getSelectedSensorPosition());

        motor.config_kP(0, kPEntry.getDouble(0.0));
        motor.config_kI(0, kIEntry.getDouble(0.0));
        motor.config_kD(0, kDEntry.getDouble(0.0));
        motor.config_kF(0, kFEntry.getDouble(0.0));

        if (getOuter())
            Store.DRAWER_ENCODER_END_POSITION = motor.getSelectedSensorPosition();
        if (getInner())
            Store.DRAWER_ENCODER_START_POSITION = motor.getSelectedSensorPosition();
    }

    public void set(double speed) {
        if (getOuter() || getInner())
            return;
        motor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public boolean getOuter() {
        return outer.get();
    }

    public boolean getInner() {
        return inner.get();
    }

}
