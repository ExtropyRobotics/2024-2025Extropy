package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="test_andrei", group="!")
public class test_andrei extends LinearOpMode {

    DcMotor motorBrat1 = null;
    DcMotor motorBrat2 = null;
    Servo servoSus1 = null;
    Servo servoSus2 = null;
    Servo servoJos1 = null;
    Servo servoJos2 = null;
    int targetPoz = 0;
    double targetPoz2 = 1;
    double targetPoz3 = 0.7805;
    double pozMotor = 452;

    @Override
    public void runOpMode() {

        motorBrat1 = hardwareMap.get(DcMotor.class, "motorBrat1");
        motorBrat2 = hardwareMap.get(DcMotor.class, "motorBrat2");

        servoSus1 = hardwareMap.get(Servo.class, "servoSus1");
        servoSus2 = hardwareMap.get(Servo.class, "servoSus2");
        servoJos1 = hardwareMap.get(Servo.class, "servoJos1");
        servoJos2 = hardwareMap.get(Servo.class, "servoJos2");

        motorBrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorBrat1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBrat2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorBrat1.setDirection(DcMotorSimple.Direction.REVERSE);
        servoJos1.setDirection(Servo.Direction.REVERSE);
        servoSus1.setDirection(Servo.Direction.REVERSE);

        motorBrat1.setPower(0.5);
        motorBrat2.setPower(0.5);

        waitForStart();
        while (opModeIsActive()) {
            if (gamepad2.right_stick_y < 0) {
                targetPoz += 3;
                setPozMotor(targetPoz);
            }
            if (gamepad2.right_stick_y > 0) {
                targetPoz -= 4;
                setPozMotor(targetPoz);
            }
            if (targetPoz < 0) {
                targetPoz = 0;
                setPozMotor(targetPoz);
            }
            if (targetPoz > 1500) {
                targetPoz = 1500;
                setPozMotor(targetPoz);
            }
            if (gamepad2.left_stick_y < 0) {
                targetPoz2 += 0.00425;
                setPozServo(targetPoz2);
            }
            if (gamepad2.left_stick_y > 0) {
                targetPoz2 -= 0.00425;
                setPozServo(targetPoz2);
            }
            if (targetPoz2 < 0.9) {
                targetPoz2 = 0.9;
                setPozServo(targetPoz2);
            }
            if (targetPoz2 > 1) {
                targetPoz2 = 1;
                setPozServo(targetPoz2);
            }
            if (targetPoz >=464){
                setPozServo(targetPoz3);
            }

        }
    }

    public void setPozMotor(int targetPoz) {
        motorBrat1.setTargetPosition(targetPoz);
        motorBrat2.setTargetPosition(targetPoz);
        motorBrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPozServo(double targetPoz) {
        servoJos1.setPosition(targetPoz);
        servoJos2.setPosition(targetPoz);
    }
}
//464

