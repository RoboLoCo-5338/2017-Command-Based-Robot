package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command
{
    double rotateAng;
    public Turn(int angle)
    {
			Robot.ahrs.reset();
			Robot.ahrs.zeroYaw();
			Robot.turnController.setSetpoint(angle);
			Robot.turnController.enable();
			rotateAng=0;
		//
    // 	degrees = angle*12/13;
		// requires(Robot.drivetrain);
		setTimeout(3);
     }
    protected void execute()
    {
    	 rotateAng = (rotateAng*0.8)+0.2*(Robot.rotateToAngleRate);
			Robot.drivetrain.drive(-rotateAng*0.5,rotateAng*0.5);

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
    	return isTimedOut() || Math.abs(Robot.ahrs.getAngle() - Robot.turnController.getSetpoint()) < Robot.kToleranceDegrees;
			//2.0 is the tolerance in degrees
    }
    protected void end()
    {
    	Robot.drivetrain.drive(0.0, 0.0);
    	Robot.turnController.disable();
    	Robot.ahrs.reset();
    }
}
