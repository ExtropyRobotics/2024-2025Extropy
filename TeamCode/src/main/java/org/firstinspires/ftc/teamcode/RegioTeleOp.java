package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "!!! REGIONALA TELEOP")
public class RegioTeleOp extends LinearOpMode {
    SampleMecanumDrive drive;
    ArmControler brat;
    LynxModule control;

    int targetAx;
    int targetSlider;

    int isRotating = 0;
    int isSliding = 0;

    boolean parallelToggle = false;
    boolean parallelOnce = false;

    boolean clawToggle = false;
    boolean clawOnce = false;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(),telemetry);
        drive = new SampleMecanumDrive(hardwareMap);
        brat = new ArmControler(hardwareMap, telemetry);

        control = hardwareMap.get(LynxModule.class,"Control Hub");

        while(opModeInInit()){
            telemetry.addData("Voltage : ", control.getInputVoltage(VoltageUnit.MILLIVOLTS));
            telemetry.update();
        }
        waitForStart();

        brat.setPower(0.3);

        while (opModeIsActive()) {
            telemetry.addData("Battery Voltage ",control.getInputVoltage(VoltageUnit.MILLIVOLTS));

            drive.setWeightedDrivePower(new Pose2d(
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            ));

            // set the wrist to be parallel to ground or parallel to bar
            if(gamepad2.right_bumper){
                if(parallelToggle) {
                    if(parallelOnce) brat.setWristParalel(0.55, 1);
                    else brat.setWristParalel(0.2, 1);

                    parallelOnce = !parallelOnce;
                    parallelToggle = false;
                }
            } else parallelToggle = true;

            // toggle the claw from closed to open
            if(gamepad2.a){
                if(!clawToggle){
                    if(clawOnce) brat.setClaw(0.5);
                    else brat.setClaw(0.6);

                    clawOnce = !clawOnce;
                    clawToggle = true;
                }
            } else clawToggle = false;

            if(gamepad2.left_stick_y < 0 && targetAx < 900) targetAx += 20;
            if(gamepad2.left_stick_y > 0 && targetAx > 0) targetAx -= 20;
            if(gamepad2.right_stick_y < 0 && targetSlider < 2100) targetSlider += 40;
            if(gamepad2.right_stick_y > 0 && targetSlider > 0) targetSlider -= 40;

            if(gamepad2.dpad_up && targetAx < 900) targetAx += 5;
            if(gamepad2.dpad_down && targetAx > 0) targetAx -= 5;
            if(gamepad2.dpad_left && targetSlider < 2100) targetSlider += 5;
            if(gamepad2.dpad_right && targetSlider > 0) targetSlider -= 5;

            isRotating = brat.setAxPoz(targetAx);
            isSliding = brat.setSliderPoz(targetSlider);

            brat.callTelemetry();
            telemetry.update();
        }
    }
}