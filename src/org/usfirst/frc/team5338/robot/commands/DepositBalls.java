package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DepositBalls extends Command {
    public DepositBalls() {
	requires(Robot.ballhandler);
	setTimeout(4);
    }

    protected void execute() {
	Robot.ballhandler.setBalls(0.75, 0.75);
    }

    protected boolean isFinished() {
	return isTimedOut();
    }

    protected void end() {
	Robot.drivetrain.drive(0.0, 0.0);
    }
}