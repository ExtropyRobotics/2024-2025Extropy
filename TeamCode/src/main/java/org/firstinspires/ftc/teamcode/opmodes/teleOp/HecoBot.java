package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "OktoberfestBot")
public class HecoBot extends LinearOpMode {
    DcMotor motorStanga = null;
    DcMotor motorDreapta = null;
    DcMotor spinner;
    DcMotor hand = null;
    Servo servoGear = null;
    Servo servoHand = null;
    double sticklefty = 0;
    double stickrightx = 0;
    int targetPoz = 0;
    double targetPoz2 = 0;
    double targetPower = 0;

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        spinner = hardwareMap.get(DcMotor.class, "spinner");
        motorDreapta = hardwareMap.get(DcMotor.class, "motorDreapta");
        motorStanga = hardwareMap.get(DcMotor.class, "motorStanga");
        hand = hardwareMap.get(DcMotor.class, "hand");

        servoGear = hardwareMap.get(Servo.class,"servoGear");
        servoHand = hardwareMap.get(Servo.class,"servoHand");

        motorDreapta.setDirection(DcMotorSimple.Direction.REVERSE);
        hand.setDirection(DcMotorSimple.Direction.REVERSE);

        hand.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickrightx = gamepad1.right_stick_x;

            motorStanga.setPower(sticklefty - stickrightx);
            motorDreapta.setPower(sticklefty + stickrightx);

           if (gamepad1.b)
               servoHand.setPosition(0.3);
           if (gamepad1.x)
               servoHand.setPosition(0.705);

            if(gamepad1.dpad_right)
                targetPoz2 += 0.0002;
            if(gamepad1.dpad_left)
                targetPoz2 -= 0.0002;
            if (targetPoz2 < 0.0)
                targetPoz2 = 0.0;

            if (targetPoz2 > 0.2)
                targetPoz2 = 0.2;

            servoGear.setPosition(targetPoz2);

            if(gamepad1.left_bumper)targetPower += 0.002;
            if(gamepad1.right_bumper)targetPower -= 0.002;
            if(targetPower>1)targetPower = 1;
            if(targetPower<-1)targetPower = -1;

            spinner.setPower(targetPower);
            telemetry.addData("power : ",targetPower);
            telemetry.update();


            if(gamepad1.y)
                targetPoz +=1;
            if(gamepad1.a)
                targetPoz -=1;
            if(targetPoz<0)
                targetPoz = 0;
            if(targetPoz>1000)
                targetPoz = 1000;
            hand.setTargetPosition(targetPoz);
            hand.setPower(0.2);
            hand.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.addData("",targetPoz);
            telemetry.update();
        }
    }
}