package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "TELEOP SATU MARE")
public class Posi extends LinearOpMode {

    double wristPos = 0.5;

    //paralelism
    double toggleCheck = 1;
    boolean moneyCheck = false;
    double paralelPos = 0.55;

    boolean checkClawPress = false;
    boolean toggleClaw = false;


    DcMotor motorAxR = null;
    DcMotor motorAxL = null;
    DcMotor slider = null;

    Servo claw = null;
    Servo tilt = null;

    int targetPozAx = 0;
    int targetPozSl = 0;

    double pos = 0.5;
    double rotateAngle = 0;

    boolean checkPozitieSpate = false;
    boolean toogleSpate = false;

    int something = 1;

    @Override
    public void runOpMode() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        motorAxL = hardwareMap.get(DcMotor.class, "motorAxLeft");
        motorAxR = hardwareMap.get(DcMotor.class, "motorAxRight");
        slider = hardwareMap.get(DcMotor.class, "slider");
        claw = hardwareMap.get(Servo.class, "claw");
        tilt = hardwareMap.get(Servo.class, "tilt");

        motorAxL.setDirection(DcMotorSimple.Direction.REVERSE);

        motorAxL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxL.setPower(0.5);
        motorAxL.setTargetPosition(0);
        motorAxL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorAxR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxR.setPower(0.5);
        motorAxR.setTargetPosition(0);
        motorAxR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setPower(0.5);
        slider.setTargetPosition(0);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();
        while (opModeIsActive()) {
            drive.setWeightedDrivePower(new Pose2d(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            ));

            if(gamepad2.left_stick_y < -0.1) targetPozAx += 5;
            if(gamepad2.left_stick_y > 0.1) targetPozAx -= 5;

            if(gamepad2.right_stick_y > 0.1) targetPozSl += 10;
            if(gamepad2.right_stick_y < -0.1) targetPozSl -= 10;

            // brat paralel (pozitie ridicata)
            if(gamepad2.right_bumper){
                if(moneyCheck) {
                    if (toggleCheck == 1) {
                        paralelPos = 0.55;
                        something = 1;
                    } else if(toggleCheck == 2){
                        paralelPos = 0.4;
                        something = 0;
                    }
                    else if(toggleCheck == 3){
                        paralelPos = 0.3;
                        something = 1;
                    }
                    toggleCheck += 1;
                    moneyCheck = false;
                }
                if(toggleCheck == 4) {
                    toggleCheck = 1;
                }
            }else{
                moneyCheck = true;
            }

            pos = something*(-rotateAngle / 480 * (0.35-1)) + paralelPos;
            tilt.setPosition(pos);

            // cleste
            if(gamepad2.a){
                if(checkClawPress) {
                    if (toggleClaw) {
                        claw.setPosition(0.45);
                    } else {
                        claw.setPosition(0.25);
                    }
                    toggleClaw = !toggleClaw;
                    checkClawPress = false;
                }
            }else{
                checkClawPress = true;
            }
            if(gamepad2.dpad_left) {
                motorAxL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motorAxR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

            // incheietura

            // Limite
            if(targetPozSl < -1700) targetPozSl = -1700;
//            if(targetPozAx > 540) targetPozAx = 540;

            if(gamepad2.dpad_up) targetPozSl = -1600;
            if(gamepad2.dpad_left) targetPozSl = -1000;
            if(gamepad2.dpad_down) targetPozSl = -200;

            rotateAngle = motorAxL.getCurrentPosition()/540.0 * 360;
            motorAxL.setTargetPosition(targetPozAx);
            motorAxR.setTargetPosition(targetPozAx);
            slider.setTargetPosition(targetPozSl);

            telemetry.addData("angle ",rotateAngle);
            telemetry.addData("toggleCheck ",toggleCheck);
            telemetry.addData("paralelPose ",paralelPos);
            telemetry.addData("wrist ",wristPos);
            telemetry.addData("servo trying angle ",pos);
            telemetry.addData("ax ",targetPozAx);
            telemetry.addData("slider " ,targetPozSl);
            telemetry.update();
        }
    }
}