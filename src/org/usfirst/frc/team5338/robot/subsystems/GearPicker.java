package org.usfirst.frc.team5338.robot.subsystems;

import org.usfirst.frc.team5338.robot.OI;
import org.usfirst.frc.team5338.robot.commands.HandleGears;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearPicker extends Subsystem
{
    private final DoubleSolenoid DOOR = new DoubleSolenoid(3, 4); 

    public GearPicker()
    {
	super();
    }
    public void initDefaultCommand()
    {
	setDefaultCommand(new HandleGears());
    }
    public void pickGears(OI oi)
    {
	if(oi.get(OI.Button.GEAR_DEPOSIT))
	{
	    DOOR.set(DoubleSolenoid.Value.kReverse);
	}
	else
	{
	    DOOR.set(DoubleSolenoid.Value.kForward);
	}
    }
    public void setGears(DoubleSolenoid.Value setting){
	DOOR.set(setting);
    }
}

