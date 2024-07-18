package org.firstinspires.ftc.teamcode.auto;

import static org.firstinspires.ftc.teamcode.camera.CazState.*;

import androidx.annotation.HalfFloat;
import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.camera.Camera;
import org.firstinspires.ftc.teamcode.drive.MecanumDrive;
import org.opencv.core.Point;

import java.util.Calendar;

@Autonomous(name = "AutoTestDANI", group = "!Test")
public class AutoTest extends LinearOpMode {

    MecanumDrive drive;
    DcMotor randomMotor;

    Point location;
    @Override
    public void runOpMode() throws InterruptedException{

//        randomMotor = hardwareMap.get(DcMotor.class, "randomMotor");
//        randomMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        randomMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


//        Pose2d startingPose = new Pose2d(0,0,0);
//        drive = new MecanumDrive(hardwareMap,startingPose);
//        Action testTraj = drive.actionBuilder(drive.pose)
//                .splineToSplineHeading(new Pose2d(10,10,Math.toRadians(90)),Math.toRadians(0))
//                .afterTime(1, new Action() {
//                    @Override
//                    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//                        int finalPoz = 100;
//
//                        randomMotor.setTargetPosition(finalPoz);
//                        randomMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                        randomMotor.setPower(0.2);
//
//                        telemetry.addData("action after 1 second","");
//                        telemetry.update();
//
//                        while(randomMotor.isBusy())
//                            if(randomMotor.getCurrentPosition()>finalPoz)
//                                randomMotor.setPower(0);
//
//                        return false;
//                    }
//                })
//                .build();

        Camera camera = new Camera(hardwareMap,telemetry,RED);

        while(opModeInInit()){
            location = camera.getDetectionLocation();
            telemetry.addData("location ", location);
            telemetry.update();
        }

        waitForStart();

//        Actions.runBlocking(new SequentialAction(
//                testTraj, // first action to be performed
//                telemetryPacket -> { // second action to be performed
//                    telemetry.addData("sequential action","");
//                    telemetry.update();
//
//                    return false;
//                }
//        ));

//        Actions.runBlocking(new ParallelAction(
//                testTraj,   // actions ran in parallel
//                telemetryPacket -> { // actions ran in parallel
//                    while(opModeIsActive()){
//                        telemetry.addData("parallel action","");
//                        telemetry.addData("pos", drive.pose);
//                        telemetry.update();
//                    }
//                    return false;
//                }
//        ));
    }
}
