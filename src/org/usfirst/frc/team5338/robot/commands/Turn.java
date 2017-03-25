package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Turn extends PIDCommand
{
    public Turn(int angle)
    {
	super(0.1, 0.1, 0.1, 0.005);
	requires(Robot.drivetrain);
	getPIDController().setAbsoluteTolerance(1.0);
	getPIDController().setToleranceBuffer(3);
	getPIDController().setOutputRange(0.0, 0.75);
	double targetHeading = (Robot.ahrs.getFusedHeading() + angle) % 360;
	if(targetHeading < 0)
	{
	    setSetpoint(360 + targetHeading);
	}
	else
	{
	    setSetpoint(targetHeading);
	}
	setTimeout(5);
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
	Robot.drivetrain.drive(-output, output);
    }
}
