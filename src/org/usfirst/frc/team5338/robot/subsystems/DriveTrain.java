package org.usfirst.frc.team5338.robot.subsystems;

import org.usfirst.frc.team5338.robot.OI;
import org.usfirst.frc.team5338.robot.Robot;
import org.usfirst.frc.team5338.robot.OI.DriveState;
import org.usfirst.frc.team5338.robot.commands.TankDriveWithJoysticks;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem incorporates the sensors and actuators attached to
 * the robots chassis. These include four drive motors.
 */
public class DriveTrain extends Subsystem
{
	public final CANTalon DRIVEL1 = new CANTalon(4);
    public final CANTalon DRIVEL2 = new CANTalon(3);
    public final CANTalon DRIVER1 = new CANTalon(2);
    public final CANTalon DRIVER2 = new CANTalon(1);
	
	public final RobotDrive DRIVE = new RobotDrive(DRIVEL1, DRIVEL2, DRIVER1, DRIVER2);

	private double throttle = 1.0;

	public DriveTrain()
	{
		super();
	}
	/**
	 * When no other command is running let the operator drive around using the
	 * twin joysticks.
	 */
	@Override
	public void initDefaultCommand()
	{
		setDefaultCommand(new TankDriveWithJoysticks());
	}
	public void drive(OI oi)
	{
		if(oi.get(OI.Button.REVERSE))
    	{
    		oi.driveState = DriveState.REVERSE;
    	}
    	else if(oi.get(OI.Button.FORWARD))
    	{
    		oi.driveState = DriveState.FORWARD;
    	}
		
		if(oi.get(OI.Button.JETSON_RESET))
		{
			Robot.jetsonReset.set(Relay.Value.kOn);
		}
		else
		{
			Robot.jetsonReset.set(Relay.Value.kOff);
		}
		
		if(oi.get(OI.Button.SLOW))
		{
			throttle = 0.5;
		}
		else
		{
			throttle = 1.0;
		}
		
		switch(oi.driveState)
    	{
		case FORWARD:
			if(oi.get(OI.Button.STRAIGHT))
			{
				drive(oi.getRight(), oi.getRight());
			}
			else
			{
				DRIVE.tankDrive(-throttle * oi.getLeft(), -throttle * oi.getRight(), false);
			}
		case REVERSE:
			if(oi.get(OI.Button.STRAIGHT))
			{
				drive(-oi.getRight(), -oi.getRight());
			}
			else
			{
		    DRIVE.tankDrive(throttle * oi.getLeft(), throttle * oi.getRight(), false);
			}	
    	default:
    		drive(0.0, 0.0);
    	}
	}
	public void drive(double left, double right)
	{
		DRIVE.tankDrive(throttle * -left, -throttle * right, false);
	}
}