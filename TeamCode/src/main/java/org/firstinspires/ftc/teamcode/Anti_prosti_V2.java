package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "newBotTeleOp")

public class Anti_prosti_V2 extends LinearOpMode {

    DcMotor motorLf = null;
    DcMotor motorRf = null;
    DcMotor motorLb = null;
    DcMotor motorRb = null;

    DcMotor motorAxR = null;
    DcMotor motorAxL = null;

    DcMotor sliderR = null;
    DcMotor sliderL = null;

    double leftStickY;
    double leftStickX;
    double rightStickX;

    int targetPozAx = 0;

    int targetPozSl = 0;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(), telemetry);

        motorLf = hardwareMap.get(DcMotor.class,"Lf");
        motorRf = hardwareMap.get(DcMotor.class,"Rf");
        motorLb = hardwareMap.get(DcMotor.class,"Lb");
        motorRb = hardwareMap.get(DcMotor.class,"Rb");

        motorAxL = hardwareMap.get(DcMotor.class,"AxLeft");
        motorAxR = hardwareMap.get(DcMotor.class,"AxRight");

        sliderL = hardwareMap.get(DcMotor.class,"sliderLeft");
        sliderR = hardwareMap.get(DcMotor.class,"sliderRight");

        motorRf.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRb.setDirection(DcMotorSimple.Direction.REVERSE);

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

        waitForStart();
        while (opModeIsActive()) {

            leftStickY = -gamepad1.left_stick_y;
            leftStickX = gamepad1.left_stick_x;
            rightStickX = gamepad1.right_stick_x;

            motorLf.setPower(leftStickY + leftStickX - rightStickX);
            motorRb.setPower(leftStickY + leftStickX + rightStickX);
            motorLb.setPower(leftStickY - leftStickX - rightStickX);
            motorRf.setPower(leftStickY - leftStickX + rightStickX);

            if(gamepad2.left_stick_y<0){
                targetPozAx +=5;
            }

            if(gamepad2.left_stick_y>0){
                targetPozAx -=3;
            }
            if(targetPozAx<0){
                targetPozAx = 0;
            }
            if(targetPozAx>500){
                targetPozAx = 500;
            }
            motorAxR.setPower(0.25);
            motorAxL.setPower(0.25);

            motorAxL.setTargetPosition(targetPozAx);
            motorAxR.setTargetPosition(targetPozAx);

            motorAxL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorAxR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            telemetry.addData("",targetPozAx);
            telemetry.update();

//              NU SE POATE TESTA INCA!!!
//
//            if(gamepad2.right_stick_y>0){
//                targetPozSl +=8;
//            }
//            if(gamepad2.right_stick_y<0){
//                targetPozSl -=6;
//            }
//            if(targetPozSl<0){
//                targetPozSl=0;
//            }
//            if(targetPozSl>7500){
//                targetPozSl=7500;
//            }
//
//            sliderL.setPower(0.6);
//            sliderR.setPower(0.6);
//
//            sliderL.setTargetPosition(targetPozSl);
//            sliderR.setTargetPosition(targetPozSl);
//
//            sliderL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            sliderR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        }
    }

}
