package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Move extends Command 
{
	int time;
    public Move(int distance)
    {
    	time = distance;
		requires(Robot.drivetrain);
		setTimeout(Math.abs(time));
	}
    protected void execute()
    {
    	if(time > 0)
    	{
    		Robot.drivetrain.drive(-0.25, -0.25);
    	}
    	else
    	{
    		Robot.drivetrain.drive(0.25, 0.25);
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