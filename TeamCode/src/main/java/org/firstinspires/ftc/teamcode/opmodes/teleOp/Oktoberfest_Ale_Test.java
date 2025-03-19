package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.OktMove;

@TeleOp (name = "!!!!!!!!!!!!!!!Oktoberfest_Ale_Test")
public class Oktoberfest_Ale_Test extends LinearOpMode {

    DcMotor motorL = null;
    DcMotor motorR = null;
    DcMotor motorSlide = null;
    DcMotor motorScrew = null;
    Servo servoJos = null;
    Servo servoSus = null;


    double sticklefty = 0;
    double stickrightx = 0;
    double screwPower = 0;
    double slidePower = 0;

    double poz = 0.065;
    double stepServo = 0.0105;
    double stepSmallServo = stepServo/6;
    double stepMotor = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {

        motorL = hardwareMap.get(DcMotor.class, "motorL");
        motorR = hardwareMap.get(DcMotor.class, "motorR");
        motorSlide = hardwareMap.get(DcMotor.class, "motorSlide");
        motorScrew = hardwareMap.get(DcMotor.class, "motorScrew");
        servoJos = hardwareMap.get(Servo.class, "servoJos");
        servoSus = hardwareMap.get(Servo.class, "servoSus");

        motorSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorR.setDirection(DcMotor.Direction.REVERSE);
        servoSus.setPosition(0);

        boolean upPressed = true;
        boolean leftPressed = true;
        boolean leftBump = true;
        boolean rightBump = true;
        boolean leftSmallBump = true;
        boolean rightSmallBump = true;

        waitForStart();

        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickrightx = gamepad1.right_stick_x;

            motorR.setPower(sticklefty + stickrightx);
            motorL.setPower(sticklefty - stickrightx);

            slidePower = -0.001;
            if (gamepad1.y) {
                slidePower = 0.2;
            }
            if (gamepad1.a) {
                slidePower = -0.2;
            }

            if (gamepad1.left_trigger > 0.01) {
                if (leftBump) {
                    if(screwPower + stepMotor < 1){
                        screwPower += stepMotor;
                    }
                    telemetry.addData("left trigger pressed", screwPower);
                    telemetry.update();
                    motorScrew.setPower(screwPower);
                    leftBump = false;
                }
            } else {
                leftBump = true;
            }
            if (gamepad1.right_trigger > 0.1) {
                if (rightBump) {
                    if(screwPower - stepMotor > -1){
                        screwPower -= stepMotor;
                    }
                    telemetry.addData("right trigger pressed", screwPower);
                    telemetry.update();
                    motorScrew.setPower(screwPower);
                    rightBump = false;
                }
            } else {
                rightBump = true;
            }

            if (gamepad1.dpad_right) {
                servoJos.setPosition(0.198  );
            }
            if (gamepad1.dpad_down) {
                servoJos.setPosition(0);
            }
            if (gamepad1.dpad_up) {
                if (upPressed) {
                    if (poz + stepServo < 1) {
                        poz += 0.0097;
                    }
                    telemetry.addData("servo sus pressed", poz);
                    telemetry.update();
                    servoSus.setPosition(poz);
                    upPressed = false;
                }
            } else {
                upPressed = true;
            }
            if (gamepad1.dpad_left) {
                if (leftPressed) {
                    if (poz - stepServo > 0) {
                        poz -= 0.0095;
                    }
                    telemetry.addData("servo jos pressed", poz);
                    telemetry.update();
                    servoSus.setPosition(poz);
                    leftPressed = false;
                }
            } else {
                leftPressed = true;
            }
            if(gamepad1.left_bumper){
                if(leftSmallBump){
                    if(poz - stepSmallServo > 0) poz -= stepSmallServo;
                    telemetry.addData("servo jos small pressed", poz);
                    telemetry.update();
                    servoSus.setPosition(poz);
                    leftPressed = false;
                }
            }else{
                leftSmallBump = false;
            }
            if(gamepad1.right_bumper){
                if(rightSmallBump){
                    if(poz + stepSmallServo > 0) poz += stepSmallServo;
                    telemetry.addData("servo sus small pressed", poz);
                    telemetry.update();
                    servoSus.setPosition(poz);
                    rightSmallBump = false;
                }
            }else{
                rightSmallBump = false;
            }


            motorSlide.setPower(slidePower);
        }
    }
}
