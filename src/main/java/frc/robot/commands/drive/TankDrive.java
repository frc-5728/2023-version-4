package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {
    DriveTrain driveTrain;
    XboxController controller = new XboxController(OperatorConstants.xboxControllerPort);

    public TankDrive(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;

        addRequirements(driveTrain);
    }

    @Override
    public void execute() {
        double triggerVal = controller.getRightTriggerAxis() - controller.getLeftTriggerAxis();

        // then for the left and right turns during teleop tank drive
        // that we will use the left axis (i think its 0 as id)
        // http://www.team358.org/files/programming/ControlSystem2015-2019/images/Logitech-F310_ControlMapping.pdf
        double stick = controller.getLeftX() * OperatorConstants.TURNING_RATE;

        // for a slow controlling mode on the right side
        double slowTrigger = controller.getRightY() * OperatorConstants.SLOW_MODE_RATE;

        // this is using manning robotic's GTA drive where they control robot like the
        // GTA game

        driveTrain.setLeftSpeed(triggerVal + stick - (slowTrigger));
        driveTrain.setRightSpeed(triggerVal - stick - (slowTrigger));
    }
}
