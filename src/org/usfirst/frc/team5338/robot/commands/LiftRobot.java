package org.usfirst.frc.team5338.robot.commands;

import org.usfirst.frc.team5338.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftRobot extends Command
{
    public LiftRobot()
    {
	requires(Robot.winch);
    }
    @Override
    protected void execute()
    {
	Robot.winch.liftRobot(Robot.oi);
    }
    @Override
    protected boolean isFinished()
    {
	return false;
    }
    @Override
    protected void end()
    {
	Robot.winch.stopLift();
    }
}