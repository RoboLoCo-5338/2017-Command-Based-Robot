package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends PIDCommand
{
    public Turn(int angle)
    {
	super(0.10, 0.5, 0.10, 0.005);
	requires(Robot.drivetrain);
	getPIDController().setPercentTolerance(1.0);
	getPIDController().setOutputRange(0.0, 0.50);
	getPIDController().setInputRange(0.0, 360.0);
	getPIDController().setContinuous();
//	double targetHeading = (Robot.ahrs.getFusedHeading() + angle) % 360;
//	if(targetHeading < 0)
//	{
//	    setSetpoint(360 + targetHeading);
//	}
//	else
//	{
	    setSetpoint(180.0);
	//}
	setTimeout(4);
    }
    protected void execute()
    {
	SmartDashboard.putNumber("HEADING", Robot.ahrs.getFusedHeading());
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
