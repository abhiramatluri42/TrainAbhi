// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Flywheel;

public class FlyWheelComm extends CommandBase {
  /** Creates a new flyWheelComm. */
  public Flywheel flywheel;
  public FlyWheelEnum state;
//mpyjomweofjgifepkgjirpthtykgrijrkk0otj04w09ekp9gtgjrioiotoigkpoegfkoojmpgjmpgfjpoimfoijmdfknmflknfklm
  public FlyWheelComm(Flywheel flywheel, FlyWheelEnum state) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.flywheel = flywheel;
    this.state = state;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    switch (state) {
      case ON:
        flywheel.start();
      case OFF:
        flywheel.stop();
      default:
        flywheel.stop();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    flywheel.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum FlyWheelEnum {
    ON,
    OFF
  }
}
