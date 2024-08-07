package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import static org.firstinspires.ftc.teamcode.modules.State.HandState.*;
import static org.firstinspires.ftc.teamcode.modules.State.LiftState.*;
import static org.firstinspires.ftc.teamcode.modules.State.WristState.*;
import static org.firstinspires.ftc.teamcode.modules.State.PlaneState.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.modules.Controller.ArmController;
//import org.firstinspires.ftc.teamcode.modules.Controller.LedController;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.modules.State.WristState;

import org.firstinspires.ftc.teamcode.modules.Controller.PlaneController;
import org.firstinspires.ftc.teamcode.modules.State.PlaneState;

@TeleOp(name = "!!!!!!!DejTeleOp", group = "TeleOp")
public class DejTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        LiftState liftState = DEFAULT;
        WristState wristState = CARRY;
        PlaneState planeState = HOLD;

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmController arm = new ArmController(hardwareMap, telemetry, this);
        PlaneController plane = new PlaneController(hardwareMap, telemetry);

        waitForStart();
        while(opModeIsActive()){
            drive.setWeightedDrivePower(new Pose2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));

            if(gamepad2.dpad_up)liftState = HIGH;
            if(gamepad2.dpad_left)liftState = MID;
            if(gamepad2.dpad_down)liftState = LOW;
            if(gamepad2.dpad_right)liftState = DEFAULT;

            if(gamepad2.left_bumper)arm.setHandState(LEFT_OPEN);
            if(gamepad2.right_bumper)arm.setHandState(RIGHT_OPEN);
            if(gamepad2.left_trigger > 0)arm.setHandState(LEFT_CLOSE);
            if(gamepad2.right_trigger > 0)arm.setHandState(RIGHT_CLOSE);

            if(gamepad2.x && liftState == DEFAULT)wristState = CARRY;
            if(gamepad2.a && liftState == DEFAULT)wristState = GET_LOW;
            if(gamepad1.b)planeState = RELEASE;
            if(gamepad1.y)planeState = HOLD;
            if(liftState == DEFAULT)arm.setWristState(wristState);
            if(gamepad1.x)liftState = HANG_DOWN;
            if(gamepad1.y)liftState = HANG_UP;

            arm.setState(liftState);
            plane.setState(planeState);

            if(opModeIsActive())
                telemetry.addData("time :", arm.timeElapsed.time());
            telemetry.update();
        }
    }
}
