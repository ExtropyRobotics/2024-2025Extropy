package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "'testeaigesiuabhdasbkd'")
public class Teste  extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor backLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = hardwareMap.get(DcMotor.class, "rightFront");
        backLeft = hardwareMap.get(DcMotor.class, "leftBack");
        backRight = hardwareMap.get(DcMotor.class, "rightBack");

        waitForStart();

        frontLeft.setPower(0.5);
        sleep(1000);
        frontLeft.setPower(0);
        sleep(1000);

        backRight.setPower(0.5);
        sleep(1000);
        backRight.setPower(0);
        sleep(1000);

        frontRight.setPower(0.5);
        sleep(1000);
        frontRight.setPower(0);
        sleep(1000);

        backLeft.setPower(0.5);
        sleep(1000);
        backLeft.setPower(0);
        sleep(1000);


    }
}
