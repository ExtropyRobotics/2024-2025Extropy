package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name ="Into_the_Deep")
public class Into_the_Deep extends LinearOpMode {

    DcMotor motorStangaF = null;
    DcMotor motorStangaS = null;
    DcMotor motorDreaptaF = null;
    DcMotor motorDreaptaS = null;
    DcMotor motorHandLow = null;
    DcMotor motorHandHigh = null;

    double sticklefty = 0;
    double stickleftx = 0;
    double stickrightx = 0;
    int targetPoz = 0;
    int targetPoz2 = 0;

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motorDreaptaF = hardwareMap.get(DcMotor.class, "motorDreaptaF");
        motorDreaptaS = hardwareMap.get(DcMotor.class, "motorDreaptaS");
        motorStangaF = hardwareMap.get(DcMotor.class, "motorStangaF");
        motorStangaS = hardwareMap.get(DcMotor.class, "motorStangaS");

        motorHandLow = hardwareMap.get(DcMotor.class,"HandLow");
        motorHandHigh = hardwareMap.get(DcMotor.class,"HandHigh");

        motorStangaS.setDirection(DcMotorSimple.Direction.REVERSE);
        motorDreaptaF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorHandHigh.setDirection(DcMotorSimple.Direction.REVERSE);

        motorHandLow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorHandHigh.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorHandLow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorHandHigh.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        telemetry.addData("",motorHandLow.getCurrentPosition());
        telemetry.addData("",motorHandHigh.getCurrentPosition());

        waitForStart();
        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;
            stickrightx = gamepad1.right_stick_x;

            motorStangaF.setPower(sticklefty + stickleftx + stickrightx);
            motorDreaptaF.setPower(sticklefty - stickleftx - stickrightx);
            motorDreaptaS.setPower(sticklefty + stickleftx - stickrightx);
            motorStangaS.setPower(sticklefty - stickleftx + stickrightx);


            if (gamepad2.right_stick_y < 0)
                targetPoz += 7;

            if (gamepad2.right_stick_y > 0)
                targetPoz -= 6;

            if (targetPoz<0)
                targetPoz = 0;
            if (targetPoz>1650)
                targetPoz = 1650;
            motorHandLow.setTargetPosition(targetPoz);
            motorHandLow.setPower(0.25);
            motorHandLow.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            if (gamepad2.dpad_up)
                targetPoz2 +=5;

            if (gamepad2.dpad_down)
                targetPoz2 -=5;

            if (targetPoz2<0)
                targetPoz2 = 0;
            if (targetPoz2>3000)
                targetPoz2 = 3000;
            motorHandHigh.setTargetPosition(targetPoz2);
            motorHandHigh.setPower(0.25);
            motorHandHigh.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.update();
        }
    }
}
