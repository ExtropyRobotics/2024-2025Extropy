package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TestRecruti", group = "Test")
public class TestRecruti extends LinearOpMode {

    DcMotor motorStangaF = null;
    DcMotor motorStangaS = null;
    DcMotor motorDreaptaF = null;
    DcMotor motorDreaptaS = null;
    DcMotor armLeft = null;
    DcMotor armRight = null;

    double sticklefty = 0;
    double stickleftx = 0;
    double stickrightx = 0;
    int targetPoz = 0;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        motorDreaptaF = hardwareMap.get(DcMotor.class, "motorDreaptaFata");
        motorDreaptaS = hardwareMap.get(DcMotor.class, "motorDreaptaSpate");
        motorStangaF = hardwareMap.get(DcMotor.class, "motorStangaFata");
        motorStangaS = hardwareMap.get(DcMotor.class, "motorStangaSpate");

        armLeft = hardwareMap.get(DcMotor.class,"armLeft");
        armRight = hardwareMap.get(DcMotor.class,"armRight");

        armRight.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStangaF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStangaS.setDirection(DcMotorSimple.Direction.REVERSE);

        armLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;
            stickrightx = gamepad1.right_stick_x;

            motorStangaF.setPower(sticklefty + stickleftx + stickrightx);
            motorDreaptaF.setPower(sticklefty - stickleftx - stickrightx);
            motorDreaptaS.setPower(sticklefty + stickleftx - stickrightx);
            motorStangaS.setPower(sticklefty - stickleftx + stickrightx);

            if(gamepad1.dpad_up)targetPoz += 3;
            if(gamepad1.dpad_down)targetPoz -= 3;

            if(targetPoz > 1000) targetPoz = targetPoz;
            if(targetPoz < 0) targetPoz = targetPoz;

            armLeft.setTargetPosition(targetPoz);
            armRight.setTargetPosition(targetPoz);
            armLeft.setPower(0.25);
            armRight.setPower(0.25);
            armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("left ",armLeft.getCurrentPosition());
            telemetry.addData("right ",armRight.getCurrentPosition());
            telemetry.update();
        }
    }
}
