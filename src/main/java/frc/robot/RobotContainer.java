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
import frc.robot.commands.arm.HatchCommand;
import frc.robot.commands.arm.MoveClaw;
import frc.robot.commands.arm.MoveDrawer;
import frc.robot.commands.drive.Move;
import frc.robot.commands.drive.MoveToDistance;
import frc.robot.commands.drive.TankDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drawer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hatch;
import frc.robot.commands.VisionUtil.*;
import frc.robot.commands.autos.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.subsystems.Vision.*;

public class RobotContainer {
  private final CommandXboxController xboxController = new CommandXboxController(
      OperatorConstants.xboxControllerPort);
  private final Joystick joystick = new Joystick(OperatorConstants.joystickPort);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Hatch hatch = new Hatch();
  private final Arm arm = new Arm(joystick);
  private final Drawer drawer = new Drawer(joystick);
  private final Elevator elevator = new Elevator(joystick);

  private final ReflectiveTapeSubsystem m_rtSubsystem = new ReflectiveTapeSubsystem();
  private final AprilTagSubsystem m_atSubsystem = new AprilTagSubsystem();

  public RobotContainer() {
    configureBindings();

    SmartDashboard.putData("Drop Cone Upper", new DropConeUpper(m_rtSubsystem, driveTrain, hatch, elevator));
    SmartDashboard.putData("Drop Cone Lower", new DropConeLower(m_rtSubsystem, driveTrain, hatch, elevator));
    SmartDashboard.putData("Drop Block Upper", new DropBlockUpper(m_atSubsystem, driveTrain, hatch, elevator));
    SmartDashboard.putData("Drop Block Lower", new DropBlockLower(m_atSubsystem, driveTrain, hatch, elevator));
    SmartDashboard.putData("Balance", new AutoBalance(driveTrain));
    SmartDashboard.putData("Go 2 coopertition", new GoToCoopertition(m_atSubsystem, driveTrain));
    SmartDashboard.putData("Go 2 Home", new GoToHome(m_atSubsystem, driveTrain));
  }

  private void configureBindings() {
    // bindings for drive train stuff (like ones using xbox controller)
    xboxController.start().onTrue(new TankDrive(driveTrain));

    xboxController.povUp().onTrue(new Move(driveTrain, 2));
    xboxController.povDown().onTrue(new Move(driveTrain, -5));

    configureBindingsJoystick();
  }

  private void configureBindingsJoystick() {
    JoystickButton triggerButton = new JoystickButton(joystick, JoystickIDs.JOYSTICK_TRIGGER_ID);
    JoystickButton armUpButton = new JoystickButton(joystick, JoystickIDs.ARM_UP_JOYSTICK_ID);
    JoystickButton armDownButton = new JoystickButton(joystick, JoystickIDs.ARM_DOWN_JOYSTICK_ID);
    JoystickButton elevatorUpButton = new JoystickButton(joystick, JoystickIDs.ELEVATOR_UP_JOYSTICK_ID);
    JoystickButton elevatorDownButton = new JoystickButton(joystick, JoystickIDs.ELEVATOR_DOWN_JOYSTICK_ID);
    JoystickButton drawerUpButton = new JoystickButton(joystick, JoystickIDs.DRAWER_UP_JOYSTICK_ID);
    JoystickButton drawerDownButton = new JoystickButton(joystick, JoystickIDs.DRAWER_DOWN_JOYSTICK_ID);

    JoystickButton setpointModeToggleButton = new JoystickButton(joystick, 8);

    triggerButton.whileTrue(new HatchCommand(hatch));
    armUpButton.whileTrue(new MoveClaw(arm, 0.6));
    armDownButton.whileTrue(new MoveClaw(arm, -0.6));
    elevatorUpButton.onTrue(Commands.run(() -> elevator.moveUp(), elevator));
    elevatorDownButton.onTrue(Commands.run(() -> elevator.moveDown(), elevator));

    drawerUpButton.whileTrue(new MoveDrawer(drawer, 0.5));
    drawerDownButton.whileTrue(new MoveDrawer(drawer, -0.5));
    setpointModeToggleButton.onTrue(Commands.run(() -> {drawer.setSetpointModeOn(!drawer.setpointModeOn);}, drawer));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
