package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Suferinta1")
public final class Suferinta1 extends LinearOpMode {
    DcMotor motorLB = null;
    DcMotor motorLF = null;
    DcMotor motorRB = null;
    DcMotor motorRF = null;
    DcMotor motorBrat = null;
    DcMotor motorSlideS = null;
    DcMotor motorSlideD = null;
    Servo sample1 = null;
    Servo wrist = null;


    boolean leftdpad = true;

    @Override
    public void runOpMode() throws InterruptedException {

        motorLB = hardwareMap.get(DcMotor.class, "perp");
        motorLF = hardwareMap.get(DcMotor.class, "par1");
        motorRB = hardwareMap.get(DcMotor.class, "par0");
        motorRF = hardwareMap.get(DcMotor.class, "rightFront");
        motorBrat = hardwareMap.get(DcMotor.class, "motorBrat");
        motorSlideS = hardwareMap.get(DcMotor.class, "motorSlideS");
        motorSlideD = hardwareMap.get(DcMotor.class, "motorSlideD");
        wrist = hardwareMap.get(Servo.class, "servoWrist");

        waitForStart();

        double stickleftx = 0;
        double stickrighty = 0;

        while (opModeIsActive()){

        stickleftx = -gamepad1.left_stick_x;
        stickrighty = gamepad1.right_stick_y;

        motorLF.setPower(stickleftx + stickrighty);
        motorRB.setPower(stickleftx - stickrighty);
        motorLB.setPower(stickleftx + stickrighty);
        motorRF.setPower(stickleftx - stickrighty);
        motorBrat.setPower(0);

        if(gamepad1.right_bumper){
            motorSlideS.setPower(-0.6);
            motorSlideD.setPower(0.6);
        }
        if(gamepad1.right_trigger > 0){
            motorSlideS.setPower(0);
            motorSlideD.setPower(0);
        }
        if(gamepad1.left_bumper){
            motorSlideS.setPower(0.6);
            motorSlideD.setPower(-0.6);
        }
        if(gamepad1.left_trigger > 0){
            motorSlideS.setPower(0);
            motorSlideD.setPower(0);
        }

        if(gamepad1.dpad_up){
            motorBrat.setPower(-0.5);
        }
        if(gamepad1.dpad_down) {
            motorBrat.setPower(0.5);
        }

        if(gamepad1.dpad_left) {
            wrist.setPosition(0.5);

        }
        if(gamepad1.dpad_right){
            wrist.setPosition(0);
            telemetry.addData("wrist", 2);
            telemetry.update();
        }

        }

    }
}