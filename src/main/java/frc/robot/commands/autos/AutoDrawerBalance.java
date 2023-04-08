// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autos;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.arm.DrawerTimed;
import frc.robot.commands.arm.TimedDrawer;
import frc.robot.subsystems.Drawer;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoDrawerBalance extends ParallelCommandGroup {
  /** Creates a new AutoDrawerBalance. */
  public AutoDrawerBalance(DriveTrain driveTrain, Drawer drawer) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        new TimedDrawer(drawer, 5.0, -0.2),
        new AutoBalance(driveTrain));
  }
}
