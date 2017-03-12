package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command
{
	int degrees;
    public Turn(int angle)
    {
    	degrees = angle;
		requires(Robot.drivetrain);
		setTimeout((int)(Math.ceil(Math.abs(degrees / 72.5))));
    }
    protected void execute()
    {
    	if(timeSinceInitialized() <= Math.abs(degrees / 72.5))
    	{
    		if(degrees > 0)
    		{
    			Robot.drivetrain.drive(0.25, -0.25);
    		}
    		else
    		{
    			Robot.drivetrain.drive(-0.25, 0.25);
    		}
    	}
    	else
    	{
    		Robot.drivetrain.drive(0,0);
    	}
    }
    protected boolean isFinished()
    {
    	return isTimedOut();
    }
    protected void end()
    {
    	Robot.drivetrain.drive(0.0, 0.0);
    }
}