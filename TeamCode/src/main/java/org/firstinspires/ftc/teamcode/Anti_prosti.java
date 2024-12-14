package org.firstinspires.ftc.teamcode;

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
    DcMotor sliderFix;
    Servo cleste;

    int TargetPozSF = 1;
    int TargetPozSM = 1;
    int TargetPozMA = 1;

    @Override
    public void runOpMode() throws InterruptedException{

        motorRF = hardwareMap.get(DcMotor.class, "fatadr");
        motorRB = hardwareMap.get(DcMotor.class, "spatedr");
        motorLB = hardwareMap.get(DcMotor.class, "spatestg");
        motorLF = hardwareMap.get(DcMotor.class, "fatastg");
        sliderFix = hardwareMap.get(DcMotor.class, "sliderfix");
        sliderMob = hardwareMap.get(DcMotor.class, "slidermobil");
        motorAx = hardwareMap.get(DcMotor.class, "motorax");
        cleste = hardwareMap.get(Servo.class, "cleste");

        sliderFix.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderFix.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderFix.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderMob.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderMob.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMob.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorAx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        double stickLeftY;
        double stickLeftX;
        double stickRightX;

        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);

        while(opModeIsActive()){

            stickLeftY = -gamepad1.left_stick_y;
            stickLeftX = gamepad1.left_stick_x;
            stickRightX = gamepad1.right_stick_x;

            motorLF.setPower(stickLeftY + stickLeftX + stickRightX);
            motorRB.setPower(stickLeftY + stickLeftX - stickRightX);
            motorLB.setPower(stickLeftY - stickLeftX + stickRightX);
            motorRF.setPower(stickLeftY - stickLeftX - stickRightX);


            if(gamepad2.left_stick_y < 0)
                TargetPozSF -= 2;
            if(gamepad2.left_stick_y > 0)
                TargetPozSF += 2;

            sliderFix.setTargetPosition(TargetPozSF);
            sliderFix.setPower(0.4);


            if(gamepad2.right_stick_y < 0)
                TargetPozSM += 2;
            if(gamepad2.right_stick_y > 0)
                TargetPozSM -= 2;

            sliderMob.setTargetPosition(TargetPozSM);
            sliderMob.setPower(0.4);


            if(gamepad2.right_bumper)
                TargetPozMA +=2;
            if(gamepad2.left_bumper)
                TargetPozMA -= 2;

            motorAx.setTargetPosition(TargetPozMA);
            motorAx.setPower(0.4);


            if(gamepad2.dpad_left)
                cleste.setPosition(0);
            if(gamepad2.dpad_right)
                cleste.setPosition(0.09);


            sliderFix.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }
}