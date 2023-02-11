// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Serializer extends SubsystemBase {
  /** Creates a new Serializer. */
  private CANSparkMax serializerMotor;
  private CANSparkMax serializerMotor2;

  

  public Serializer() {
    serializerMotor = new CANSparkMax(motorID, MotorType.kBrushless);
    serializerMotor2 = new CANSparkMax(motorID2, MotorType.kBrushless);
  }

  public void runMotor(double speed, double speed2) {
    serializerMotor.set(speed);
    serializerMotor2.set(speed2);
  }

  public void stop() {
    serializerMotor.set(0);
    serializerMotor2.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
