package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Gear extends Command {
	
	double dps = 1; //degrees per second at max speed
	double timefordist = 0;
	public Gear()
	{
		requires(Robot.drivetrain);
		requires(Robot.gearhandler);
		//requires(Robot.table);
		setTimeout(12);
	}

	protected double angle()
	{
		//TODO
		return     0;//     Math.atan(Robot.table.getValue("x")/1108.5) * (180/Math.PI);
	}
	
	protected void execute()
	{
		//TODO
		while(angle() > 5)
		{
			Robot.drivetrain.drive(-0.5, 0.5);
			Timer.delay(.5);
		}
		while(angle() < -5)
		{
			Robot.drivetrain.drive(0.5, -0.5);
		}
//		while(Robot.table.getValue("width") != null){
//			Robot.drivetrain.tank(0.5, 0.5);
//			Timer.delay(.5);
//		}
		Robot.drivetrain.drive(-0.5, -0.5);
		Timer.delay(2);
		end();
	}
	protected boolean isFinished()
	{
		return isTimedOut();
	}
	protected void end()
	{
		Robot.drivetrain.drive(0, 0);
	}
}