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


@Autonomous (name = "Left")


public class AutoLeftRed extends LinearOpMode {

    DcMotor motorAx = null;
    DcMotor sliderMob = null;
    Servo cleste = null;


    Point detection = new Point(0,0);
    Pose2d startingPoseLeftRed = new Pose2d(-36, -60,Math.toRadians(90));
    Pose2d startingPoseRightBlue = new Pose2d(5, -57,Math.toRadians(90));

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

        TrajectorySequence leftRed = drive.trajectorySequenceBuilder(startingPoseLeftRed)
                .splineToSplineHeading(new Pose2d(-5, -36, Math.toRadians(90)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0);
                    setRotate(-470,0.5);
                    setSlide(-1900,0.7);
                })
                .waitSeconds(2)
                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
                    setRotate(-410,0.5);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
                    cleste.setPosition(0.6);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
                    setSlide(0,0.8);
                })
                .waitSeconds(1.5)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-50, -40, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-1.3, ()->{
                    setRotate(30,0.3);
                    setSlide(-1250,0.8);
                    cleste.setPosition(0.6);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.6, ()->{
                    setRotate(30,0);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.2, ()->{
                    cleste.setPosition(0);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-460,0.5);
                    setSlide(-500,0.8);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
                    setSlide(-2600,0.8);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0.6);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-460,0.5);
                    setSlide(-500,0.8);
                })
                .setTangent(Math.toRadians(180))
                .turn(Math.toRadians(235))
                .UNSTABLE_addTemporalMarkerOffset(-0.8, ()->{
                    setRotate(0,0.3);
                    setSlide(-1200,0.8);
                })
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    setRotate(0, 0);
                })
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-60, -40, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    setRotate(30,0);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-460,0.5);
                    setSlide(-500,0.8);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
                    setSlide(-2600,0.8);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    cleste.setPosition(0.6);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-470,0.5);
                    setSlide(-500,0.8);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-30, -10, Math.toRadians(0)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    setSlide(-2000, 0.8);
                })
                .waitSeconds(5)
                .build();


        drive.setPoseEstimate(startingPoseLeftRed);
        waitForStart();
        drive.followTrajectorySequence(leftRed);

    }





    public void setRotate(int x,double pow){
        motorAx.setTargetPosition(x);
        motorAx.setPower(pow);
        motorAx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setSlide(int x,double pow){
        sliderMob.setTargetPosition(x);
        sliderMob.setPower(pow);
        sliderMob.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
