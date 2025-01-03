package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Anti_prosti")
public class Anti_prosti extends LinearOpMode {

    DcMotor motorRF;
    DcMotor motorRB;
    DcMotor motorLB;
    DcMotor motorLF;
    DcMotor motorAx;
    DcMotor sliderMob;
    DcMotor sliderFix1;
    DcMotor sliderFix2;
    Servo cleste;
    Servo rabatare1;
    Servo rabatare2;
    Servo aspirator;
    double targetPozSF1 = 0;
    double targetPozSF2 = 0;
    int targetPozSM = 0;
    int targetPozMA = 0;
    double step = 4;


    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        motorRF = hardwareMap.get(DcMotor.class, "rightFront");
        motorRB = hardwareMap.get(DcMotor.class, "rightBack");
        motorLB = hardwareMap.get(DcMotor.class, "leftBack");
        motorLF = hardwareMap.get(DcMotor.class, "leftFront");

        sliderFix1 = hardwareMap.get(DcMotor.class, "sliderfix1");
        sliderFix2 = hardwareMap.get(DcMotor.class, "sliderfix2");
        sliderMob = hardwareMap.get(DcMotor.class, "slidermobil");

        motorAx = hardwareMap.get(DcMotor.class, "motorax");

        cleste = hardwareMap.get(Servo.class, "cleste");
        rabatare1 = hardwareMap.get(Servo.class, "rabatare1");
        rabatare2 = hardwareMap.get(Servo.class, "rabatare2");
        aspirator = hardwareMap.get(Servo.class, "aspirator");


        sliderFix1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderFix1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderFix1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderFix2.setDirection(DcMotorSimple.Direction.REVERSE);
        sliderFix2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderFix2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderFix2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderMob.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderMob.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMob.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorAx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderFix1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderFix2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        double stickLeftY;
        double stickLeftX;
        double stickRightX;

        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()) {

            stickLeftY = -gamepad1.left_stick_y;
            stickLeftX = gamepad1.left_stick_x;
            stickRightX = gamepad1.right_stick_x;

            motorLF.setPower(stickLeftY + stickLeftX + stickRightX);
            motorRB.setPower(stickLeftY + stickLeftX - stickRightX);
            motorLB.setPower(stickLeftY - stickLeftX + stickRightX);
            motorRF.setPower(stickLeftY - stickLeftX - stickRightX);

//            if (gamepad2.left_stick_y < 0) {
//                targetPozSF1 -= step;
//                targetPozSF2 -= step;
//            }
//            if (gamepad2.left_stick_y > 0) {
//                targetPozSF1 += step;
//                targetPozSF2 += step;
//            }
//
//            sliderFix1.setTargetPosition((int) targetPozSF1);
//            sliderFix2.setTargetPosition((int) targetPozSF2);
//            sliderFix1.setPower(0.6);
//            sliderFix2.setPower(0.6);


            if (gamepad2.right_stick_y > 0)
                targetPozSM += 2;
            if (gamepad2.right_stick_y < 0)
                targetPozSM -= 2;

            sliderMob.setTargetPosition(targetPozSM);
            sliderMob.setPower(0.5);

            if(targetPozSM > 30)
                targetPozSM = 30;
            if(targetPozSM < -546)
                targetPozSM = -546;

            if (gamepad2.left_stick_y > 0)
                targetPozMA += 8;
            if (gamepad2.left_stick_y < 0)
                targetPozMA -= 6;

            motorAx.setTargetPosition(targetPozMA);
            motorAx.setPower(0.7);

            if(targetPozMA > 0)
                targetPozMA = 0;
            if(targetPozMA < -7600)
                targetPozMA = -7600;

            if (gamepad2.dpad_left)
                cleste.setPosition(0);
            if (gamepad2.dpad_right)
                cleste.setPosition(0.09);

            telemetry.addData("SliderMobil: ", targetPozSM);
            telemetry.addData("MotorAx: ", targetPozMA);
            telemetry.update();


        }
    }
}