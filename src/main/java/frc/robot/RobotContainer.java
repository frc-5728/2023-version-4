// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.JoystickIDs;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.drive.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hatch;

public class RobotContainer {
  private final CommandXboxController xboxController = new CommandXboxController(
      OperatorConstants.xboxControllerPort);
  private final Joystick joystick = new Joystick(OperatorConstants.joystickPort);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Hatch hatch = new Hatch();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // bindings for drive train stuff (like ones using xbox controller)
    xboxController.start().onTrue(new TankDrive(driveTrain));
    xboxController.x().onTrue(Commands.print("X button pressed"));

    configureBindingsJoystick();
  }

  private void configureBindingsJoystick() {
    JoystickButton triggerButton = new JoystickButton(joystick, JoystickIDs.JOYSTICK_TRIGGER_ID);

    triggerButton.onTrue(Commands.run(() -> hatch.toggle(), hatch));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
