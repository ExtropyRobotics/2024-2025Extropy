package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "Test IMU")
public class Posi extends LinearOpMode {


    int wristPos = 1;
    boolean checkWristPress;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor backLeft;

    DcMotor motorAx = null;
    DcMotor slider = null;
    DcMotor liftRight = null;
    DcMotor liftLeft = null;

    Servo handLeft = null;
    Servo handRight = null;
    Servo claw = null;
    Servo rotation = null;
    Servo tilt = null;

    int targetPozAx = 0;
    int targetPozSl = 0;
    int targetPozLiftR = 0;
    int targetPozLiftL = 0;


    public BHI260IMU imu;

    @Override
    public void runOpMode() {

        double driveTurn;

        double gamepadXCoordinate;
        double gamepadYCoordinate;
        double gamepadHypot;
        double gamepadDegree;
        double robotDegree;
        double movementDegree;
        double gamepadXControl;
        double gamepadYControl;

        frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = hardwareMap.get(DcMotor.class, "rightFront");
        backRight = hardwareMap.get(DcMotor.class, "rightBack");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack");

        motorAx = hardwareMap.get(DcMotor.class, "motorAx");
        slider = hardwareMap.get(DcMotor.class, "slider");
        liftLeft = hardwareMap.get(DcMotor.class, "liftLeft");
        liftRight = hardwareMap.get(DcMotor.class, "liftRight");

        claw = hardwareMap.get(Servo.class, "claw");
        rotation = hardwareMap.get(Servo.class, "rotation");
        tilt = hardwareMap.get(Servo.class, "tilt");
        handRight = hardwareMap.get(Servo.class, "handRight");
        handLeft = hardwareMap.get(Servo.class, "handLeft");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorAx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorAx.setPower(1);
        motorAx.setTargetPosition(0);
        motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slider.setPower(1);
        slider.setTargetPosition(0);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        ImuOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.DOWN);
        BHI260IMU.Parameters parameters = new BHI260IMU.Parameters(orientation);

        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(parameters);

        motorAx.setTargetPosition(targetPozAx);
        slider.setTargetPosition(targetPozSl);
        liftRight.setTargetPosition(targetPozLiftR);
        liftLeft.setTargetPosition(targetPozLiftL);

        waitForStart();

        while (opModeIsActive()) {
            driveTurn = gamepad1.right_stick_x;

            gamepadXCoordinate = -gamepad1.left_stick_x; //this simply gives our x value relative to the driver
            gamepadYCoordinate = gamepad1.left_stick_y; //this simply gives our y vaue relative to the driver

            gamepadHypot = Range.clip(Math.hypot(gamepadXCoordinate, gamepadYCoordinate), 0, 1);

            gamepadDegree = Math.toDegrees(Math.atan2(gamepadYCoordinate, gamepadXCoordinate));
            robotDegree = getAngle();
            movementDegree = gamepadDegree - robotDegree;

            gamepadXControl = Math.cos(Math.toRadians(movementDegree)) * gamepadHypot;
            gamepadYControl = Math.sin(Math.toRadians(movementDegree)) * gamepadHypot;

            frontRight.setPower(gamepadYControl * Math.abs(gamepadYControl) - gamepadXControl * Math.abs(gamepadXControl) + driveTurn);
            backRight.setPower(gamepadYControl * Math.abs(gamepadYControl) + gamepadXControl * Math.abs(gamepadXControl) + driveTurn);
            frontLeft.setPower(gamepadYControl * Math.abs(gamepadYControl) + gamepadXControl * Math.abs(gamepadXControl) - driveTurn);
            backLeft.setPower(gamepadYControl * Math.abs(gamepadYControl) - gamepadXControl * Math.abs(gamepadXControl) - driveTurn);

            telemetry.addData("rogot degrees ", robotDegree);
            telemetry.addData("gamepad degree ", gamepadDegree);
            telemetry.addData("movement degree ", movementDegree);

            //      Tilt
            if(gamepad2.a) {
                if(!checkWristPress){
                    telemetry.addLine("adriana");
                    checkWristPress = true;
                    wristPos += 1;
                }
            }else  checkWristPress = false;

            if(wristPos == 4) wristPos = 1;

            if(wristPos == 1) tilt.setPosition(0);
            if(wristPos == 2) tilt.setPosition(0.5);
            if(wristPos == 3) tilt.setPosition(1);

            //    brat sincron
            if(gamepad2.left_stick_y < 0) targetPozAx += 5;
            if(gamepad2.left_stick_y > 0) targetPozAx -= 3;
            if(gamepad2.right_stick_y > 0) targetPozSl += 3;
            if(gamepad2.right_stick_y < 0) targetPozSl -= 3;


            motorAx.setTargetPosition(targetPozAx);
            slider.setTargetPosition(targetPozSl);
            telemetry.addData("ax ",targetPozAx);
            telemetry.addData("slider " ,targetPozSl);
            telemetry.update();
        }
    }

    public double getAngle() {
        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }
}