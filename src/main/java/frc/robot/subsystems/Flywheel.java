// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
  /** Creates a new Flywheel. */
  private CANSparkMax flywheelMotor;
  private CANSparkMax flywheelSlave;
  private RelativeEncoder flywheelEncoder;
  private final double kV = 0.0075;
  private final double kP = 0.0002;
  private final int setpoint = 300;
  private PIDController controller;
  private SparkMaxPIDController flywheelController;
  

  public Flywheel() {
    flywheelMotor = new CANSparkMax(9, MotorType.kBrushless);
    flywheelSlave = new CANSparkMax(10, MotorType.kBrushless);
    flywheelSlave.follow(flywheelMotor, true);
    flywheelEncoder = flywheelMotor.getEncoder();

    flywheelController = flywheelMotor.getPIDController();
    flywheelController.setP(kP);
    flywheelController.setFF(kV);

    controller = new PIDController(kP, 0.0, 0.0);
  }

  public void start() {
    flywheelController.setReference(-setpoint, ControlType.kVelocity);
  }

  public void stop() {
    flywheelMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

}
