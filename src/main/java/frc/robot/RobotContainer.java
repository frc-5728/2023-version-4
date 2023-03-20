// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.drive.TankDrive;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {
  private final CommandXboxController xboxController = new CommandXboxController(
      OperatorConstants.xboxControllerPort);
  private final Joystick joystick = new Joystick(OperatorConstants.joystickPort);

  private final DriveTrain driveTrain = new DriveTrain();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // bindings for drive train stuff (like ones using xbox controller)
    xboxController.start().onTrue(new TankDrive(driveTrain));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
