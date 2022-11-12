// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;

public class IntakeCommand extends CommandBase {
  /** Creates a new IntakeCommand. */
  public Intake intake;
  public Index index;
  public IntakeEnum state;

  public IntakeCommand(Intake intake, Index index, IntakeEnum state) {
    this.index = index;
    this.intake = intake;
    this.state = state;
    addRequirements(RobotContainer.index);
    addRequirements(RobotContainer.intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (state) {
      case INTAKE:
        intake.runMotor(1);
        index.runMotor(1);
      case OUTTAKE:
        intake.runMotor(-1);
        index.runMotor(-1);
      case STOP:
        intake.stop();
        index.stop();
      default:
        intake.runMotor(1);
        index.runMotor(1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    index.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum IntakeEnum {
    INTAKE,
    OUTTAKE,
    STOP
  }
}
