/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.OI;
import frc.subsystems.Elevator;
import harkerrobolib.commands.IndefiniteCommand;

public class ElevateWithPercentManual extends IndefiniteCommand {
 
  private static final double SPEED_MULTIPLIER = 0.25;

  public ElevateWithPercentManual() {
    requires(Elevator.getInstance());
  }

  /*
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }*/

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double speed = OI.getInstance().getDriver().getRightY();

    // Uses utility function to set motion style
    Elevator.getInstance().setElevator(SPEED_MULTIPLIER * speed);
    // Elevator.getInstance().getMasterTalon().set(ControlMode.MotionMagic, lastSetpoint, DemandType.ArbitraryFeedForward, Elevator.GRAVITY_FF);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Elevator.getInstance().getMasterTalon().set(ControlMode.Disabled, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
