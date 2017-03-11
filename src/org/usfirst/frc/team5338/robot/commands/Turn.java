package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command
{
	int time;
    public Turn(int angle)
    {
    	time = angle;
		requires(Robot.drivetrain);
		setTimeout(Math.abs(time));
    }
    protected void execute()
    {
    	if(time > 0)
    	{
    		Robot.drivetrain.drive(0.50, -0.50);
    	}
    	else
    	{
    		Robot.drivetrain.drive(-0.50, 0.50);
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