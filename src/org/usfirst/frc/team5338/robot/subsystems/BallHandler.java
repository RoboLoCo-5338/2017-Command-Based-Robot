package org.usfirst.frc.team5338.robot.subsystems;

import org.usfirst.frc.team5338.robot.OI;
import org.usfirst.frc.team5338.robot.commands.HandleBalls;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BallHandler extends Subsystem {
    private final CANTalon BOTTOM = new CANTalon(5);
    private final CANTalon TOP = new CANTalon(6);

    public BallHandler() {
	super();
	TOP.enable();
	BOTTOM.enable();
    }

    public void initDefaultCommand() {
	setDefaultCommand(new HandleBalls());
    }

    public void handleBalls(OI oi) {
	if (oi.get(OI.Button.OUTTAKE)) {
		TOP.set(0.75);
	    BOTTOM.set(0.75);
	} else if (oi.get(OI.Button.UPPER_INTAKE)) {
		TOP.set(0.75);
	    BOTTOM.set(-0.75);
	} else {
		stopBalls();
    }
    }

    public void stopBalls() {
	TOP.set(0.0);
	BOTTOM.set(0.0);
    }
    
    public void setBalls(double top, double bottom)
    {
    	TOP.set(top);
    	BOTTOM.set(bottom);
    }
}
