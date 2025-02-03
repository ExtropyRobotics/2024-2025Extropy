package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "newBotTeleOp")

public class Anti_prosti_V2 extends LinearOpMode {

    DcMotor motorLF = null;
    DcMotor motorRF = null;
    DcMotor motorLB = null;
    DcMotor motorRB = null;

    DcMotor motorAxR = null;
    DcMotor motorAxL = null;

    DcMotor sliderR = null;
    DcMotor sliderL = null;

//    Servo clawL = null;
//    Servo clawR = null;
//
//    Servo wristL = null;
//    Servo wristR = null;

    Servo c0;
    Servo c1;
    Servo clawLeft;
    Servo clawRight;
    Servo c4;
    Servo c5;
    Servo wristRight;
    Servo wristLeft;

    double leftStickY;
    double leftStickX;
    double rightStickX;

    int targetPozAx = 0;
    int targetPozSl = 0;

    boolean agatare = false;
    boolean inchis = true;

    boolean toggleWrist = false;
    boolean checkWristPress = false;

    boolean toggleClaw = false;
    boolean checkClawPress = false;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        motorLF = hardwareMap.get(DcMotor.class,"leftFront");
        motorRF = hardwareMap.get(DcMotor.class,"rightFront");
        motorLB = hardwareMap.get(DcMotor.class,"leftBack");
        motorRB = hardwareMap.get(DcMotor.class,"rightBack");

        motorAxL = hardwareMap.get(DcMotor.class,"axLeft");
        motorAxR = hardwareMap.get(DcMotor.class,"axRight");

        sliderL = hardwareMap.get(DcMotor.class,"sliderLeft");
        sliderR = hardwareMap.get(DcMotor.class,"sliderRight");

        clawLeft = hardwareMap.get(Servo.class,"servoc2");
        clawRight = hardwareMap.get(Servo.class,"servoc3");

        wristRight = hardwareMap.get(Servo.class,"servoe0");
        wristLeft = hardwareMap.get(Servo.class,"servoe1");

        wristRight.setDirection(Servo.Direction.REVERSE);
        clawRight.setDirection(Servo.Direction.REVERSE);

        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);

        motorAxL.setDirection(DcMotorSimple.Direction.REVERSE);
        
        sliderL.setDirection(DcMotorSimple.Direction.REVERSE);

        motorAxR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        motorAxL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderL.setPower(0.6);
        sliderR.setPower(0.6);

        waitForStart();

        while (opModeIsActive()) {

            //      Motoare Roti
            leftStickY = -gamepad1.left_stick_y;
            leftStickX = gamepad1.left_stick_x;
            rightStickX = gamepad1.right_stick_x;

            motorLF.setPower(leftStickY + leftStickX + rightStickX);
            motorRB.setPower(leftStickY + leftStickX - rightStickX);
            motorLB.setPower(leftStickY - leftStickX + rightStickX);
            motorRF.setPower(leftStickY - leftStickX - rightStickX);


            //      Aplicare Target Poz
            motorAxL.setTargetPosition(targetPozAx);
            motorAxR.setTargetPosition(targetPozAx);
            sliderL.setTargetPosition(targetPozSl);
            sliderR.setTargetPosition(targetPozSl);


            //      Motor Ax
            if(gamepad2.left_stick_y<0) targetPozAx +=5;
            if(gamepad2.left_stick_y>0) targetPozAx -=3;

            motorAxR.setPower(0.25);
            motorAxL.setPower(0.25);

            motorAxL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorAxR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            //      Slider
            if(gamepad2.right_stick_y<0) targetPozSl +=16;
            if(gamepad2.right_stick_y>0) targetPozSl -=6;

            sliderL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            sliderR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // slider agatare
            if(gamepad2.dpad_right) agatare = true;
            if(gamepad2.dpad_left) agatare = false;

            if(agatare){
                sliderL.setPower(0.9);
                sliderR.setPower(0.9);
            }

            if(!agatare){
                sliderL.setPower(0.6);
                sliderR.setPower(0.6);
            }

            if(gamepad2.y){
                if(checkWristPress) {
                    telemetry.addData("toggling position", 0);
                    telemetry.update();
                    if (toggleWrist) {
                        wristRight.setPosition(0.9);
                        wristLeft.setPosition(0.9);
                    } else {
                        wristRight.setPosition(0.95);
                        wristLeft.setPosition(0.95);
                    }
                    toggleWrist = !toggleWrist;
                    checkWristPress = false;
                }
            }else{
                telemetry.addData("released button", toggleWrist);
                telemetry.update();
                checkWristPress = true;
            }


            if(gamepad2.a){
                if(checkClawPress) {
                    telemetry.addData("toggling position", 0);
                    telemetry.update();
                    if (toggleClaw) {
                        clawLeft.setPosition(0.7);
                        clawRight.setPosition(0.7);
                    } else {
                        clawLeft.setPosition(1);
                        clawRight.setPosition(1);
                    }
                    toggleClaw = !toggleClaw;
                    checkClawPress = false;
                }
            }else{
                telemetry.addData("released button", 0);
                telemetry.update();
                checkClawPress = true;
            }






            //      Limite
//            if(targetPozAx<0) targetPozAx = 0;
//            if(targetPozAx>500) targetPozAx = 500;
//
//            if(targetPozSl<0) targetPozSl=0;
//            if(targetPozSl>3100) targetPozSl=3100;



//                  Cleste
//            if(gamepad2.right_trigger>0){
//                    clawLeft.setPosition(1);
//                    clawRight.setPosition(1);
//
//            }
//            if(gamepad2.left_trigger>0){
//                clawLeft.setPosition(0.7);
//                clawRight.setPosition(0.7);
//            }

//            //      Incheieteura
//            if(gamepad2.right_bumper){
//                wristR.setPosition(0.1);
//                wristL.setPosition(0.1);
//            }
//            if(gamepad2.left_bumper){
//                wristL.setPosition(0.7);
//                wristR.setPosition(0.7);
//            }

            //auto incheietura
//            if(targetPozAx>0&&targetPozAx<150){
//                wristL.setPosition(0.05);
//                wristR.setPosition(0.05);
//            }
//
//            if(targetPozAx>200&&targetPozAx<300){
//                wristL.setPosition(0.7);
//                wristR.setPosition(0.7);
//            }
//            if(targetPozAx>400&&targetPozAx<550){
//                wristL.setPosition(0.9);
//                wristR.setPosition(0.9);
//            }

//            telemetry.addData("telemetry freq: ", telemetry.getMsTransmissionInterval());
//            telemetry.update();


//            if(gamepad1.dpad_right){
//                wristR.setPosition(1);
//                wristL.setPosition(1);
//

//            }



            if(gamepad1.dpad_left){
                clawLeft.setPosition(1);
            }
            if(gamepad1.dpad_up){
                clawRight.setPosition(1);
            }


//            if(gamepad1.right_trigger > 0.01){
//                wristRight.setPosition(0.09);
//                wristLeft.setPosition(0.09);
//            }
//            if(gamepad1.left_trigger > 0.01){
//                wristRight.setPosition(0.05);
//                wristLeft.setPosition(0.05);
//            }

//            if(gamepad1.y){
//                e0.setPosition(0.9);
//            }
            if(gamepad1.b){
                wristLeft.setPosition(0.9);
            }
        }
    }
}