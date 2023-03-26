package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.Store;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;

public class TankDrive extends CommandBase {
    private final DriveTrain driveTrain;
    private final XboxController controller = new XboxController(OperatorConstants.xboxControllerPort);
    private final Elevator elevator;

    public TankDrive(DriveTrain driveTrain, Elevator elevator) {
        this.driveTrain = driveTrain;
        this.elevator = elevator;

        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        double triggerVal = controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();

        // then for the left and right turns during teleop tank drive
        // that we will use the left axis
        // http://www.team358.org/files/programming/ControlSystem2015-2019/images/Logitech-F310_ControlMapping.pdf
        double stick = controller.getLeftX() * OperatorConstants.TURNING_RATE;

        // for a slow controlling mode on the right side
        double slowTrigger = controller.getRightY() * OperatorConstants.SLOW_MODE_RATE;

        // this is using manning robotic's GTA drive where they control robot like the
        // GTA game

        driveTrain.setLeftSpeed(triggerVal + stick - (slowTrigger));
        driveTrain.setRightSpeed(triggerVal - stick - (slowTrigger));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
