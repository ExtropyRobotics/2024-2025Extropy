package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;
import org.opencv.core.Point;


@Autonomous (name = "AutoLeftRed")


public class AutoLeftRed extends LinearOpMode {

    DcMotor motorAx = null;
    DcMotor sliderMob = null;
    Servo cleste = null;


    Point detection = new Point(0,0);
    Pose2d startingPose = new Pose2d(-36, -60,Math.toRadians(90));

    @Override public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        sliderMob = hardwareMap.get(DcMotor.class, "slidermobil");

        motorAx = hardwareMap.get(DcMotor.class, "motorax");

        cleste = hardwareMap.get(Servo.class, "cleste");

        motorAx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderMob.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        motorAx.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAx.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderMob.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderMob.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        cleste.setPosition(0);

        drive.setPoseEstimate(startingPose);
        TrajectorySequence leftRed = drive.trajectorySequenceBuilder(startingPose)
                .splineToSplineHeading(new Pose2d(-5, -36, Math.toRadians(90)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0);
                    motorAx.setTargetPosition(-470);
                    motorAx.setPower(0.5);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setTargetPosition(-1900);
                    sliderMob.setPower(0.7);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    while (sliderMob.getCurrentPosition() > -1900 + 10) sleep(1);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
                    motorAx.setTargetPosition(-410);
                    motorAx.setPower(0.5);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
                    cleste.setPosition(0.6);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
                    sliderMob.setTargetPosition(0);
                    sliderMob.setPower(0.8);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(1.5)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-50, -40, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-1.3, ()->{
                    motorAx.setTargetPosition(30);
                    sliderMob.setTargetPosition(-1300);
                    motorAx.setPower(0.5);
                    sliderMob.setPower(0.8);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    cleste.setPosition(0.6);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.2, ()->{
                    cleste.setPosition(0);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    motorAx.setTargetPosition(-470);
                    sliderMob.setTargetPosition(-500);
                    motorAx.setPower(0.5);
                    sliderMob.setPower(0.8);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
                    sliderMob.setTargetPosition(-2600);
                    sliderMob.setPower(0.8);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0.6);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    motorAx.setTargetPosition(-470);
                    sliderMob.setTargetPosition(-500);
                    motorAx.setPower(0.5);
                    sliderMob.setPower(0.8);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .setTangent(Math.toRadians(180))
                .turn(Math.toRadians(235))
                .UNSTABLE_addTemporalMarkerOffset(-0.8, ()->{
                    motorAx.setTargetPosition(0);
                    sliderMob.setTargetPosition(-1200);
                    motorAx.setPower(0.5);
                    sliderMob.setPower(0.8);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    cleste.setPosition(0.6);
                })
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-60, -40, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    motorAx.setTargetPosition(30);
                    motorAx.setPower(0.5);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    motorAx.setTargetPosition(-470);
                    sliderMob.setTargetPosition(-500);
                    motorAx.setPower(0.5);
                    sliderMob.setPower(0.8);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
                    sliderMob.setTargetPosition(-2600);
                    sliderMob.setPower(0.8);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0.6);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    motorAx.setTargetPosition(-470);
                    sliderMob.setTargetPosition(-500);
                    motorAx.setPower(0.5);
                    sliderMob.setPower(0.8);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(5)


//                .UNSTABLE_addTemporalMarkerOffset(0.3,()->{
//                    cleste.setPosition(0);
//                }).waitSeconds(1)
//                .setTangent(Math.toRadians(15))
//                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(15))
//                .UNSTABLE_addTemporalMarkerOffset(0.5,()->{
//                    motorAx.setTargetPosition(-500);
//                    motorAx.setPower(0.5);
//                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    sliderMob.setTargetPosition(-5000);
//                    sliderMob.setPower(0.7);
//                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    while (sliderMob.getCurrentPosition() > -5000 + 10) sleep(1);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0.5,()->{
//                  cleste.setPosition(0.09);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.6,()->{
//                    sliderMob.setTargetPosition(0);
//                    sliderMob.setPower(0.8);
//                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    while (sliderMob.getCurrentPosition() < 10) sleep(1);
//                }).waitSeconds(1)
//                .setTangent(Math.toRadians(-170))
//                .splineToSplineHeading(new Pose2d(-58, 36, Math.toRadians(-90)), Math.toRadians(-170))
//                .UNSTABLE_addTemporalMarkerOffset(0.8,()->{
//                    cleste.setPosition(0);
//                })
//                .waitSeconds(0.5)

                .build();


        waitForStart();
        drive.followTrajectorySequence(leftRed);

    }
}
