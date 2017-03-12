package org.usfirst.frc.team5338.robot;
import org.usfirst.frc.team5338.robot.commands.Autonomous;
import org.usfirst.frc.team5338.robot.subsystems.BallHandler;
import org.usfirst.frc.team5338.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5338.robot.subsystems.GearHandler;
import org.usfirst.frc.team5338.robot.subsystems.Winch;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	Command autonomousCommand;
	public static final OI oi = new OI();
	public static final DriveTrain drivetrain = new DriveTrain();
	public static final BallHandler ballhandler = new BallHandler();
	public static final Winch winch = new Winch();
	public static final GearHandler gearhandler = new GearHandler();
	
	@Override
	public void robotInit()
	{
		autonomousCommand = new Autonomous();
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(320, 240);
		camera.setFPS(60);
		camera.setExposureAuto();
		camera.setWhiteBalanceAuto();
	}	
	@Override
	public void autonomousInit() {
		autonomousCommand.start(); // schedule the autonomous command (example)
	}
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}	
	@Override
	public void teleopInit()
	{
		autonomousCommand.cancel();	
	}
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

}