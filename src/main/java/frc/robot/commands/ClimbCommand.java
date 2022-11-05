// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;

public class ClimbCommand extends CommandBase {
  /** Creates a new climb. */
  public Climber climber;
  public ClimberState state;

  public ClimbCommand(Climber climber, ClimberState state) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.climber = climber;
    this.state = state;
    addRequirements(RobotContainer.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     switch (state) {
        case UP: 
          climber.up();
        case DOWN:
          climber.down();
        case STOP:
          climber.stop();
        default:
          climber.stop();
     }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public enum ClimberState {
    UP,
    DOWN,
    STOP
  }
}
