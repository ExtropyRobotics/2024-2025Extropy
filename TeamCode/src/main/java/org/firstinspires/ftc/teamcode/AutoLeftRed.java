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
        TrajectorySequence test = drive.trajectorySequenceBuilder(startingPose)
                .splineToSplineHeading(new Pose2d(-5, -36, Math.toRadians(90)), Math.toRadians(0))
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-33,-60,Math.toRadians(180)),Math.toRadians(-90))
                .build();

        TrajectorySequence leftRed = drive.trajectorySequenceBuilder(startingPose)
                .splineToSplineHeading(new Pose2d(-5, -36, Math.toRadians(90)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0);
                    motorAx.setTargetPosition(-470);
                    motorAx.setPower(0.5);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setTargetPosition(-5000);
                    sliderMob.setPower(0.7);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    while (sliderMob.getCurrentPosition() > -5000 + 10) sleep(1);

                })
                .waitSeconds(4)
                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
                    motorAx.setTargetPosition(-440);
                    motorAx.setPower(0.5);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.2, ()->{
                    cleste.setPosition(0.09);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.1,()->{
                    sliderMob.setTargetPosition(0);
                    sliderMob.setPower(0.8);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    while (sliderMob.getCurrentPosition() < 10) sleep(1);
                })
                .waitSeconds(5)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-33,-60,Math.toRadians(180)),Math.toRadians(-90))
                .build();


        waitForStart();
        drive.followTrajectorySequence(leftRed);

    }
}
