// // //Copyright (c) 2017 FIRST. All rights re
// // ///*
// // * Redistribution and use in source and binary forms, with or without modification,
// // * are permitted (subject to the limitations in the disclaimer below) provided that
// // * the following conditions are met:
// // *
// // * Redistributions of source code must retain the above copyright notice, this list
// // * of conditions and the following disclaimer.
 
// package Autonomie;
// import android.app.Activity;
// import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
// import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
// import com.qualcomm.robotcore.hardware.DistanceSensor;
// import com.qualcomm.robotcore.util.ElapsedTime;
// import com.qualcomm.robotcore.hardware.Servo;
// import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
// import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
// import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
// import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
// import com.qualcomm.robotcore.hardware.DcMotor;
// import com.qualcomm.hardware.bosch.BNO055IMU;
// import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
// import android.graphics.Color;
// import android.view.View;

// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
// import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
// import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
// import com.qualcomm.robotcore.hardware.NormalizedRGBA;
// import com.qualcomm.robotcore.hardware.SwitchableLight;


// @Autonomous(name = "AutonomieAlbastruParcareFull", group = "Autonomous")
// public class AutonomieCuCyberPunk extends LinearOpMode {

//   Rev2mDistanceSensor CS1, CS2;
//   View relativeLayout;
//   int RataStanga=0,RataMijloc=0,RataDreapta=0;
//	DcMotor			  motorDreaptaFata, motorStangaFata, motorDreaptaSpate, motorStangaSpate,motorManuta;
//	BNO055IMU			imu;
//	Orientation		   lastAngles = new Orientation();
//	double			   globalAngle, correction, rotation;
//	PIDController		 pidRotate, pidDrive;
//	Servo servo1,servo2;
//	DcMotor planetara;
//	// DistanceSensor dstanga;
//	// DistanceSensor dreapta;
//	private ElapsedTime	runtime = new ElapsedTime();
//   @Override
//   public void runOpMode() throws InterruptedException {
//	int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
//	relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
//	motorStangaSpate = hardwareMap.get(DcMotor.class, "motorStangaSpate");
//	    motorDreaptaSpate = hardwareMap.get(DcMotor.class, "motorDreaptaSpate");
//	    motorStangaFata = hardwareMap.get(DcMotor.class, "motorStangaFata");
//	    motorDreaptaFata = hardwareMap.get(DcMotor.class, "motorDreaptaFata");
//	    motorManuta = hardwareMap.get(DcMotor.class, "motorManuta");
//	    servo1 = hardwareMap.get(Servo.class, "servo1");
//	    servo2 = hardwareMap.get(Servo.class, "servo2");
//	    planetara = hardwareMap.get(DcMotor.class,"planetara");
//	    motorDreaptaFata.setDirection(DcMotor.Direction.REVERSE);
//	    motorDreaptaSpate.setDirection(DcMotor.Direction.REVERSE);
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

//	    motorDreaptaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//	    motorDreaptaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//	    motorStangaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//	    motorStangaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

 


//	  BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

//	    parameters.mode			 = BNO055IMU.SensorMode.IMU;
//	    parameters.angleUnit		 = BNO055IMU.AngleUnit.DEGREES;
//	    parameters.accelUnit		 = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//	    parameters.loggingEnabled	 = false;

//	    imu = hardwareMap.get(BNO055IMU.class, "imu");

//	    imu.initialize(parameters);

//	    pidRotate = new PIDController(0, 0, 0);

//	    pidDrive = new PIDController(0.025, 0, 0);

//	    telemetry.addLine()
//			   .addData("Giroscop", "se calibreaza...");
//	    telemetry.update();

//	    while (!isStopRequested() && !imu.isGyroCalibrated())
//	    {
//		   sleep(50);
//		   idle();
//	    }

//	    telemetry.addLine()
//			   .addData("Giroscop", "s-a calibrat. Puteti incepe autonomia!");
			  
//	    telemetry.update();
	  
//	  CS1 = hardwareMap.get(Rev2mDistanceSensor.class, "CS1");
//	  CS2 = hardwareMap.get(Rev2mDistanceSensor.class, "CS2");
//	  waitForStart();
//	  runtime.reset();
//	  servodeschisinchis(0);
	 
//	  if (opModeIsActive() && !isStopRequested())
//	  {
//	  driveForward(39);
	  
