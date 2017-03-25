package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Turn extends PIDCommand
{
    public Turn(int angle)
    {
	super(0.0, 0.0, 0.0, 0.005);
	requires(Robot.drivetrain);
	setTimeout(3);
	setSetpoint(Robot.ahrs.getFusedHeading() + angle);
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
	Robot.drivetrain.drive(output, -output);
    }
}
