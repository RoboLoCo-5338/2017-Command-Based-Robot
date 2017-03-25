package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Turn extends PIDCommand
{
    public Turn(int angle)
    {
	super(0.05, 0.05, 0.15, 0.005);
	requires(Robot.drivetrain);
	setTimeout(3);
	double targetHeading = (Robot.ahrs.getFusedHeading() + angle) % 360;
	if(targetHeading % 360 < 0)
	{
	    setSetpoint(360 + (targetHeading % 360));
	}
	else
	{
	    setSetpoint(targetHeading % 360);
	}
    }
    protected void execute()
    {
    }
    protected boolean isFinished()
    {
	return isTimedOut();
    }
    protected void end()
    {
	Robot.drivetrain.drive(0.0, 0.0);
    }
    protected double returnPIDInput()
    {
	return Robot.ahrs.getFusedHeading();
    }
    protected void usePIDOutput(double output)
    {
	Robot.drivetrain.drive(-output * 0.50, output * 0.50);
    }
}
