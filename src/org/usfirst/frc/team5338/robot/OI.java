package org.usfirst.frc.team5338.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	private final Joystick joyL = new Joystick(0);
	private final Joystick joyR = new Joystick(1);
	public DriveState driveState;

	public enum Button {
		OFF, UPPER_INTAKE, OUTTAKE, SLOW, STRAIGHT, REVERSE, FORWARD, GEAR_DEPOSIT, WINCH, GEAR_PICK_INTAKE, GEAR_PICK_OUTTAKE
	}

	public enum DriveState {
		REVERSE, FORWARD
	}

	public OI() {
		driveState = DriveState.FORWARD;
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
			return joyR.getRawButton(5);
		case UPPER_INTAKE:
			return joyR.getRawButton(6);
		case OUTTAKE:
			return joyR.getRawButton(3);
		case SLOW:
			return joyR.getRawButton(1);
		case STRAIGHT:
			return joyL.getRawButton(1);
		case REVERSE:
			return joyL.getRawButton(2);
		case FORWARD:
			return joyR.getRawButton(2);
		case GEAR_DEPOSIT:
			return joyL.getRawButton(5);
		case WINCH:			return joyL.getRawButton(6);
		case GEAR_PICK_INTAKE:
			return joyL.getRawButton(4);
		case GEAR_PICK_OUTTAKE:
			return joyL.getRawButton(3);
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
		return joystickDeadZone(joyL.getRawAxis(1));
	}

	public double getRight() {
		return joystickDeadZone(joyR.getRawAxis(1));
	}
}
