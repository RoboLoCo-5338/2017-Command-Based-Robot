package org.usfirst.frc.team5338.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Autonomous extends CommandGroup
{
	public Autonomous()
	{
		switch("LEFT")
		{
			case "LEFT":
				addSequential(new Move(5));
				addSequential(new DepositGear());
				/*addSequential(new Move(bdist));
				addSequential(new Turn(theta));
				addSequential(new Move(fdist2));
				addSequential(new DepositGear());*/
				break;
			case "CENTER":
				addSequential(new Move(2));
				addSequential(new DepositGear());
				break;
			case "RIGHT":
				addSequential(new Move(3));
				/*addSequential(new Move(bdist));
				addSequential(new Turn(theta));
				addSequential(new Move(fdist2));
				addSequential(new DepositGear());*/
				break;
			default:
				break;
		}
	}
}