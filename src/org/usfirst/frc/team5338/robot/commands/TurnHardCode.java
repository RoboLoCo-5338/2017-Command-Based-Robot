package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnHardCode extends Command{
    int seconds;

    public TurnHardCode(int input) {
	seconds = input;
	requires(Robot.drivetrain);
	setTimeout(Math.abs(seconds));
    }

    protected void execute() {
	if (seconds > 0) {
	    Robot.drivetrain.drive(-0.25, 0.25);
	} else {
	    Robot.drivetrain.drive(0.25, -0.25);
	}
    }

    protected boolean isFinished() {
	return isTimedOut();
    }

    protected void end() {
	Robot.drivetrain.drive(0.0, 0.0);
    }

}