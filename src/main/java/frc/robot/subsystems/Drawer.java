package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.Constants.LimitSwitches;
import frc.robot.Constants.OtherConstants;
import frc.robot.Constants.Store;

public class Drawer extends SubsystemBase {
    private final TalonSRX motor = new TalonSRX(CANIDs.DRAWER);

    public final DigitalInput outer = new DigitalInput(LimitSwitches.DRAWER_TOP_LIMIT);
    public final DigitalInput inner = new DigitalInput(LimitSwitches.DRAWER_BOTTOM_LIMIT);

    private final ShuffleboardTab tab = Shuffleboard.getTab("JoySticks");
    private final GenericEntry positionEntry = tab.add("Drawer Encoder", motor.getSelectedSensorPosition()).getEntry();
    private final GenericEntry kPEntry = tab.add("Drawer kP", 0.0).getEntry();
    private final GenericEntry kIEntry = tab.add("Drawer kI", 0.0).getEntry();
    private final GenericEntry kDEntry = tab.add("Drawer kD", 0.0).getEntry();
    private final GenericEntry kFEntry = tab.add("Drawer kF", 0.0).getEntry();

    private int position = 0;
    private double[] positions = new double[OtherConstants.NUMBER_OF_POSITIONS_DRAWER];

    public Drawer() {
        computePositions();
        motor.configFactoryDefault();
    }

    private void computePositions() {
        double range = Store.ELEVATOR_ENCODER_END_POSITION - Store.ELEVATOR_ENCODER_START_POSITION;
        double step = range / (OtherConstants.NUMBER_OF_POSITIONS_DRAWER);

        for (int i = 0; i < OtherConstants.NUMBER_OF_POSITIONS_DRAWER; i++) {
            positions[i] = Store.ELEVATOR_ENCODER_START_POSITION + (i * step);
        }
    }

    public void moveUp() {
        position++;
        if (position > OtherConstants.NUMBER_OF_POSITIONS_DRAWER)
            position = OtherConstants.NUMBER_OF_POSITIONS_DRAWER;
        setPosition(positions[position]);
    }

    public void moveDown() {
        position--;
        if (position < 0)
            position = 0;
        setPosition(positions[position]);
    }

    public void setPosition(double position) {
        motor.set(TalonSRXControlMode.Position, position);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        positionEntry.setDouble(motor.getSelectedSensorPosition());

        motor.config_kP(0, kPEntry.getDouble(0.0));
        motor.config_kI(0, kIEntry.getDouble(0.0));
        motor.config_kD(0, kDEntry.getDouble(0.0));
        motor.config_kF(0, kFEntry.getDouble(0.0));

        if (getOuter()) {
            Store.DRAWER_ENCODER_END_POSITION = motor.getSelectedSensorPosition();
            computePositions();
        }
        if (getInner()) {
            Store.DRAWER_ENCODER_START_POSITION = motor.getSelectedSensorPosition();
            computePositions();
        }
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
