package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name ="TeleOp_Tabara_de_zi")

public class TeleOp_Tabara_de_zi extends LinearOpMode {
DcMotor motorStangaFata = null;
DcMotor motorDreaptaFata = null;
DcMotor motorStangaSpate = null;
DcMotor motorDreaptaSpate = null;
DcMotor motorBrat1 = null;
DcMotor motorBrat2 = null;
Servo servoLeft = null;
Servo servoRight = null;
    double sticklefty = 0;
    double stickleftx = 0;
    double stickrightx = 0;
    int targetPoz = 0;
    @Override
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motorStangaFata = hardwareMap.get(DcMotor.class, "motorStangaFata");
        motorDreaptaFata = hardwareMap.get(DcMotor.class, "motorDreaptaFata");
        motorStangaSpate = hardwareMap.get(DcMotor.class, "motorStangaSpate");
        motorDreaptaSpate = hardwareMap.get(DcMotor.class, "motorDreaptaSpate");
        motorBrat1 = hardwareMap.get(DcMotor.class, "motorBrat1");
        motorBrat2 = hardwareMap.get(DcMotor.class, "motorBrat2");

        servoLeft = hardwareMap.get(Servo.class,"servoLeft");
        servoRight = hardwareMap.get(Servo.class,"servoRight");

        motorBrat1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBrat2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorStangaSpate.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStangaFata.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBrat2.setDirection(DcMotorSimple.Direction.REVERSE);

        servoRight.setDirection(Servo.Direction.REVERSE);


        waitForStart();
        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;
            stickrightx = gamepad1.right_stick_x;

            motorStangaFata.setPower(sticklefty + stickleftx + stickrightx);
            motorDreaptaFata.setPower(sticklefty - stickleftx - stickrightx);
            motorDreaptaSpate.setPower(sticklefty + stickleftx - stickrightx);
            motorStangaSpate.setPower(sticklefty - stickleftx + stickrightx);


            if (gamepad2.right_stick_y < 0) {
                targetPoz += 2;
            }
            if (gamepad2.right_stick_y > 0) {
                targetPoz -= 2;
            }
            if (targetPoz<0)
                targetPoz = 0;
            if (targetPoz>150)
                targetPoz = 150;
            motorBrat1.setTargetPosition(targetPoz);
            motorBrat2.setTargetPosition(targetPoz);
            motorBrat1.setPower(0.25);
            motorBrat2.setPower(0.25);
            motorBrat1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorBrat2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            if(gamepad2.b) {
                servoRight.setPosition(0.25);
                servoLeft.setPosition(0.25);
            }
            if(gamepad2.x) {
                servoRight.setPosition(0.03);
                servoLeft.setPosition(0.03);
            }
//            servoRight.setPosition(0);
//            servoLeft.setPosition(0);
        }
    }
}
