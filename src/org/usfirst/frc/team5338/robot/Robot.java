package org.usfirst.frc.team5338.robot;

import org.usfirst.frc.team5338.robot.commands.Autonomous;
import org.usfirst.frc.team5338.robot.subsystems.BallHandler;
import org.usfirst.frc.team5338.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5338.robot.subsystems.GearHandler;
import org.usfirst.frc.team5338.robot.subsystems.Winch;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements PIDOutput {
	Command autonomousCommand;
	SendableChooser<String> autoChooser;
	public static String chosenAuto;
	
	public static final OI oi = new OI();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final BallHandler ballhandler = new BallHandler();
	public static final Winch winch = new Winch();
	public static final GearHandler gearhandler = new GearHandler();

	public static AHRS ahrs;
	public static PIDController turnController;

	static final double kP = 0.03;
	static final double kI = 0.00;
	static final double kD = 0.000001;
	static final double kF = 0.00;
	static final double kToleranceDegrees = 2.0f;

	public static double rotateToAngleRate;
	public static boolean rotateToAngle;

	@Override
	public void robotInit() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 320);
		camera.setFPS(60);
		camera.setExposureAuto();
		camera.setWhiteBalanceAuto();
		
		autoChooser = new SendableChooser<String>();
		autoChooser.addDefault("Do nothing", "NONE");
		autoChooser.addObject("Drive straight and place center gear", "CENTERGEAR");
		autoChooser.addObject("Drive straight and place center gear and then go left", "CENTERGEAR-LEFT");
		autoChooser.addObject("Drive straight and place center gear and then go right", "CENTERGEAR-RIGHT");
		//TODO add the rest of the autos
		

		try {
			ahrs = new AHRS(SPI.Port.kMXP, (byte)(200));
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
		}
		turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
		turnController.setInputRange(-180.0f, 180.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);

		rotateToAngle = false;


	}

	@Override
	public void autonomousInit() {
		chosenAuto = autoChooser.getSelected();
		autonomousCommand = new Autonomous();
		autonomousCommand.start(); // schedule the autonomous command (example)
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void pidWrite(double output) {
		rotateToAngleRate = output;
		SmartDashboard.putNumber("rotate", output);
	}

}
