package org.usfirst.frc.team5338.robot;

import org.usfirst.frc.team5338.robot.commands.Autonomous;
import org.usfirst.frc.team5338.robot.subsystems.BallHandler;
import org.usfirst.frc.team5338.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5338.robot.subsystems.GearHandler;
import org.usfirst.frc.team5338.robot.subsystems.Winch;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
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
public class Robot extends IterativeRobot implements PIDOutput
{
	public static SendableChooser<String> autonomousChooser;
	
	public static final OI oi = new OI();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final BallHandler ballhandler = new BallHandler();
	public static final Winch winch = new Winch();
	public static final GearHandler gearhandler = new GearHandler();
	
	private static Command autonomousCommand;
	
	public static AHRS ahrs = new AHRS(SPI.Port.kMXP, (byte)(200));;
	
	public static PIDController turnController;
	private static final double kP = 0.03;
	private static final double kI = 0.00;
	private static final double kD = 0.000001;
	private static final double kF = 0.00;
	public static final double kToleranceDegrees = 1.0f;

	public static double rotateToAngleRate;

	@Override
	public void robotInit()
	{
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 320);
		camera.setFPS(60);
		camera.setExposureAuto();
		camera.setWhiteBalanceAuto();
		
		ahrs.setPIDSourceType(PIDSourceType.kRate);
		
		SmartDashboard.putString("AUTONOMOUS CHOICE", "CENTERGEAR");

		turnController = new PIDController(kP, kI, kD, kF, ahrs, this, 0.01);
		turnController.setInputRange(0.0f, 360.0f);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(kToleranceDegrees);
		turnController.setContinuous(true);
	}
	@Override
	public void autonomousInit()
	{
		System.out.println("HI");
		autonomousCommand = new Autonomous();
		autonomousCommand.start(); // schedule the autonomous command (example)
	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void teleopInit()
	{
		try
		{
		autonomousCommand.cancel();
		}
		catch(Exception e)
		{
		}
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	public void pidWrite(double output)
	{
		rotateToAngleRate = output;
	}
}
