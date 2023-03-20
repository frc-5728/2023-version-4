package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;

public class Elevator extends SubsystemBase {
    CANSparkMax motor = new CANSparkMax(CANIDs.ELEVATOR, MotorType.kBrushless);
    SparkMaxPIDController pid = motor.getPIDController();

    DigitalInput upper = new DigitalInput(0);
    DigitalInput lower = new DigitalInput(1);

    public Elevator() {
    }
}
