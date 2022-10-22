// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */

  private CANSparkMax climberMotor;
  private RelativeEncoder climberEncoder;

  private int maxHeight = 182;
  private int minHeight = 5;

  public Climber() {
    climberMotor = new CANSparkMax(0, MotorType.kBrushless);
    climberEncoder = climberMotor.getEncoder();
  }

  public void up() {
    if (climberEncoder.getPosition()<maxHeight) {
      climberMotor.set(1);
    }
    else{
      stop();
    }
  }

  public void down() {
    if (climberEncoder.getPosition()>minHeight) {
      climberMotor.set(-1);
    }
    else {
      stop();
    }
  }

  public void stop() {
    climberMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
