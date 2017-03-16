package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command
{
	int degrees;
    public Turn(int angle)
    {
			Robot.ahrs.ZeroYaw();
			Robot.ahrs.SetSetpoint(angle);
			Robot.ahrs.Enable();

		//
    // 	degrees = angle*12/13;
		// requires(Robot.drivetrain);
		// setTimeout((int)(Math.ceil(Math.abs(degrees / 72.5))));
    // }

    protected void execute()
    {
			Robot.drivetrain.drive(-0.25 * Robot.rotateToAngleRate, 0.25 * Robot.rotateToAngleRate);

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
    	// }
    }
    protected boolean isFinished(int angle, int tolerance)
    {
    	return Abs(Robot.ahrs.getAngle() - angle) < tolerance;
    }
    protected void end()
    {
    	Robot.drivetrain.drive(0.0, 0.0);
    }
}
