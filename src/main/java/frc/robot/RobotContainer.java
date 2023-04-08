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
import frc.robot.commands.arm.MoveElevator;
import frc.robot.commands.arm.TimedClaw;
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

  private final Elevator elevator = new Elevator(joystick);
  private final DriveTrain driveTrain = new DriveTrain(elevator);
  private final Hatch hatch = new Hatch();
  private final Arm arm = new Arm(joystick);
  private final Drawer drawer = new Drawer(joystick);

  private final ReflectiveTapeSubsystem m_rtSubsystem = new ReflectiveTapeSubsystem();
  private final AprilTagSubsystem m_atSubsystem = new AprilTagSubsystem();

  public RobotContainer() {
    configureBindings();

    SmartDashboard.putData("Drop Cone Upper", new DropConeUpper(m_rtSubsystem, driveTrain, hatch, elevator, arm, drawer));
    SmartDashboard.putData("Drop Cone Lower", new DropConeLower(m_rtSubsystem, driveTrain, hatch, elevator, arm, drawer));
    SmartDashboard.putData("Drop Block Upper left", new DropBlockUpperLeft(m_atSubsystem, driveTrain, hatch, elevator, arm, drawer));
    SmartDashboard.putData("Drop Block Lower left", new DropBlockLowerLeft(m_atSubsystem, driveTrain, hatch, elevator, drawer, arm));
    SmartDashboard.putData("Drop Block Lower middle", new DropBlockLowerMiddle(m_atSubsystem, driveTrain, hatch, elevator, drawer, arm));
    SmartDashboard.putData("Drop Block Lower Right", new DropBlockLowerRight(m_atSubsystem, driveTrain, hatch, elevator, drawer, arm));
    SmartDashboard.putData("Drop Block Upper Middle", new DropBlockUpperMiddle(m_atSubsystem, driveTrain, hatch, elevator, arm, drawer));
    SmartDashboard.putData("Drop Block Upper Right", new DropBlockUpperRight(m_atSubsystem, driveTrain, hatch, elevator, arm, drawer));
    SmartDashboard.putData("Balance", new AutoDrawerBalance(driveTrain, drawer));
    SmartDashboard.putData("Go 2 coopertition", new GoToCoopertition(m_atSubsystem, driveTrain));
    SmartDashboard.putData("Go 2 Home", new GoToHome(m_atSubsystem, driveTrain));
    SmartDashboard.putData("7AutoPeriod", new AutoPeriod(driveTrain, hatch, elevator, drawer,  arm));
    SmartDashboard.putData("Auto Get Ready To Drop Top", new AutoTop( elevator, drawer,  arm));
    SmartDashboard.putData("Auto Get Ready To Drop Bottom", new AutoBottom( elevator, drawer,  arm));

  }

  private void configureBindings() {
    // bindings for drive train stuff (like ones using xbox controller)
    xboxController.start().onTrue(new TankDrive(driveTrain, elevator));

    // xboxController.povUp().onTrue(new Move(driveTrain, 2));
    xboxController.povUp().onTrue(new MoveToDistance(driveTrain, 5));

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
    elevatorUpButton.whileTrue(new MoveElevator(elevator, 1));
    elevatorDownButton.whileTrue(new MoveElevator(elevator, -1));

    drawerUpButton.whileTrue(new MoveDrawer(drawer, 0.7));
    drawerDownButton.whileTrue(new MoveDrawer(drawer, -0.5));
    setpointModeToggleButton.onTrue(Commands.run(() -> {drawer.setSetpointModeOn(!drawer.setpointModeOn);}, drawer));
  }

  public Command getAutonomousCommand() {
    return new AutoPeriod(driveTrain, hatch, elevator, drawer, arm);
  }
}
