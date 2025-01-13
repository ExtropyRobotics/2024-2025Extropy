package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOPfirst")
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
    int targetPozAX = 0;



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


        sliderFix1.setTargetPosition(0);
        sliderFix2.setTargetPosition(0);
        sliderMob.setTargetPosition(0);
        motorAx.setTargetPosition(0);

        sliderFix1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderFix2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        double leftStickY;
        double leftStickX;
        double rightStickX;

        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()) {

            leftStickY = -gamepad1.left_stick_y;
            leftStickX = gamepad1.left_stick_x;
            rightStickX = gamepad1.right_stick_x;

            motorLF.setPower(leftStickY + leftStickX + rightStickX);
            motorRB.setPower(leftStickY + leftStickX - rightStickX);
            motorLB.setPower(leftStickY - leftStickX + rightStickX);
            motorRF.setPower(leftStickY - leftStickX - rightStickX);


            // Slider mobil
            if (gamepad2.left_stick_y > 0) targetPozSM += 5;
            if (gamepad2.left_stick_y < 0) targetPozSM -= 4;
            sliderMob.setTargetPosition(targetPozSM);
            sliderMob.setPower(0.7);

            // Motor ax
            if (gamepad2.right_stick_y > 0) targetPozAX += (1 - targetPozSM / (-2500)) + 1;
            if (gamepad2.right_stick_y < 0) targetPozAX -= (1 - targetPozSM / (-2500)) + 1;
            motorAx.setTargetPosition(targetPozAX);
            motorAx.setPower(0.5);

            // Limite
//            if(targetPozAX > 60) targetPozAX = 60;
//            if(targetPozAX < -546) targetPozAX = -546;
//
//            if(targetPozSM > 0) targetPozSM = 0;
//            if(targetPozSM < -2500) targetPozSM = -2500;

            // Cleste
            if (gamepad2.left_trigger > 0) cleste.setPosition(0);
            if (gamepad2.right_trigger > 0) cleste.setPosition(0.6);


//            // Butoane pt fast movement
//            if(gamepad2.dpad_up) targetPozSM = -2500;
//            if(gamepad2.dpad_left) targetPozSM = -1230;
//            if(gamepad2.dpad_down) targetPozSM = 0;
//
//            if(gamepad2.y) targetPozAX = -546;
//            if(gamepad2.a) targetPozAX = 0;


            // Useless
            telemetry.addData("SliderMobil: ", targetPozSM);
            telemetry.addData("MotorAx: ", targetPozAX);
            telemetry.update();
            }
        }
    }
