package org.usfirst.frc.team5338.robot.subsystems;

import org.usfirst.frc.team5338.robot.OI;
import org.usfirst.frc.team5338.robot.OI.GearLiftState;
import org.usfirst.frc.team5338.robot.OI.GearMotorState;
import org.usfirst.frc.team5338.robot.commands.PickGears;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearPicker extends Subsystem {
    private final DoubleSolenoid LIFT = new DoubleSolenoid(3, 4);
    private final CANTalon INTAKE = new CANTalon(8, 1);

    public GearPicker() {
	super();
    }

    public void initDefaultCommand() {
	setDefaultCommand(new PickGears());
    }

    public void pickGears(OI oi) {
	if (oi.get(OI.Button.GEAR_PICK_INTAKE)) {
	    oi.gearMotorState = GearMotorState.INTAKE;
	} else if (oi.get(OI.Button.GEAR_PICK_OUTTAKE)) {
	    oi.gearMotorState = GearMotorState.OUTTAKE;
	} else {
	    oi.gearMotorState = GearMotorState.HOLD;
	}
	if (oi.get(OI.Button.POV)) {
	    oi.gearLiftState = GearLiftState.RAISED;
	} else {
	    oi.gearLiftState = GearLiftState.LOWERED;
	}
	switch (oi.gearLiftState) {
	case RAISED:
	    LIFT.set(DoubleSolenoid.Value.kReverse);
	    break;
	case LOWERED:
	    LIFT.set(DoubleSolenoid.Value.kForward);
	    break;
	}
	switch (oi.gearMotorState) {
	case HOLD:
	    INTAKE.set(0.25);
	    Timer.delay(0.50);
	    INTAKE.set(0.0);
	    Timer.delay(3.0);
	    break;
	case INTAKE:
	    INTAKE.set(0.99);
	    break;
	case OUTTAKE:
	    INTAKE.set(-0.99);
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
