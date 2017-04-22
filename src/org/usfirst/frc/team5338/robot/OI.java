package org.usfirst.frc.team5338.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    private final Joystick joyL = new Joystick(0);
    private final Joystick joyR = new Joystick(1);
    public BallState ballState;
    public DriveState driveState;
    static public boolean kidMode = false;

    public enum Button {
	OFF, UPPER_INTAKE, OUTTAKE, SLOW, STRAIGHT, REVERSE, FORWARD, GEAR_DEPOSIT, WINCH, GEAR_PICK_INTAKE, GEAR_PICK_OUTTAKE, KID_MODE_ON, KID_MODE_OFF
    }

    public enum BallState {
	OFF, UPPER_INTAKE, OUTTAKE
    }

    public enum DriveState {
	REVERSE, FORWARD
    }

    public OI() {
	ballState = BallState.OFF;
	driveState = DriveState.REVERSE;
    }

    public Joystick getJoystick(int n) {
	if (n == 0)
	    return joyL;
	else if (n == 1)
	    return joyR;
	else
	    return null;
    }

    public boolean get(Button button) {
	switch (button) {
	case OFF:
		if (kidMode) return false;
		
	    return joyR.getRawButton(5);
	case UPPER_INTAKE:
		if (kidMode) return false;

	    return joyR.getRawButton(6);
	case OUTTAKE:
		if (kidMode) return false;

	    return joyR.getRawButton(3);
	case SLOW:
		if (kidMode) return false;

	    return joyR.getRawButton(1);
	case STRAIGHT:
		if (kidMode) return false;

	    return joyL.getRawButton(1);
	case REVERSE:
		if (kidMode) return false;

	    return joyL.getRawButton(2);
	case FORWARD:
		if (kidMode) return false;

	    return joyR.getRawButton(2);
	case GEAR_DEPOSIT:
	    return joyL.getRawButton(5);
	case WINCH:
		if (kidMode) return false;

	    return joyL.getRawButton(6);
	case GEAR_PICK_INTAKE:
	    return joyL.getRawButton(4);
	case GEAR_PICK_OUTTAKE:
	    return joyL.getRawButton(3);
	case KID_MODE_ON:
	    return joyR.getRawButton(7);
	case KID_MODE_OFF:
	    return joyR.getRawButton(8);
	default:
	    return false;
	}
    }

    private double joystickDeadZone(double value) {
	if (value > 0.05) {
	    return (value - 0.05) / 0.95;
	} else if (value < -0.05) {
	    return (value + 0.05) / 0.95;
	}
	return value;
    }

    public double getLeft() {
	double a = joystickDeadZone(joyL.getRawAxis(1));
	if(kidMode) 
		if(a<-.3)
			return -.3;
		else if (a>.3)
			return .3;
		else
			return a;
	else
		return a;
    }

    public double getRight() {
	double a= joystickDeadZone(joyR.getRawAxis(1));
	if(kidMode) 
		if(a<-.3)
			return -.3;
		else if (a>.3)
			return .3;
		else
			return a;
	else
		return a;
    }
}
