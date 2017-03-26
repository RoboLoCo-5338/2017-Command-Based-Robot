package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends PIDCommand
{
    public Turn(int angle)
    {
	super(0.05, 0.5, 0.05, 0.005);
	requires(Robot.drivetrain);
	Robot.ahrs.reset();
	Robot.ahrs.zeroYaw();
	getPIDController().setPercentTolerance(1.0);
	getPIDController().setOutputRange(0.0, 0.25);
	setInputRange(0.0, 360.0);
	getPIDController().setContinuous();
	double targetHeading = ((double)(Robot.ahrs.getCompassHeading()) + angle) % 360.0;
	if(targetHeading < 0)
	{
	    setSetpoint(360.0 + targetHeading);
	}
	else
	{
	    setSetpoint(targetHeading);
	}
	setTimeout(4);
    }
    protected void execute()
    {
	SmartDashboard.putNumber("HEADING", Robot.ahrs.getCompassHeading());
	SmartDashboard.putNumber("PID VALUE", getPosition());
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
	return Robot.ahrs.getCompassHeading();
    }
    protected void usePIDOutput(double output)
    {
	Robot.drivetrain.drive(-output, output);
    }
}
