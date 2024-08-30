package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "happyNigggation")
public class EuSuntMeniul extends LinearOpMode {

    DcMotor motorStangaFata = null;
    DcMotor motorStangaSpate = null;
    DcMotor motorDreaptaFata = null;
    DcMotor motorDreaptaSpate = null;

    @Override
    public void runOpMode() throws InterruptedException {

        motorStangaFata = hardwareMap.get(DcMotor.class,"motorStangaFata");
        motorStangaSpate = hardwareMap.get(DcMotor.class,"motorStangaSpate");
        motorDreaptaFata = hardwareMap.get(DcMotor.class,"motorDreaptaFata");
        motorDreaptaSpate = hardwareMap.get(DcMotor.class,"motorDreaptaSpate");

        motorStangaFata.setDirection(DcMotor.Direction.REVERSE);
        motorStangaSpate.setDirection(DcMotor.Direction.REVERSE);

        motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorStangaSpate.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorStangaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorStangaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorDreaptaFata.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorDreaptaSpate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        run(800, 0.5);
        turning(750, 0.4);
        run(700, 0.5);
        turning(750, 0.4);
        run(800, 0.5);
        turning(750, 0.4);
        run(700, 0.5);


    }

    public void run (int targetPos, double power){

        motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorDreaptaSpate.setTargetPosition(targetPos);
        motorStangaSpate.setTargetPosition(targetPos);

        motorDreaptaSpate.setPower(power);
        motorStangaSpate.setPower(power);

        if(targetPos<=0) {
            motorDreaptaFata.setPower(-power);
            motorStangaFata.setPower(-power);
        }
        else {
            motorDreaptaFata.setPower(power);
            motorStangaFata.setPower(power);
        }

        motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorStangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorDreaptaSpate.isBusy() || motorStangaSpate.isBusy()){
            sleep(1);
        }
        motorDreaptaFata.setPower(0);
        motorStangaFata.setPower(0);
        motorStangaSpate.setPower(0);
        motorDreaptaSpate.setPower(0);
        sleep(500);
    }

    public void turning (int targetPos, double power){

        motorDreaptaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorStangaSpate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorDreaptaSpate.setTargetPosition(-targetPos);
        motorStangaSpate.setTargetPosition(targetPos);

        motorDreaptaSpate.setPower(-power);
        motorStangaSpate.setPower(power);

        if(targetPos<=0) {
            motorDreaptaFata.setPower(power);
            motorStangaFata.setPower(-power);
        }
        else {
            motorDreaptaFata.setPower(power);
            motorStangaFata.setPower(power);
        }

        motorDreaptaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorStangaSpate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(motorDreaptaSpate.isBusy() || motorStangaSpate.isBusy()){
            sleep(1);
        }

        motorDreaptaFata.setPower(0);
        motorStangaFata.setPower(0);
        motorStangaSpate.setPower(0);
        motorDreaptaSpate.setPower(0);
        sleep(500);

    }


//        while (opModeIsActive()){
//
//            motorStangaFata.setPower(-(gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x));
//            motorStangaSpate.setPower(-(gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x));
//            motorDreaptaFata.setPower(-(gamepad1.left_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x));
//            motorDreaptaSpate.setPower(-(gamepad1.left_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x));
//
//        }

}
