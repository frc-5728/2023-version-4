package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANIDs;
import frc.robot.commands.drive.TankDrive;

public class DriveTrain extends SubsystemBase {
    public CANSparkMax leftCanSparkMax = new CANSparkMax(CANIDs.MOTOR_LEFT0_ID, MotorType.kBrushless);
    public CANSparkMax leftFollowCanSparkMax = new CANSparkMax(CANIDs.MOTOR_LEFT1_ID, MotorType.kBrushless);

    public CANSparkMax rightCanSparkMax = new CANSparkMax(CANIDs.MOTOR_RIGHT0_ID, MotorType.kBrushless);
    public CANSparkMax rightFollowCanSparkMax = new CANSparkMax(CANIDs.MOTOR_RIGHT1_ID, MotorType.kBrushless);

    public RelativeEncoder leftEncoder = leftCanSparkMax.getEncoder();
    public RelativeEncoder rightEncoder = rightCanSparkMax.getEncoder();

    public final SparkMaxPIDController leftPID = leftCanSparkMax.getPIDController();
    public final SparkMaxPIDController rightPID = rightCanSparkMax.getPIDController();

    public final AHRS gyro = new AHRS();

    private final ShuffleboardTab tab = Shuffleboard.getTab("Drive");

    private final GenericEntry leftPositionEntry = tab.add("left position", 0).getEntry();
    private final GenericEntry rightPositionEntry = tab.add("right position", 0).getEntry();

    public DriveTrain() {
        rightCanSparkMax.setInverted(true);
        
        leftFollowCanSparkMax.follow(leftCanSparkMax);
        rightFollowCanSparkMax.follow(rightCanSparkMax);

        // leftCanSparkMax.burnFlash();
        // leftFollowCanSparkMax.burnFlash();
        // rightCanSparkMax.burnFlash();
        // rightFollowCanSparkMax.burnFlash();

        setDefaultCommand(new TankDrive(this));
    }

    public void resetEncoders() {
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }
    
    public void setSpeed(double speed) {
        setLeftSpeed(speed);
        setRightSpeed(speed);
    }

    public void setLeftSpeed(double speed) {
        leftCanSparkMax.set(speed);
    }

    public void setRightSpeed(double speed) {
        rightCanSparkMax.set(speed);
    }

    @Override
    public void periodic() {
        leftPositionEntry.setDouble(this.leftEncoder.getPosition());
        rightPositionEntry.setDouble(this.rightEncoder.getPosition());
    }
}
