package org.usfirst.frc.team5338.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI
{
	private final Joystick joyL = new Joystick(0);
	private final Joystick joyR = new Joystick(1);
	
	public BallState ballState; 
	public DriveState driveState;
	
	public enum Button
	{
		OFF, LOWER_INTAKE, UPPER_INTAKE, OUTTAKE,
		SLOW, STRAIGHT, REVERSE, FORWARD,
		GEAR_OPEN, GEAR_CLOSE, WINCH
	}
	public enum BallState
	{
		OFF, UPPER_INTAKE, LOWER_INTAKE, OUTTAKE
	}
	public enum DriveState
	{
		REVERSE, FORWARD
	}
	
	public OI()
	{
		ballState = BallState.OFF;
		driveState = DriveState.FORWARD;
	}	
	public Joystick getJoystick(int n)
	{
		if(n == 0)
			return joyL;
		else if(n == 1)
			return joyR;
		else
			return null;
	}
	public boolean get(Button button)
	{
		switch(button)
		{
		case OFF: 			
			return joyR.getRawButton(3);
		case LOWER_INTAKE:
			return joyR.getRawButton(6);
		case UPPER_INTAKE: 
			return joyR.getRawButton(4);
		case OUTTAKE: 	
			return joyR.getRawButton(5);
		
		case SLOW: 		
			return joyL.getRawButton(1);
		case STRAIGHT:	
			return joyR.getRawButton(1);
		case REVERSE: 	
			return joyL.getRawButton(2);
		case FORWARD: 	
			return joyR.getRawButton(2);
		
		case GEAR_OPEN: 
			return joyL.getRawButton(3);
		case GEAR_CLOSE: 
			return joyL.getRawButton(6);
		case WINCH: 	
			return joyL.getRawButton(4) && joyR.getRawButton(6);
		
		default: 			
			return false;
		}
	}
	private double joystickDeadZone(double value)
	{
		if (value > 0.04 || value < -0.04)
		{
		 return (value - 0.04)/0.96;
		}
		else if (value < -0.04)
		{
		 return (value + 0.04)/0.96;
		}
		return 0.0;
	}	
	public double getLeft()
	{
		return joystickDeadZone(joyL.getRawAxis(1));
	}
	
	public double getRight()
	{
		return joystickDeadZone(joyR.getRawAxis(1));
	}
}