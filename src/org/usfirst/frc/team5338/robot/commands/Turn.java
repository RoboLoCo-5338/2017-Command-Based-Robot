package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command
{
    private double rotateAng = 0;
    public Turn(int angle)
    {
		requires(Robot.drivetrain);
		setTimeout(3);
		Robot.ahrs.reset();
		Robot.ahrs.zeroYaw();
		Robot.turnController.setSetpoint(angle);
		Robot.turnController.enable();
		//
    // 	degrees = angle*12/13;
		
     }
    protected void execute()
    {
    	 rotateAng = (rotateAng*0.6) + 0.4*(Robot.rotateToAngleRate);
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
    	return isTimedOut() || Robot.turnController.onTarget();
			//2.0 is the tolerance in degrees
    }
    protected void end()
    {
    	Robot.drivetrain.drive(0.0, 0.0);
    	Robot.turnController.disable();
    	Robot.ahrs.reset();
		Robot.ahrs.zeroYaw();
    }
}
