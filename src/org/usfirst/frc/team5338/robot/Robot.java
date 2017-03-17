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
public class Robot extends IterativeRobot implements PIDOutput
{
	private static final SendableChooser<String> autoChooser = new SendableChooser<String>();
	public static String chosenAuto;
	
	public static final OI oi = new OI();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final BallHandler ballhandler = new BallHandler();
	public static final Winch winch = new Winch();
	public static final GearHandler gearhandler = new GearHandler();
	
	private static final UsbCamera camera = CameraServer.getInstance();
	private static Command autonomousCommand;
	
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte)(200));;
	
	private static PIDController turnController;
	private static final double kP = 0.03;
	private static final double kI = 0.00;
	private static final double kD = 0.000001;
	private static final double kF = 0.00;
	private static final double kToleranceDegrees = 2.0f;

	public static double rotateToAngleRate;
	public static boolean rotateToAngle;

	@Override
	public void robotInit()
	{
		camera.startAutomaticCapture();
		camera.setResolution(320, 320);
		camera.setFPS(60);
		camera.setExposureAuto();
		camera.setWhiteBalanceAuto();
		
		autoChooser.addDefault("Drive straight and place center gear", "CENTERGEAR");
		autoChooser.addObject("Drive straight and place center gear and then go left", "CENTERGEAR-LEFT");
		autoChooser.addObject("Drive straight and place center gear and then go right", "CENTERGEAR-RIGHT");
		autoChooser.addObject("Drive straight and place left gear and then go across baseline", "LEFTGEAR");
		autoChooser.addObject("Drive straight and place right gear and then across baseline", "RIGHTGEAR");
		autoChooser.addObject("Drive straight across baseline", "BASELINE");
		autoChooser.addObject("Testing", "TEST");

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
