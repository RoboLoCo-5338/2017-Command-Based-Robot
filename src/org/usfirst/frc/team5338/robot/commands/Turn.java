package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command implements PIDOutput
{
    private double rotateAng = 0;
	public static double rotateToAngleRate;
    private static PIDController turnController;
	private static final double kP = 0.03;
	private static final double kI = 0.00;
	private static final double kD = 0.000001;
	private static final double kF = 0.00;
	public static final double kToleranceDegrees = 1.0f;
    public Turn(int angle)
    {
    	requires(Robot.drivetrain);
		setTimeout(3);
    	turnController = new PIDController(kP, kI, kD, kF, Robot.ahrs, this, 0.01);
		turnController.setInputRange(0.0f, 360.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);
		Robot.ahrs.reset();
		Robot.ahrs.zeroYaw();
		turnController.setSetpoint(angle + Robot.ahrs.getFusedHeading());
		turnController.enable();
		//
    // 	degrees = angle*12/13;	
     }
    protected void execute()
    {
    	 rotateAng = (rotateAng * 0.6) + 0.4 * (rotateToAngleRate);
    	 Robot.drivetrain.drive(-rotateAng * 0.5,rotateAng * 0.5);
    	// if(timeSinceInitialized() <= Math.abs(degrees / 72.5))
    	// {
    	// 	if(degrees > 0)
    	// 	{
    	// 		Robot.drivetrain.drive(0.25, -0.25);
    	// 	}
    	// 	else
    	// 	{
    	// 		Robot.drivetrain.drive(-0.25, 0.25);
    	// 	}
    	// }
    	// else
    	// {
    	// 	Robot.drivetrain.drive(0,0);
    	//}
    }
    protected boolean isFinished()
    {
    	return isTimedOut() || turnController.onTarget();
    }
    protected void end()
    {
    	Robot.drivetrain.drive(0.0, 0.0);
    	turnController.disable();
    	Robot.ahrs.reset();
		Robot.ahrs.zeroYaw();
    }
	public void pidWrite(double output)
	{
		rotateToAngleRate = output;
	}
}
