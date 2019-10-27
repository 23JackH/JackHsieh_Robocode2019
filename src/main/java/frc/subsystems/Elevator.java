/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import harkerrobolib.wrappers.HSTalon;
import frc.commands.ElevateWithPercentManual;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  
  private static Elevator instance;

  private static final boolean MASTER_TALON_INVERT = false;
  private static final boolean FOLLOWER_TALON_INVERT = false;
  private static final boolean LEFT_VICTOR_INVERT = false;
  private static final boolean RIGHT_VICTOR_INVERT = false;

  // TODO: Ask about variable names
  private HSTalon masterTalon;
  private HSTalon followerTalon;
  private VictorSPX leftVictor;
  private VictorSPX rightVictor;

  private Elevator()
  {
    this.initializeMotors();
    
    this.setUpMotors();
  }

  /**
   * Initializes motors
   */
  private void initializeMotors()
  {
    masterTalon = new HSTalon(RobotMap.MASTER_TALON_ID_ELEVATOR);
    followerTalon = new HSTalon(RobotMap.FOLLOWER_TALON_ID_ELEVATOR);
    leftVictor = new VictorSPX(RobotMap.LEFT_VICTOR_ID_ELEVATOR);
    rightVictor = new VictorSPX(RobotMap.RIGHT_VICTOR_ID_ELEVATOR);
  }

  /**
   * Sets up motors
   */
  private void setUpMotors()
  {
    getMasterTalon().setInverted(MASTER_TALON_INVERT);
    getFollowerTalon().setInverted(FOLLOWER_TALON_INVERT);
    getLeftVictor().setInverted(LEFT_VICTOR_INVERT);
    getRightVictor().setInverted(RIGHT_VICTOR_INVERT);
  
    getFollowerTalon().follow(getMasterTalon());
    getLeftVictor().follow(getMasterTalon());
    getRightVictor().follow(getMasterTalon());
    
    getMasterTalon().setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    this.setDefaultCommand(new ElevateWithPercentManual());
  }

  /**
   * @return Instance of Elevator 
   */
  public static Elevator getInstance()
  {
    if(instance == null)
      instance = new Elevator();
    return instance;
  }

  // Getters
  public HSTalon getMasterTalon()
  {
    return masterTalon;
  }

  public HSTalon getFollowerTalon()
  {
    return followerTalon;
  }

  public VictorSPX getLeftVictor()
  {
    return leftVictor;
  }

  public VictorSPX getRightVictor()
  {
    return rightVictor;
  }

}