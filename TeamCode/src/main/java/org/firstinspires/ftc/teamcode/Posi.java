package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "Test IMU")
public class Posi extends LinearOpMode {
    SampleMecanumDrive drive;
    ArmControler brat;
    LynxModule control;
    int targetAx;
    int targetSlider;

    int isRotating = 0;
    int isSliding = 0;


    @Override
    public void runOpMode() {
        ImuOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.DOWN);
        BHI260IMU.Parameters parameters = new BHI260IMU.Parameters(orientation);

        drive = new SampleMecanumDrive(hardwareMap);
        brat = new ArmControler(hardwareMap, telemetry, gamepad2);
        control = hardwareMap.get(LynxModule.class,"Control Hub");

        while(opModeInInit()){
            telemetry.addData("Voltage : ", control.getInputVoltage(VoltageUnit.MILLIVOLTS));
            telemetry.update();
        }
        waitForStart();

        brat.setPower(0.6);

        while (opModeIsActive()) {
            telemetry.addData("Battery Voltage ",control.getInputVoltage(VoltageUnit.MILLIVOLTS));
            telemetry.addData("Overall Amperage ", control.getCurrent(CurrentUnit.MILLIAMPS));

            drive.setWeightedDrivePower(new Pose2d(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            ));

            if(gamepad2.left_bumper) brat.resetEncoders();

            brat.hand();
//             && isRotating != 1
//             && isRotating != -1

            if(gamepad2.left_stick_y < 0 && targetAx < 1700) targetAx += 20;
            if(gamepad2.left_stick_y > 0 && targetAx > -50) targetAx -= 20;
            if(gamepad2.right_stick_y < 0 && targetSlider < 2100) targetSlider += 20;
            if(gamepad2.right_stick_y > 0 && targetSlider > -50) targetSlider -= 20;

            isRotating = brat.setAxPoz(targetAx);
            isSliding = brat.setSliderPoz(targetSlider);

            brat.callTelemetry();
            telemetry.update();
        }
    }
}