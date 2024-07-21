package org.firstinspires.ftc.teamcode.teleOp;

import static org.firstinspires.ftc.teamcode.modules.State.HandState.*;
import static org.firstinspires.ftc.teamcode.modules.State.LiftState.*;
import static org.firstinspires.ftc.teamcode.modules.State.WristState.*;
import static org.firstinspires.ftc.teamcode.modules.State.PlaneState.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.Controller.ArmController;
import org.firstinspires.ftc.teamcode.modules.Controller.HandController;
import org.firstinspires.ftc.teamcode.modules.State.HandState;
import org.firstinspires.ftc.teamcode.modules.Controller.WristController;
import org.firstinspires.ftc.teamcode.modules.Controller.LedController;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.modules.State.WristState;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.modules.Controller.PlaneController;
import org.firstinspires.ftc.teamcode.modules.State.PlaneState;

@TeleOp(name = "!!!!DejTeleOp", group = "!")
public class DejTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        LiftState liftState = DEFAULT;
        WristState wristState = CARRY;
        HandState handState = CLOSE;
        PlaneState planeState = HOLD;

        MecanumDrive drive = new MecanumDrive(hardwareMap,new Pose2d(0,0,0));
        HandController hand = new HandController(hardwareMap, telemetry);
        ArmController arm = new ArmController(hardwareMap, telemetry, wristState);
        PlaneController plane = new PlaneController(hardwareMap, telemetry);
        LedController led = new LedController(hardwareMap, telemetry);

//        int liftPos = 0;
//        double wristPos = 0;
//        double armPos = 0.08;
//        double handPos = 0;

        waitForStart();
        while(opModeIsActive()){
            drive.setDrivePowers(new PoseVelocity2d(
                    new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ),
                    -gamepad1.right_stick_x
            ));

            if(gamepad1.dpad_up)liftState = HIGH;
            if(gamepad1.dpad_left)liftState = MID;
            if(gamepad1.dpad_down)liftState = LOW;
            if(gamepad1.dpad_right)liftState = DEFAULT;

            if(gamepad1.right_bumper)handState = OPEN;
            if(gamepad1.left_bumper)handState = CLOSE;
            if(gamepad1.x && liftState == DEFAULT)wristState = CARRY;
            if(gamepad1.a && liftState == DEFAULT)wristState = GET;
            if(gamepad1.b)planeState = RELEASE;
            if(gamepad1.y)planeState = HOLD;
            if(liftState == DEFAULT)arm.setWristState(wristState);

            arm.setState(liftState);
            hand.setState(handState);
            plane.setState(planeState);
            led.runDetection();


////             TODO: For *MANUAL* testing purposes
//            if(gamepad2.left_stick_y>0)liftPos+=1;
//            if(gamepad2.left_stick_y<0)liftPos-=1;
//            if(gamepad2.dpad_up)armPos+=0.001;
//            if(gamepad2.dpad_down)armPos-=0.001;
//            if(gamepad2.dpad_left)wristPos-=0.001;
//            if(gamepad2.dpad_right)wristPos+=0.001;
//            if(gamepad2.left_bumper)handPos+=0.001;
//            if(gamepad2.right_bumper)handPos-=0.001;
//
//            arm.setWristPos(wristPos);
//            arm.setArmPos(armPos);
//            arm.setHandPos(handPos);
//            arm.setLiftPos(liftPos);

            if(opModeIsActive())
                telemetry.addData("time :", arm.timeElapsed.time());
            telemetry.update();
        }
    }
}
