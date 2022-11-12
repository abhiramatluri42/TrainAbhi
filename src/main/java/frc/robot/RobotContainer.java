// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.ClimbCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.FlyWheelComm;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimbCommand.ClimberState;
import frc.robot.commands.FlyWheelComm.FlyWheelEnum;
import frc.robot.commands.IntakeCommand.IntakeEnum;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static Joystick controller = new Joystick(0);

  private JoystickButton aButton = new JoystickButton(controller, 1);
  private JoystickButton bButton = new JoystickButton(controller, 2);
  private JoystickButton xButton = new JoystickButton(controller, 3);
  private JoystickButton yButton = new JoystickButton(controller, 4);
  private JoystickButton lBumper = new JoystickButton(controller, 5);
  private JoystickButton rBumper = new JoystickButton(controller, 6);

  public static final Climber climber = new Climber();
  public static final Intake intake = new Intake();
  public static final Index index = new Index();
  public static final Flywheel flywheel = new Flywheel();
  public ClimberState climberState = ClimberState.DOWN;
  public IntakeEnum intakeState = IntakeEnum.INTAKE;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    if (climberState == ClimberState.DOWN) {
      lBumper.whileHeld(new ClimbCommand(climber, ClimberState.UP));
      climberState = ClimberState.UP;
    }
    if (climberState == ClimberState.UP){
      lBumper.whileHeld(new ClimbCommand(climber, ClimberState.DOWN));
      climberState = ClimberState.DOWN;
    }
    if (intakeState == intakeState.INTAKE) {
      rBumper.whileHeld(new IntakeCommand(intake, index, IntakeEnum.INTAKE));
      intakeState = IntakeEnum.OUTTAKE;
    }
    if (intakeState == intakeState.OUTTAKE) {
      rBumper.whileHeld(new IntakeCommand(intake, index, IntakeEnum.OUTTAKE));
      intakeState = IntakeEnum.STOP;
    }
    if (intakeState == intakeState.STOP) {
      rBumper.whileHeld(new IntakeCommand(intake, index, IntakeEnum.STOP));
      intakeState = IntakeEnum.INTAKE;
    }
    aButton.whileHeld(new FlyWheelComm(flywheel, FlyWheelEnum.ON));
    aButton.whenReleased(new FlyWheelComm(flywheel, FlyWheelEnum.OFF));

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
