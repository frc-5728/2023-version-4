package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
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
import frc.robot.Constants.OtherConstants;
import frc.robot.Constants.Store;
import frc.robot.commands.arm.DrawerPosition;

public class Drawer extends SubsystemBase {
    private final Joystick joystick;

    public final TalonSRX motor = new TalonSRX(CANIDs.DRAWER);

    private final ShuffleboardTab tab = Shuffleboard.getTab("JoySticks");
    private final GenericEntry positionEntry = tab.add("Drawer Encoder", motor.getSelectedSensorPosition()).getEntry();

    private final double kP = 1.0;
    private final double kI = 0.0;
    private final double kD = 0.0;
    private final double kFF = 0.0;

    private final GenericEntry kPEntry = tab.add("Drawer kP", kP).getEntry();
    private final GenericEntry kIEntry = tab.add("Drawer kI", kI).getEntry();
    private final GenericEntry kDEntry = tab.add("Drawer kD", kD).getEntry();
    private final GenericEntry kFEntry = tab.add("Drawer kF", kFF).getEntry();

    public boolean setpointModeOn = true;

    private double pos = 0.0;

    private int position = 0;
    private double[] positions = new double[OtherConstants.NUMBER_OF_POSITIONS_DRAWER];

    public Drawer(Joystick joystick) {
        this.joystick = joystick;

        computePositions();
        motor.configFactoryDefault();
        motor.setNeutralMode(NeutralMode.Brake);

        // setDefaultCommand(new DrawerPosition(this,
        // Store.DRAWER_ENCODER_START_POSITION));

        motor.setSelectedSensorPosition(0);
    }

    public void resetEncoders() {
        motor.setSelectedSensorPosition(0);
    }

    public void setSpeed(double speed) {
        motor.set(TalonSRXControlMode.PercentOutput, -speed);
    }

    private void computePositions() {
        double range = Store.ELEVATOR_ENCODER_END_POSITION - Store.ELEVATOR_ENCODER_START_POSITION;
        double step = range / (OtherConstants.NUMBER_OF_POSITIONS_DRAWER);

        for (int i = 0; i < OtherConstants.NUMBER_OF_POSITIONS_DRAWER; i++) {
            positions[i] = Store.ELEVATOR_ENCODER_START_POSITION + (i * step);
        }
    }

    public void setSetpointModeOn(boolean on) {
        setpointModeOn = on;
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

    public void changePosition(double position) {
        this.pos = position;
    }

    private void setPosition(double position) {
        motor.set(TalonSRXControlMode.Position, position);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        positionEntry.setDouble(motor.getSelectedSensorPosition());

        motor.config_kP(0, kPEntry.getDouble(kP));
        motor.config_kI(0, kIEntry.getDouble(kI));
        motor.config_kD(0, kDEntry.getDouble(kD));
        motor.config_kF(0, kFEntry.getDouble(kFF));

        if (setpointModeOn) {
            double range = 150;
            double p = 0 + range * (joystick.getZ() + 1) / 2;
            // setPosition(p);
        } else {
        }

        if (motor.getMotorOutputPercent() != 0
                && (motor.getSelectedSensorVelocity() < 2 && motor.getSelectedSensorVelocity() > -2)) {
            // System.out.println("at the end point");
            // System.out.println(motor.getSelectedSensorVelocity());

            // motor.setSelectedSensorPosition(0);
        }

        if (joystick.getRawButton(9)) {
            resetEncoders();
        }

        // if (getOuter()) {
        // Store.DRAWER_ENCODER_END_POSITION = motor.getSelectedSensorPosition();
        // computePositions();
        // }
        // if (getInner()) {
        // Store.DRAWER_ENCODER_START_POSITION = motor.getSelectedSensorPosition();
        // computePositions();
        // }
    }
}