//	  if(CS1.getDistance(DistanceUnit.CM)<=15)
//	  {
//		 RataMijloc = 1;
//		 telemetry.addData("Caz", "Rata stanga");
//		 telemetry.update();
		 
//	  }
//	  else  if(CS2.getDistance(DistanceUnit.CM)<=15 && RataMijloc==0)
//	  {
//		 RataStanga = 1;
//		 telemetry.addData("Caz", "Rata mijloc");
//		 telemetry.update();
		 
//	  }
//	  else if(RataMijloc==0 && RataStanga==0)
//	  {
//		 RataDreapta = 1;
//		 telemetry.addData("Caz", "Rata dreapta");
//		 telemetry.update();
//	  }
	   
//	  if(RataStanga==1)
//	  {
//	    telemetry.addData("CAZ:", "JOS");
//	    telemetry.update();
//	    cazJos();
//	  }
	 
//	  else if(RataMijloc==1)
//	  {
//	    telemetry.addData("CAZ:", "MIJLOC");
//	    telemetry.update();
//	    cazMijloc();
//	  }
	 
//	  else if(RataDreapta==1)
//	  {
//	    telemetry.addData("CAZ:", "SUS");
//	    telemetry.update();
//	    cazSus();
//	  }

//	}    
// }
//	private void resetAngle()
//	{
//	    lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
//	    globalAngle = 0;
//	}

//	private double getAngle()
//	{
//	    Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

//	    double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

//	    if (deltaAngle < -180)
//		   deltaAngle += 360;
//	    else if (deltaAngle > 180)
//		   deltaAngle -= 360;

//	    globalAngle += deltaAngle;
																	  
//	    lastAngles = angles;

//	    return globalAngle;
//	}
    
//	private void cazJos()
//	{
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	   
//	    ElapsedTime runtime = new ElapsedTime();
//	    int merge = 0;
//	    while(runtime.seconds() <= 10 && merge==0 && opModeIsActive())
//	    {
//		   if(merge==0) merge=1;
//		   // telemetry.addData("MISCA MANUTA", "DA DA DA");
//		   // telemetry.update();
//		   //motorManuta.setTargetPosition(1710);
//		   motorManuta.setTargetPosition(1800);
//		   //motorManuta.setTargetPosition(1630);
//		   motorManuta.setPower(0.4);
//		   motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		  
		  
//		   while(runtime.seconds()<=29 && opModeIsActive())
//		   {   
//			  // driveForward(-37); //merge in spate
//			  // rotate(-40, 0.7); //se invarte inspre ciuperca
//			  // driveForward(50); //duce manuta la ciuperca
//			  // deschideManuta();
//			  // driveForward(-20); //merge in spate
//			  // // rotate(48, 0.7); //se invarte spre rata
//			  // motorManuta.setTargetPosition(300);
//			  // motorManuta.setPower(-0.4);
//			  // motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  // sleep(1000);
//			  // // rotate(95, 0.7); //se invarte spre rata
//			  // // driveForward(74); //merge la rata
//			  // // startPlanetara();
//			  // // driveForward(-7); //merge in spate
//			  // // rotate(-98, 0.7); //se invarte spre parcare
//			  // // driveForward(40); //se parcheaza
//			  // rotate(-25,0.7);
//			  // driveForward(-50);
//			  // driveForward(150);
//			   driveForward(-37); //merge in spate
//			  rotate(-35, 0.7); //se invarte inspre ciuperca
//			  driveForward(50); //duce manuta la ciuperca
//			  deschideManuta();
//			  driveForward(-20); //merge in spate
//			  // rotate(48, 0.7); //se invarte spre rata
//			  motorManuta.setTargetPosition(300);
//			  motorManuta.setPower(-0.4);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  sleep(1000);
//			  // rotate(95, 0.7); //se invarte spre rata
//			  // driveForward(74); //merge la rata
//			  // startPlanetara();
//			  // driveForward(-7); //merge in spate
//			  // rotate(-98, 0.7); //se invarte spre parcare
//			  // driveForward(40); //se parcheaza
//			  rotate(-90,0.7);
//			  driveForward(-50);
//			  driveForward(150);
//			  break;
//		   }
//	    }
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  
//	    motorManuta.setPower(0);
//	}
  
  
//	private void cazMijloc()
//	{
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	   
//	    ElapsedTime runtime = new ElapsedTime();
//	    int merge = 0;
//	    while(runtime.seconds() <= 10 && merge==0 && opModeIsActive())
//	    {
//		   telemetry.addData("caz", "mijloc");
//		   if(merge==0) merge=1;
//		   motorManuta.setTargetPosition(1600);
//		   //motorManuta.setTargetPosition(1450);
//		   motorManuta.setPower(0.4);
//		   motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		  
		  
