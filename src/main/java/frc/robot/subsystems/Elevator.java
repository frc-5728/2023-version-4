package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.Constants.LimitSwitches;
import frc.robot.Constants.OtherConstants;
import frc.robot.Constants.Store;

public class Elevator extends SubsystemBase {
    CANSparkMax motor = new CANSparkMax(CANIDs.ELEVATOR, MotorType.kBrushless);
    SparkMaxAbsoluteEncoder encoder = motor.getAbsoluteEncoder(Type.kDutyCycle);
    SparkMaxPIDController pid = motor.getPIDController();

    public final DigitalInput upper = new DigitalInput(LimitSwitches.ELEVATOR_TOP_LIMIT);
    public final DigitalInput lower = new DigitalInput(LimitSwitches.ELEVATOR_BOTTOM_LIMIT);

    private int position = 0;
    private double[] positions = new double[OtherConstants.NUMBER_OF_POSITIONS_ELEVATOR];

    private final ShuffleboardTab tab = Shuffleboard.getTab("Joysticks");
    private final GenericEntry positionEntry = tab.add("Elevator Position", encoder.getPosition())
            .getEntry();

    public Elevator() {
        motor.setSmartCurrentLimit(40);

        pid.setP(0.0001);
        pid.setI(0.0000001);
        pid.setD(0.0001);
        pid.setIZone(0);
        pid.setFF(0);
        pid.setOutputRange(-1, 1);

        computePositions();
    }

    private void computePositions() {
        double range = Store.ELEVATOR_ENCODER_END_POSITION - Store.ELEVATOR_ENCODER_START_POSITION;
        double step = range / (OtherConstants.NUMBER_OF_POSITIONS_ELEVATOR);

        for (int i = 0; i < OtherConstants.NUMBER_OF_POSITIONS_ELEVATOR; i++) {
            positions[i] = Store.ELEVATOR_ENCODER_START_POSITION + (i * step);
        }
    }

    public void moveUp() {
        position++;
        if (position > OtherConstants.NUMBER_OF_POSITIONS_ELEVATOR) {
            position = OtherConstants.NUMBER_OF_POSITIONS_ELEVATOR - 1;
            return;
        }
        setPosition(positions[position]);
    }

    public void moveDown() {
        position--;
        if (position < 0) {
            position = 0;
            return;
        }

        setPosition(positions[position]);
    }

    public void setPosition(double position) {
        pid.setReference(position, ControlType.kPosition);
    }

    public void set(double speed) {
        if (upper.get() || lower.get())
            return;
        motor.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (upper.get()) {
            Store.ELEVATOR_ENCODER_END_POSITION = encoder.getPosition();
            computePositions();
        }
        if (lower.get()) {
            Store.ELEVATOR_ENCODER_START_POSITION = encoder.getPosition();
            computePositions();
        }

        // setPosition(positionEntry.getDouble(Store.ELEVATOR_ENCODER_START_POSITION));
    }
}
