package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.commands.drive.TankDrive;

public class DriveTrain extends SubsystemBase {
    public final CANSparkMax leftCanSparkMax = new CANSparkMax(CANIDs.MOTOR_LEFT0_ID, MotorType.kBrushless);
    public final CANSparkMax leftFollowCanSparkMax = new CANSparkMax(CANIDs.MOTOR_LEFT1_ID, MotorType.kBrushless);

    public final CANSparkMax rightCanSparkMax = new CANSparkMax(CANIDs.MOTOR_RIGHT0_ID, MotorType.kBrushless);
    public final CANSparkMax rightFollowCanSparkMax = new CANSparkMax(CANIDs.MOTOR_RIGHT1_ID, MotorType.kBrushless);

    public final SparkMaxPIDController leftPID = leftCanSparkMax.getPIDController();
    public final SparkMaxPIDController rightPID = rightCanSparkMax.getPIDController();

    public final AHRS gyro = new AHRS();

    public DriveTrain() {
        leftFollowCanSparkMax.follow(leftCanSparkMax);
        rightFollowCanSparkMax.follow(rightCanSparkMax);

        leftCanSparkMax.burnFlash();
        leftFollowCanSparkMax.burnFlash();
        rightCanSparkMax.burnFlash();
        rightFollowCanSparkMax.burnFlash();

        setDefaultCommand(new TankDrive(this));
    }

    public void setSpeed(double speed) {
        setLeftSpeed(speed);
        setRightSpeed(speed);
    }

    public void setLeftSpeed(double speed) {
        leftCanSparkMax.set(speed);
    }

    public void setRightSpeed(double speed) {
        rightCanSparkMax.set(-speed);
    }
}
