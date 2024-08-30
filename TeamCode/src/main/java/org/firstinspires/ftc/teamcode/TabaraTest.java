package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "tabara1Test")
public class TabaraTest extends LinearOpMode {

    DcMotor motorStF = null;
    DcMotor motorDrF = null;
    DcMotor motorStS = null;
    DcMotor motorDrS = null;

    @Override
    public void runOpMode() throws InterruptedException{

        motorStF = hardwareMap.get(DcMotor.class, "motorStF");
        motorDrF = hardwareMap.get(DcMotor.class, "motorDrF");
        motorStS = hardwareMap.get(DcMotor.class, "motorStS");
        motorDrS = hardwareMap.get(DcMotor.class, "motorDrS");

        motorStF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStS.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while(opModeIsActive()){

            motorStF.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            motorDrF.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            motorStS.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            motorDrS.setPower(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
        }








































//    DcMotor motorStF = null;
//    DcMotor motorDrF = null;
//    DcMotor motorStS = null;
//    DcMotor motorDrS = null;
//    DcMotor motorBratDr = null;
//    DcMotor motorBratSt = null;
//    Servo servoDr = null;
//    Servo servoSt = null;
//
//    int targetPoz = 1;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//        motorStF = hardwareMap.get(DcMotor.class, "motorStF");
//        motorDrF = hardwareMap.get(DcMotor.class, "motorDrF");
//        motorStS = hardwareMap.get(DcMotor.class, "motorStS");
//        motorDrS = hardwareMap.get(DcMotor.class, "motorDrS");
//        motorBratDr = hardwareMap.get(DcMotor.class, "motorBratDr");
//        motorBratSt = hardwareMap.get(DcMotor.class, "motorBratSt");
//
//        servoDr = hardwareMap.get(Servo.class, "servoDr");
//        servoSt = hardwareMap.get(Servo.class, "servoSt");
//
//        motorDrF.setDirection(DcMotorSimple.Direction.REVERSE);
//        motorDrS.setDirection(DcMotorSimple.Direction.REVERSE);
//        motorBratSt.setDirection(DcMotor.Direction.REVERSE);
//        servoDr.setDirection(Servo.Direction.REVERSE);
//
//        motorBratDr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorBratSt.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        motorBratDr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motorBratSt.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        motorStF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorStS.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorDrF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorDrS.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        motorBratSt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
////        motorBratDr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        waitForStart();
//
//        while (opModeIsActive()) {
//
//            motorStF.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x);
//            motorDrF.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
//            motorStS.setPower(gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);
//            motorDrS.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
//
//            if (gamepad2.a) {
//                servoDr.setPosition(0.03);
//                servoSt.setPosition(0.03);
//            }
//            if (gamepad2.x) {
//                servoDr.setPosition(0.25);
//                servoSt.setPosition(0.25);
//            }
//            if (gamepad2.left_stick_y > 0) {
//                targetPoz += 2;
//            }
//            if (gamepad2.left_stick_y < 0) {
//                targetPoz -= 2;
//            }
//            if (targetPoz < 0) {
//                targetPoz = 0;
//            }
//            if (targetPoz > 117) {
//                targetPoz = 117;
//            }
//
//            motorBratSt.setTargetPosition(targetPoz);
//            motorBratDr.setTargetPosition(targetPoz);
//
//            motorBratSt.setPower(0.4);
//            motorBratDr.setPower(0.4);
//
//            motorBratSt.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            motorBratDr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//            telemetry.addData("current Position", targetPoz);
//            telemetry.addData("motorBratSt Position", motorBratSt.getCurrentPosition());
//            telemetry.addData("motorBratDr Position", motorBratDr.getCurrentPosition());
//            telemetry.update();
//        }
//    }
}
}