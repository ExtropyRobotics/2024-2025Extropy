package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name="Teleop_Robot_2024-2025")

public class Working_Teleop_Robot_2024_2025 extends LinearOpMode {
    DcMotor motorStangaFata = null;
    DcMotor motorDreaptaFata = null;
    DcMotor motorStangaSpate = null;
    DcMotor motorDreaptaSpate = null;
    DcMotor motorBrat1 = null;
    DcMotor motorBrat2 = null;
    DcMotor motorHang = null;
    Servo servoSus1 = null;
    Servo servoSus2 = null;
    Servo servoJos1 = null;
    Servo servoJos2 = null;
    Servo servoAvion = null;
    Servo servoHang = null;
    Rev2mDistanceSensor color1 = null;
    Rev2mDistanceSensor color2 = null;
    RevBlinkinLedDriver led =null;
    double sticklefty = 0;
    double stickleftx = 0;
    double stickrightx = 0;
    int targetPoz = 0;
    int targetPoz3 = 1200;
    double targetPozServo ;
    double dist1 = 0;
    double dist2 = 0;
    double unghiMotor;
    double unghiServo;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motorStangaFata = hardwareMap.get(DcMotor.class, "motorStangaFata");
        motorDreaptaFata = hardwareMap.get(DcMotor.class, "motorDreaptaFata");
        motorStangaSpate = hardwareMap.get(DcMotor.class, "motorStangaSpate");
        motorDreaptaSpate = hardwareMap.get(DcMotor.class, "motorDreaptaSpate");
        motorBrat1 = hardwareMap.get(DcMotor.class, "motorBrat1");
        motorBrat2 = hardwareMap.get(DcMotor.class, "motorBrat2");
        motorHang = hardwareMap.get(DcMotor.class,"motorHang");

        servoSus1 = hardwareMap.get(Servo.class, "servoSus1");
        servoSus2 = hardwareMap.get(Servo.class, "servoSus2");
        servoJos1 = hardwareMap.get(Servo.class, "servoJos1");
        servoJos2 = hardwareMap.get(Servo.class, "servoJos2");

        servoAvion = hardwareMap.get(Servo.class,"servoAvion");
        servoHang = hardwareMap.get(Servo.class,"servoHang");

        color2 = hardwareMap.get(Rev2mDistanceSensor.class,"color_2");
        color1 = hardwareMap.get(Rev2mDistanceSensor.class,"color_1");

        led = hardwareMap.get(RevBlinkinLedDriver.class,"led");

        motorBrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorHang.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorBrat1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBrat2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorStangaSpate.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStangaFata.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBrat1.setDirection(DcMotorSimple.Direction.REVERSE);
        servoJos1.setDirection(Servo.Direction.REVERSE);
        servoSus1.setDirection(Servo.Direction.REVERSE);
        servoHang.setDirection(Servo.Direction.REVERSE);;
        motorBrat1.setPower(0.5);
        motorBrat2.setPower(0.5);

        servoAvion.setPosition(0.69);

        waitForStart();

        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;
            stickrightx = gamepad1.right_stick_x;

            motorStangaFata.setPower(+sticklefty + stickleftx + stickrightx);
            motorStangaSpate.setPower(+sticklefty - stickleftx + stickrightx);
            motorDreaptaFata.setPower(-sticklefty + stickleftx + stickrightx);
            motorDreaptaSpate.setPower(+sticklefty + stickleftx - stickrightx);


            if (gamepad1.y) {
                servoAvion.setPosition(0);

            }
            if (gamepad2.x) {
                servoSus1.setPosition(0);
                servoSus2.setPosition(0.1);
            }
            if (gamepad2.a) {
                servoSus1.setPosition(0.45);
                servoSus2.setPosition(0.3);
            }
            if (gamepad1.x) {
                servoHang.setPosition(0);
            }
            if(gamepad1.b){
                servoHang.setPosition(1);
            }
            if (gamepad2.y) {
                motorHang.setPower(1);
                motorHang.setTargetPosition(targetPoz3);
                motorBrat1.setPower(1);
                motorBrat2.setPower(1);
                motorBrat1.setTargetPosition(0);
                motorBrat2.setTargetPosition(0);
            }
            if (gamepad2.right_stick_y < 0) {
                targetPoz += 8;
                setPozMotor(targetPoz);
            }
            if (gamepad2.right_stick_y > 0) {
                targetPoz -= 10;
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


            dist1 = color1.getDistance(DistanceUnit.CM);
            dist2 = color2.getDistance(DistanceUnit.CM);

            if (dist1 < 7 && dist2 < 5)
                led.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            if (dist1 > 7 && dist2 > 5)
                led.setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
            if ((dist1 < 7 || dist2 < 5) && !(dist1 < 7 && dist2 < 5))
                led.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);

            setPozServo();

            telemetry.addData("unghi motor  ", unghiMotor);
            telemetry.addData("unghi servo  ", unghiServo);

            telemetry.update();
        }
    }
    public void setPozMotor(int targetPoz){
        motorBrat1.setTargetPosition(targetPoz);
        motorBrat2.setTargetPosition(targetPoz);
        motorBrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBrat2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setPozServo(){
        unghiMotor = (targetPoz * 90)/452.0;
        if(unghiMotor >= 70){
            targetPozServo = (237-unghiMotor)/150*(-0.1)+1;
        };
        if (gamepad2.left_stick_y < 0) {
            targetPozServo = 1;
        }
        if (gamepad2.left_stick_y > 0) {
            targetPozServo = 0.9;
        }
        servoJos1.setPosition(targetPozServo);
        servoJos2.setPosition(targetPozServo);
    }
    //unghiServo = (targetPozServo-1)/(-0.1)*150+unghiMotor+30;
}