//		   while(runtime.seconds()<=29)
//		   {   
			 
//			  // driveForward(-37); //merge in spate
//			  // rotate(-40, 0.7); //se invarte inspre ciuperca
//			  // driveForward(50); //duce manuta la ciuperca
//			  // deschideManuta();
//			  // driveForward(-20); //merge in spate
//			  // // rotate(48, 0.7); //se invarte spre rata
//			  // motorManuta.setTargetPosition(300);
//			  // motorManuta.setPower(-0.4);
//			  // motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  // sleep(1000);
//			  // // rotate(95, 0.7); //se invarte spre rata
//			  // // driveForward(74); //merge la rata
//			  // // startPlanetara();
//			  // // driveForward(-7); //merge in spate
//			  // // rotate(-98, 0.7); //se invarte spre parcare
//			  // // driveForward(40); //se parcheaza
//			  // rotate(-25,0.7);
//			  // driveForward(-50);
//			  // driveForward(150);
//			   driveForward(-37); //merge in spate
//			  rotate(-35, 0.7); //se invarte inspre ciuperca
//			  driveForward(50); //duce manuta la ciuperca
//			  deschideManuta();
//			  driveForward(-20); //merge in spate
//			  // rotate(48, 0.7); //se invarte spre rata
//			  motorManuta.setTargetPosition(300);
//			  motorManuta.setPower(-0.4);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  sleep(1000);
//			  // rotate(95, 0.7); //se invarte spre rata
//			  // driveForward(74); //merge la rata
//			  // startPlanetara();
//			  // driveForward(-7); //merge in spate
//			  // rotate(-98, 0.7); //se invarte spre parcare
//			  // driveForward(40); //se parcheaza
//			  rotate(-90,0.7);
//			  driveForward(-50);
//			  driveForward(150);
//			  break;
//		   }
//	    }
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  
//	    motorManuta.setPower(0);
//	}
    
//	private void cazSus()
//	{
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
	   
//	    ElapsedTime runtime = new ElapsedTime();
//	    int merge = 0;
	   
//	    while(runtime.seconds() <= 10 && merge==0 && opModeIsActive())
//	    {
//		   if(merge==0) merge=1;
//		   motorManuta.setTargetPosition(1400);
//		   motorManuta.setPower(0.4);
//		   motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		  
		  
//		   while(runtime.seconds()<=29 && opModeIsActive())
//		   {   
//			  // driveForward(-37); //merge in spate
//			  // rotate(-40, 0.7); //se invarte inspre ciuperca
//			  // driveForward(50); //duce manuta la ciuperca
//			  // deschideManuta();
//			  // driveForward(-20); //merge in spate
//			  // // rotate(48, 0.7); //se invarte spre rata
//			  // motorManuta.setTargetPosition(300);
//			  // motorManuta.setPower(-0.4);
//			  // motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  // sleep(1000);
//			  // // rotate(95, 0.7); //se invarte spre rata
//			  // // driveForward(74); //merge la rata
//			  // // startPlanetara();
//			  // // driveForward(-7); //merge in spate
//			  // // rotate(-98, 0.7); //se invarte spre parcare
//			  // // driveForward(40); //se parcheaza
//			  // rotate(-25,0.7);
//			  // driveForward(-50);
//			  // driveForward(150);
//			   driveForward(-37); //merge in spate
//			  rotate(-35, 0.7); //se invarte inspre ciuperca
//			  driveForward(50); //duce manuta la ciuperca
//			  deschideManuta();
//			  driveForward(-20); //merge in spate
//			  // rotate(48, 0.7); //se invarte spre rata
//			  motorManuta.setTargetPosition(300);
//			  motorManuta.setPower(-0.4);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  sleep(1000);
//			  // rotate(95, 0.7); //se invarte spre rata
//			  // driveForward(74); //merge la rata
//			  // startPlanetara();
//			  // driveForward(-7); //merge in spate
//			  // rotate(-98, 0.7); //se invarte spre parcare
//			  // driveForward(40); //se parcheaza
//			  rotate(-90,0.7);
//			  driveForward(-50);
//			  driveForward(150);
//			  break;
//		   }
//	    }
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  
//	    motorManuta.setPower(0);
//	}
    
