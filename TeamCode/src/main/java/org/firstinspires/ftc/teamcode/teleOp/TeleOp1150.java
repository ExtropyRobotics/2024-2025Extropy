package org.firstinspires.ftc.teamcode.teleOp;

import static org.firstinspires.ftc.teamcode.module.LiftState.*;
import static org.firstinspires.ftc.teamcode.module.HandState.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.module.ArmController;
import org.firstinspires.ftc.teamcode.module.HandController;
import org.firstinspires.ftc.teamcode.module.LiftState;
import org.firstinspires.ftc.teamcode.module.HandState;

import org.firstinspires.ftc.teamcode.drive.MecanumDrive;

@TeleOp(name = "teleOp1150")
public class TeleOp1150 extends LinearOpMode {

    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        LiftState liftState = DEFAULT;
        HandState handState = CARRY;

        MecanumDrive drive = new MecanumDrive(hardwareMap,new Pose2d(0,0,0));
        ArmController arm = new ArmController(hardwareMap, telemetry);
        HandController hand = new HandController(hardwareMap, telemetry);
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

            if(gamepad1.dpad_up) {
                liftState = HIGH;
                handState = CLOSE;
            }
            if(gamepad1.dpad_left) {
                liftState = MID;
                handState = CLOSE;
            }
            if(gamepad1.dpad_down) {
                liftState = LOW;
                handState = CLOSE;
            }
            if(gamepad1.dpad_right)liftState = DEFAULT;

            if(gamepad1.right_bumper)handState = OPEN;
            if(gamepad1.left_bumper)handState = CLOSE;
            if(gamepad1.x && liftState == DEFAULT)handState = CARRY;
            if(gamepad1.a && liftState == DEFAULT)handState = GET;
            if(gamepad1.b && liftState != DEFAULT)handState = PLACE_HIGH;
            if(gamepad1.y && liftState != DEFAULT)handState = PLACE_LOW;

            arm.setState(liftState);
            hand.setState(handState);


            // TODO: For *MANUAL* testing purposes
//            if(gamepad2.left_stick_y>0)liftPos+=1;
//            if(gamepad2.left_stick_y<0)liftPos-=1;
//            if(gamepad2.dpad_up)armPos+=0.001;
//            if(gamepad2.dpad_down)armPos-=0.001;
//            if(gamepad2.dpad_left)wristPos-=0.001;
//            if(gamepad2.dpad_right)wristPos+=0.001;
//            if(gamepad2.left_bumper)handPos+=0.001;
//            if(gamepad2.right_bumper)handPos-=0.001;

//            arm.setWristPos(wristPos);
//            arm.setArmPos(armPos);
//            arm.setHandPos(handPos);
//            arm.setLiftPos(liftPos);

            if(opModeIsActive())
                telemetry.addData("time :", arm.timeElapsed.time());
            telemetry.addData("state: ", liftState);
            telemetry.addData("state: ", handState);
            telemetry.update();
        }
    }
}
