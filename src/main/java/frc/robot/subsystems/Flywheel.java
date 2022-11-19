// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
  /** Creates a new Flywheel. */
  CANSparkMax flywheelMotor;
  RelativeEncoder flywheelEncoder;
  final double kV = 0.0075;
  final double kP = 0.1;
  final int setpoint = 300;
  PIDController controller;

  public Flywheel() {
    flywheelMotor = new CANSparkMax(10, MotorType.kBrushless);
    flywheelEncoder = flywheelMotor.getEncoder();
    controller = new PIDController(kP, 0, 0);
  }

  public void start() {
    flywheelMotor.setVoltage(controller.calculate(flywheelEncoder.getVelocity(), setpoint) + kV);
  }

  public void stop() {
    flywheelMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

}