//	 private void rotate(int degrees, double power)
//	{
//	    double i;
	   
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	  
//	    // restart imu angle tracking.
//	    lastAngles = new Orientation();
//	    resetAngle();
	   
//	    // If input degrees > 359, we cap at 359 with same sign as input.
//	    if (Math.abs(degrees) > 359) degrees = (int) Math.copySign(359, degrees);
	  
//	    pidRotate.reset();
	   
//	    double p = Math.abs(power/degrees);
//	    if(degrees < 0)
//		   i = p / 130;
//	    else
//		   i = p / 180;
	  
//	    pidRotate.setPID(p+0.0048, i+0.0001, 0.20);
	   
//	    pidRotate.setSetpoint(degrees);
//	    pidRotate.setInputRange(-degrees, degrees);
//	    pidRotate.setOutputRange(0, power);
//	    pidRotate.setTolerance(1.0 / Math.abs(degrees) * 100.0);
//	    pidRotate.enable();

//	    // getAngle() returns + when rotating counter clockwise (left) and - when rotating
//	    // clockwise (right).

//	    // rotate until turn is completed.

//	    if (degrees < 0) //right turn
//	    {
//			  //On right turn we have to get off zero first.
//			  while (opModeIsActive() && getAngle() == 0)
//			  {
//				 motorStangaSpate.setPower(power);
//				 motorStangaFata.setPower(power);
//				 motorDreaptaFata.setPower(-power);
//				 motorDreaptaSpate.setPower(-power);
//				 sleep(100);
//			  }
//			  runtime = new ElapsedTime();
//			  while (opModeIsActive() && globalAngle > degrees - 0.6)
//			  {
//				 telemetry.addData("IMU Heading", lastAngles.firstAngle);
//				 telemetry.addData("Global Heading", globalAngle);
//				 telemetry.addData("targetAngle", degrees);
//				 telemetry.addData("getAngle()" , getAngle());
//				 telemetry.update();
				
//				 power = pidRotate.performPID(getAngle());
//				 motorStangaSpate.setPower(-power);
//				 motorStangaFata.setPower(-power);
//				 motorDreaptaFata.setPower(power);
//				 motorDreaptaSpate.setPower(power);
			
//				 if(globalAngle < degrees + 0.4 && runtime.seconds() <= 3) break;
			    
//			  }
//	    }
	   
//	    else if(degrees > 0)   // left turn.
//	    {
//		   runtime = new ElapsedTime();
//		   while (runtime.seconds()<=2 && globalAngle < degrees + 0.4 && opModeIsActive())
//		   {
//			  telemetry.addData("IMU Heading", lastAngles.firstAngle);
//			  telemetry.addData("Global Heading", globalAngle);
//			  telemetry.addData("targetAngle", degrees);
//			  telemetry.addData("getAngle()" , getAngle());
//			  telemetry.update();
			    
//			  power = -pidRotate.performPID(getAngle()); // power will be - on right turn.
//			  motorStangaSpate.setPower(power);
//			  motorStangaFata.setPower(power);
//			  motorDreaptaFata.setPower(-power);
//			  motorDreaptaSpate.setPower(-power);
			
//			  if(globalAngle > degrees - 0.4 || runtime.seconds() > 2.2) break;
//		   }
//	    }
	   
//	    motorDreaptaFata.setPower(0);
//	    motorDreaptaSpate.setPower(0);
//	    motorStangaSpate.setPower(0);
//	    motorStangaFata.setPower(0);
	  

//	    rotation = getAngle();

	   
//	    lastAngles = new Orientation();
//	    resetAngle();

//	    while(motorStangaFata.isBusy() && opModeIsActive()){}
//	}
   
//	void driveForward(int distance)
//	{
//	    double speedAfterAcceleratingRight = 0, speedAfterAcceleratingLeft = 0;
//	    double acceleration = 0.01, deceleration = 0.01;
//	    double diameter = 10;
//	    double circumference = 3.14 * diameter;
//	    double rotationsNeeded = distance / circumference;
//	    int encoderTargetPosition = (int)(rotationsNeeded * 560);
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//	    motorStangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	  
//	    pidDrive.setSetpoint(0);
//	    pidDrive.setOutputRange(0, 0.5);
//	    pidDrive.setInputRange(-90, 90);
//	    pidDrive.enable();
	  
