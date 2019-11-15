/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.commands.DriveWithPercentManual;
import frc.robot.RobotMap;
import harkerrobolib.subsystems.HSDrivetrain;
import harkerrobolib.wrappers.HSTalon;

/**
 * Add your docs here.
 */
public class Drivetrain extends HSDrivetrain {
    
  private static Drivetrain instance;
  
  private static final boolean LEFT_TALON_INVERT = true;
  private static final boolean RIGHT_TALON_INVERT = false;
  private static final boolean LEFT_VICTOR_INVERT = true;
  private static final boolean RIGHT_VICTOR_INVERT = false;


  private Drivetrain() {
    super(
      new HSTalon(RobotMap.LEFT_TALON_ID_DRIVETRAIN), 
      new HSTalon(RobotMap.RIGHT_TALON_ID_DRIVETRAIN),
      new VictorSPX(RobotMap.LEFT_VICTOR_ID_DRIVETRAIN), 
      new VictorSPX(RobotMap.RIGHT_VICTOR_ID_DRIVETRAIN)
    );

    // Set up motors
    this.setUpMotors();

    this.configVoltageComp();
  }

  // Set up motors
  private void setUpMotors()
  {
    getLeftMaster().setInverted(LEFT_TALON_INVERT);
    getRightMaster().setInverted(RIGHT_TALON_INVERT);
    getLeftFollower().setInverted(LEFT_VICTOR_INVERT);
    getRightFollower().setInverted(RIGHT_VICTOR_INVERT);

    getLeftFollower().follow(getLeftMaster());
    getRightFollower().follow(getRightMaster());

    getLeftMaster().setNeutralMode(NeutralMode.Brake);
    getRightMaster().setNeutralMode(NeutralMode.Brake);

    configVoltageComp();
    configCurrentLimit();
  }

  /**
   * Configures voltage compensation
   */
  private void configVoltageComp()
  {
    /*
    getLeftMaster().configVoltageCompSaturation(COMPENSATION_VOLTAGE);
    getLeftMaster().enableVoltageCompensation(true);

    getRightMaster().configVoltageCompSaturation(COMPENSATION_VOLTAGE);
    getRightMaster().enableVoltageCompensation(true);
    */
  }

  /**
   * Configures current limitation
   */
  private void configCurrentLimit()
  {
    /*
    getLeftMaster().configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
    getLeftMaster().configPeakCurrentDuration(PEAK_TIME);
    getLeftMaster().configPeakCurrentLimit(PEAK_CURRENT_LIMIT);
    getLeftMaster().enableCurrentLimit(true);

    getRightMaster().configContinuousCurrentLimit(CONTINUOUS_CURRENT_LIMIT);
    getRightMaster().configPeakCurrentDuration(PEAK_TIME);
    getRightMaster().configPeakCurrentLimit(PEAK_CURRENT_LIMIT);
    getRightMaster().enableCurrentLimit(true);
     */
  }

  @Override
  public void initDefaultCommand() {
    this.setDefaultCommand(new DriveWithPercentManual());
  }

  public static Drivetrain getInstance()
  {
    if(instance == null)
      instance = new Drivetrain();
    return instance;
  }
}
