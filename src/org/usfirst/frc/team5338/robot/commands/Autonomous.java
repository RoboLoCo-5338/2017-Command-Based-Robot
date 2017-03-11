package org.usfirst.frc.team5338.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Autonomous extends CommandGroup
{
	private static final String placement = (SmartDashboard.getString("Placement =", ""));
		
	private int fdist = 0;
	private int bdist = 0;
	private int theta = 0;
	private int fdist2 = 0;
	public Autonomous()
	{
		switch(placement)
		{
			case "left":
				addSequential(new Move(fdist));
				addSequential(new Move(bdist));
				addSequential(new Turn(theta));
				addSequential(new Move(fdist2));
				addSequential(new DepositGear());
				break;
			case "center":
				addSequential(new Move(fdist));
				addSequential(new DepositGear());
				break;
			case "right":
				addSequential(new Move(fdist));
				addSequential(new Move(bdist));
				addSequential(new Turn(theta));
				addSequential(new Move(fdist2));
				addSequential(new DepositGear());
				break;
			default:
				break;
		}
	}
}