//	    motorDreaptaFata.setTargetPosition(encoderTargetPosition);
//	    motorDreaptaSpate.setTargetPosition(encoderTargetPosition);
//	    motorStangaFata.setTargetPosition(encoderTargetPosition);
//	    motorStangaSpate.setTargetPosition(encoderTargetPosition);
	  
//	    motorDreaptaFata.setPower(0.2);
//	    motorDreaptaSpate.setPower(0.2);
//	    motorStangaFata.setPower(0.2);
//	    motorStangaSpate.setPower(0.2);
	  
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	  
//	    while ((motorDreaptaFata.isBusy() || motorStangaFata.isBusy() || motorDreaptaSpate.isBusy() || motorStangaSpate.isBusy()) && opModeIsActive())
//	    {
//		   correction = pidDrive.performPID(getAngle());
   
//		   // telemetry.addData("IMU heading", lastAngles.firstAngle);
//		   // telemetry.addData("Global Heading", globalAngle);
//		   // telemetry.addData("correction", correction);
//		   // telemetry.addData("motorDreaptaFata's power", motorDreaptaFata.getPower());
//		   // telemetry.update();
		 
//		   if (motorDreaptaFata.getCurrentPosition() < (encoderTargetPosition / 2))
//		   {
//			  speedAfterAcceleratingRight = 0.1 + acceleration + correction;
//			  speedAfterAcceleratingLeft = 0.1 + acceleration - correction;
			
//			  motorDreaptaFata.setPower(0.1 + acceleration + correction);
//			  motorDreaptaSpate.setPower(0.1 + acceleration + correction);
//			  motorStangaSpate.setPower(0.1 + acceleration - correction);
//			  motorStangaFata.setPower(0.1 + acceleration - correction);
			
//			  acceleration += 0.006;
//		   }
		 
//		   else
//		   {
//			  motorDreaptaFata.setPower(speedAfterAcceleratingRight - deceleration + correction);
//			  motorDreaptaSpate.setPower(speedAfterAcceleratingRight - deceleration + correction);
//			  motorStangaSpate.setPower(speedAfterAcceleratingLeft - deceleration - correction);
//			  motorStangaFata.setPower(speedAfterAcceleratingLeft - deceleration - correction);
			
//			  deceleration += 0.006;
//		   }
//	    }
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	  
//	    motorDreaptaFata.setPower(0);
//	    motorDreaptaSpate.setPower(0);
//	    motorStangaSpate.setPower(0);
//	    motorStangaFata.setPower(0);
//	    sleep(50);
//	}
   
//	void driveBackwards(int distance)
//	{
//	    motorDreaptaFata.setDirection(DcMotor.Direction.FORWARD);
//	    motorDreaptaSpate.setDirection(DcMotor.Direction.FORWARD);
//	    motorStangaFata.setDirection(DcMotor.Direction.REVERSE);
//	    motorStangaSpate.setDirection(DcMotor.Direction.REVERSE);

//	    double speedAfterAcceleratingRight = 0, speedAfterAcceleratingLeft = 0;
//	    double acceleration = 0.01, deceleration = 0.01;
//	    double diameter = 10;
//	    double circumference = 3.14 * diameter;
//	    double rotationsNeeded = distance / circumference;
//	    int encoderTargetPosition = (int)(rotationsNeeded * 560);
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

//	    motorStangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	  
//	    pidDrive.setSetpoint(0);
//	    pidDrive.setOutputRange(0, 0.5);
//	    pidDrive.setInputRange(-90, 90);
//	    pidDrive.enable();
	  
//	    motorDreaptaFata.setTargetPosition(encoderTargetPosition);
//	    motorDreaptaSpate.setTargetPosition(encoderTargetPosition);
//	    motorStangaFata.setTargetPosition(encoderTargetPosition);
//	    motorStangaSpate.setTargetPosition(encoderTargetPosition);
	  
//	    motorDreaptaFata.setPower(0.2);
//	    motorDreaptaSpate.setPower(0.2);
//	    motorStangaFata.setPower(0.2);
//	    motorStangaSpate.setPower(0.2);
	  
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
	  
