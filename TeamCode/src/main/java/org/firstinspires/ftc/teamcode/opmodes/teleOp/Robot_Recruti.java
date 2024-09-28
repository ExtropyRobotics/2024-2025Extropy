package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "TeleOp_Recruti")
public class Robot_Recruti extends LinearOpMode {

    DcMotor motorFataDreapta = null;
    DcMotor motorFataStanga = null;
    DcMotor motorSpateStanga = null;
    DcMotor motorSpateDreapta = null;
    DcMotor motorHandLeft = null;
    DcMotor motorHandRight = null;
    Servo servoIncheieturaDreapta = null;
    Servo servoIncheieturaStanga = null;
    Servo servoHandDreapta = null;
    Servo servoHandStanga = null;

    double sticklefty = 0;
    double stickleftx = 0;
    double stickrightx = 0;
    int targetPoz = 0;
    double targetPoz2 = 0;
    @Override

    public void runOpMode () {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motorFataDreapta = hardwareMap.get(DcMotor.class,"motorFataDreapta");
        motorFataStanga = hardwareMap.get (DcMotor.class, "motorFataStanga");
        motorSpateDreapta = hardwareMap.get (DcMotor.class, "motorSpateDreapta");
        motorSpateStanga = hardwareMap.get (DcMotor.class, "motorSpateStanga");

        motorHandLeft = hardwareMap.get(DcMotor.class, "motorHandLeft");
        motorHandRight = hardwareMap.get(DcMotor.class, "motorHandRight");

        servoIncheieturaDreapta = hardwareMap.get(Servo.class, "servoIncheieturaDreapta");
        servoIncheieturaStanga = hardwareMap.get(Servo.class, "servoIncheieturaStanga");
        servoHandDreapta = hardwareMap.get(Servo.class, "servoHandDreapta");
        servoHandStanga = hardwareMap.get(Servo.class, "servoHandStanga");

        motorFataStanga.setDirection(DcMotorSimple.Direction.REVERSE);
        motorSpateStanga.setDirection(DcMotorSimple.Direction.REVERSE);
        motorHandLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        servoIncheieturaDreapta.setDirection(Servo.Direction.REVERSE);

        servoHandDreapta.setDirection(Servo.Direction.REVERSE);

        motorHandRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorHandLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()) {

            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;
            stickrightx = gamepad1.right_stick_x;
            motorFataStanga.setPower(sticklefty + stickleftx + stickrightx);
            motorFataDreapta.setPower(sticklefty - stickleftx - stickrightx);
            motorSpateDreapta.setPower(sticklefty + stickleftx - stickrightx);
            motorSpateStanga.setPower(sticklefty - stickleftx + stickrightx);


            if(gamepad2.y) {
                servoHandDreapta.setPosition(0);
                servoHandStanga.setPosition(0);
            }
            if(gamepad2.b){
                servoHandStanga.setPosition(0.4);
                servoHandDreapta.setPosition(0.55);
            }
            if (gamepad2.right_stick_y > 0) {
                targetPoz += 1;
            }
            if (gamepad2.right_stick_y < 0) {
                targetPoz -= 2;
            }
            if (targetPoz<0)
                targetPoz = 0;
            if (targetPoz>400)
                targetPoz = 400;
            motorHandRight.setTargetPosition(targetPoz);
            motorHandLeft.setTargetPosition(targetPoz);
            motorHandRight.setPower(0.2);
            motorHandLeft.setPower(0.2);
            motorHandRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorHandLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.addData("",targetPoz);
            telemetry.update();

            if (gamepad2.left_stick_y>0)
                targetPoz2 += 0.0045;
            if (gamepad2.left_stick_y<0)
                targetPoz2 -= 0.0045;
            if (targetPoz2 < 0)
                targetPoz2 = 0;
            if (targetPoz2 > 1)
                targetPoz2 = 1;
            servoIncheieturaStanga.setPosition(targetPoz2);
            servoIncheieturaDreapta.setPosition(targetPoz2);

            telemetry.addData("",targetPoz2);
            telemetry.update();

                  }
           }

    }
