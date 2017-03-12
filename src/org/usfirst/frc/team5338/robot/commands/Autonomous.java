package org.usfirst.frc.team5338.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup
{
	public Autonomous()
	{
		switch("CENTER")
		{
			case "CENTER":
				addSequential(new Move(4));
				addSequential(new DepositGear());
//				addSequential(new Turn(90));
//				addSequential(new Move(1));
//				addSequential(new Turn(-90));
//				addSequential(new Move(4));
				break;
			case "LEFT":
				addSequential(new Move(6));
				addSequential(new DepositGear());
				addSequential(new Turn(-60));
				addSequential(new Move(3));
				break;
			case "RIGHT":
				addSequential(new Move(6));
				addSequential(new DepositGear());
				addSequential(new Turn(60));
				addSequential(new Move(3));
				break;
			case "TEST":
				addSequential(new Turn(360));
				addSequential(new Turn(-360));
				break;
			case "BASELINE":
				addSequential(new Move(6));
				break;
			default:
				break;
		}
	}
}