package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends PIDCommand
{
    public Turn(int angle)
    {
	super(0.1, 0.1, 0.1, 0.005);
	requires(Robot.drivetrain);
	getPIDController().setAbsoluteTolerance(0.5);
	getPIDController().setToleranceBuffer(5);
	getPIDController().setOutputRange(0.0, 0.50);;
	double targetHeading = (Robot.ahrs.getFusedHeading() + angle) % 360;
	if(targetHeading % 360 < 0)
	{
	    setSetpoint(360 + (targetHeading % 360));
	}
	else
	{
	    setSetpoint(targetHeading % 360);
	}
	SmartDashboard.putData("PID Name", getPIDController());
	SmartDashboard.putNumber("Starting Heading", Robot.ahrs.getFusedHeading());
	setTimeout(2);
    }
    protected void execute()
    {
    }
    protected boolean isFinished()
    {
	return isTimedOut() || getPIDController().onTarget();
    }
    protected void end()
    {
	SmartDashboard.putNumber("Finished Heading", Robot.ahrs.getFusedHeading());
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