//	    while ((motorDreaptaFata.isBusy() || motorStangaFata.isBusy() || motorDreaptaSpate.isBusy() || motorStangaSpate.isBusy())&&opModeIsActive())
//	    {
//		   correction = pidDrive.performPID(getAngle());
   
//		   // telemetry.addData("IMU heading", lastAngles.firstAngle);
//		   // telemetry.addData("Global Heading", globalAngle);
//		   // telemetry.addData("correction", correction);
//		   // telemetry.addData("motorDreaptaFata's power", motorDreaptaFata.getPower());
//		   // telemetry.update();
		 
//		   if (motorDreaptaFata.getCurrentPosition() < (encoderTargetPosition / 2))
//		   {
//			  speedAfterAcceleratingRight = 0.1 + acceleration + correction;
//			  speedAfterAcceleratingLeft = 0.1 + acceleration - correction;
			
//			  motorDreaptaFata.setPower(0.1 + acceleration + correction);
//			  motorDreaptaSpate.setPower(0.1 + acceleration + correction);
//			  motorStangaSpate.setPower(0.1 + acceleration - correction);
//			  motorStangaFata.setPower(0.1 + acceleration - correction);
			
//			  acceleration += 0.005;
//		   }
		 
//		   else
//		   {
//			  motorDreaptaFata.setPower(speedAfterAcceleratingRight - deceleration + correction);
//			  motorDreaptaSpate.setPower(speedAfterAcceleratingRight - deceleration + correction);
//			  motorStangaSpate.setPower(speedAfterAcceleratingLeft - deceleration - correction);
//			  motorStangaFata.setPower(speedAfterAcceleratingLeft - deceleration - correction);
			
//			  deceleration += 0.005;
//		   }
//	    }
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
	  
//	    motorStangaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorStangaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorDreaptaFata.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//	    motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
	  
//	    motorDreaptaFata.setPower(0);
//	    motorDreaptaSpate.setPower(0);
//	    motorStangaSpate.setPower(0);
//	    motorStangaFata.setPower(0);
	   
//	    motorStangaFata.setDirection(DcMotor.Direction.FORWARD);
//	    motorStangaSpate.setDirection(DcMotor.Direction.FORWARD);
//	    motorDreaptaFata.setDirection(DcMotor.Direction.REVERSE);
//	    motorDreaptaSpate.setDirection(DcMotor.Direction.REVERSE);
    
//	    while(motorStangaFata.isBusy()&& opModeIsActive()){}
//	}
   
//	private void manutainsus(int x)
//	{
//			  motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//			  motorManuta.setTargetPosition(x);
//			  motorManuta.setPower(0.6);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  sleep(2760);
//			  motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//			  motorManuta.setPower(0);
//			  motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//			  sleep(800);
//	}
//	private void manutainjos(int x)
//	{
//			  motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//			  motorManuta.setTargetPosition(x);
//			  motorManuta.setPower(-1);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//			  motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//			  motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//			  sleep(1000);
//			  motorManuta.setPower(0);
//	}
//	private void servodeschisinchis(int functionare)
//	{
//	    if(functionare==0)
//	    {
//		   servo1.setPosition(0);
//		   servo2.setPosition(0.5);
//	    }
//	    else if(functionare==1)
//	    {
//		   servo1.setPosition(0.3);
//		   servo2.setPosition(0.25);
//	    }
//	}
    
//	private void Rate(int power)
//	{
//	    planetara.setPower(power);
//	    sleep(3000);
//	}
    
    

//	private void inchideManuta()
//	{
//	    servo1.setPosition(0);
//	    servo2.setPosition(0.5);
//	}
    
//	private void deschideManuta()
//	{
//	    servo1.setPosition(0.3);
//	    servo2.setPosition(0.25);
//	    sleep(100);
//	}
    
//	private void startPlanetara()
//	{
//	    ElapsedTime runtime = new ElapsedTime();
//	    while(runtime.seconds() >= 0 && runtime.seconds() <= 3.2 && opModeIsActive())
//		   planetara.setPower(0.75);
//	    planetara.setPower(0);
//	}

//	private void motorManutaJos(int pozitie)
//	{
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//	    motorManuta.setTargetPosition(-pozitie);
//	    motorManuta.setPower(0.5);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//	    motorManuta.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//	    motorManuta.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);	  
//	    sleep(1700);
//	}
    
// }
		  
   


