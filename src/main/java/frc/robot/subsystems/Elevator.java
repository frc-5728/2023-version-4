package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.Constants.LimitSwitches;

public class Elevator extends SubsystemBase {
    CANSparkMax motor = new CANSparkMax(CANIDs.ELEVATOR, MotorType.kBrushless);
    SparkMaxAbsoluteEncoder encoder = motor.getAbsoluteEncoder(Type.kDutyCycle);
    SparkMaxPIDController pid = motor.getPIDController();

    public final DigitalInput upper = new DigitalInput(LimitSwitches.ELEVATOR_TOP_LIMIT);
    public final DigitalInput lower = new DigitalInput(LimitSwitches.ELEVATOR_BOTTOM_LIMIT);

    public Elevator() {
    }

    public void set(double speed) {
        if (upper.get() || lower.get())
            return;
        motor.set(speed);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (upper.get() || lower.get()) {
            motor.set(0);
        }
    }
}
