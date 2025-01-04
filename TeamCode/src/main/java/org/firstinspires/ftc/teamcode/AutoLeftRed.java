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
                .splineToSplineHeading(new Pose2d(-5, -33, Math.toRadians(90)), Math.toRadians(0))

                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0);
                    motorAx.setTargetPosition(-470);
                    motorAx.setPower(0.5);
                    motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    sliderMob.setTargetPosition(-5000);
                    sliderMob.setPower(0.7);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                })
                .waitSeconds(4)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    while (sliderMob.getCurrentPosition() > -5000 + 10) sleep(1);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.1,()->{
                    sliderMob.setTargetPosition(-3180);
                    sliderMob.setPower(-0.1);
                    sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    while (sliderMob.getCurrentPosition() > -3180 + 10) sleep(1);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(0.1, ()->{
                   cleste.setPosition(0.09);
                })

                .build();


        waitForStart();
        drive.followTrajectorySequence(leftRed);

    }

}
