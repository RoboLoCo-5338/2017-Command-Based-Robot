package org.usfirst.frc.team5338.robot.subsystems;

import org.usfirst.frc.team5338.robot.OI;
import org.usfirst.frc.team5338.robot.OI.GearMotorState;
import org.usfirst.frc.team5338.robot.commands.PickGears;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearPicker extends Subsystem {
    private final DoubleSolenoid LIFT = new DoubleSolenoid(7, 6);
    private final CANTalon INTAKE = new CANTalon(8);

    public GearPicker() {
	super();
    }

    public void initDefaultCommand() {
	setDefaultCommand(new PickGears());
    }

    public void pickGears(OI oi) {
	if (oi.get(OI.Button.GEAR_PICK_INTAKE)) {
	    oi.gearMotorState = GearMotorState.INTAKE;
	    LIFT.set(DoubleSolenoid.Value.kReverse);

	} else if (oi.get(OI.Button.GEAR_PICK_OUTTAKE)) {
	    oi.gearMotorState = GearMotorState.OUTTAKE;
	    LIFT.set(DoubleSolenoid.Value.kReverse);

	} else {
	    oi.gearMotorState = GearMotorState.HOLD;
	    LIFT.set(DoubleSolenoid.Value.kForward);

	}
	
	switch (oi.gearMotorState) {
	case HOLD:
	    INTAKE.set(-0.05);
	    break;
	case INTAKE:
	    INTAKE.set(-0.5);
	    break;
	case OUTTAKE:
	    INTAKE.set(1);
	    break;
	default:
	    stopGears();
	}
    }

    public void setGears(DoubleSolenoid.Value setting) {
	LIFT.set(setting);
    }

    public void stopGears() {
	INTAKE.set(0.0);
    }
}
