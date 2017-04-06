package org.usfirst.frc.team5338.robot;

import org.usfirst.frc.team5338.robot.commands.Autonomous;
import org.usfirst.frc.team5338.robot.subsystems.BallHandler;
import org.usfirst.frc.team5338.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5338.robot.subsystems.GearHandler;
import org.usfirst.frc.team5338.robot.subsystems.GearPicker;
import org.usfirst.frc.team5338.robot.subsystems.Wincher;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static SendableChooser<String> autonomousChooser;

	public static final OI oi = new OI();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final BallHandler ballhandler = new BallHandler();
	public static final Wincher wincher = new Wincher();
	public static final GearHandler gearhandler = new GearHandler();
	public static final GearPicker gearpicker = new GearPicker();

	private static Command autonomousCommand;

	public static final AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte) (200));

	@Override
	public void robotInit() {
		while (ahrs.isCalibrating() || ahrs.isMagnetometerCalibrated()) {
		}

	}

	@Override
	public void autonomousInit() {
		autonomousCommand = new Autonomous();
		autonomousCommand.start(); // schedule the autonomous command (example)
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		try {
			autonomousCommand.cancel();
		} catch (Exception e) {
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
