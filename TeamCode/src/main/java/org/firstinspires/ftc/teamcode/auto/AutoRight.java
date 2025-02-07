package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Point;


@Autonomous (name = "Right")


public class AutoRight extends LinearOpMode {

//    DcMotor motorAxL = null;
//    DcMotor motorAxR = null;
//    DcMotor sliderMobL = null;
//    DcMotor sliderMobR = null;
//    Servo clesteL = null;
//    Servo clesteR = null;
//    Servo wristR = null;
//    Servo wristL = null;
//
    Point detection = new Point(0,0);
  Pose2d startingPoseLeftRed = new Pose2d(-36, -60,Math.toRadians(90));
   Pose2d startingPoseRightBlue = new Pose2d(5, -57,Math.toRadians(90));

    @Override public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//        sliderMobL = hardwareMap.get(DcMotor.class, "slidermobilL");
//        sliderMobR = hardwareMap.get(DcMotor.class, "slidermobilR");
//
//        motorAxL = hardwareMap.get(DcMotor.class, "motoraxL");
//        motorAxR = hardwareMap.get(DcMotor.class, "motoraxR");
//
//        clesteL = hardwareMap.get(Servo.class, "clesteL");
//        clesteR = hardwareMap.get(Servo.class, "clesteR");
//
//        wristR = hardwareMap.get(Servo.class,"wristR");
//        wristL = hardwareMap.get(Servo.class,"wristL");
//
//        motorAxR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        motorAxL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        sliderMobL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        sliderMobR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//
//        motorAxL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motorAxL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        motorAxR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        motorAxR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        sliderMobL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        sliderMobL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        sliderMobR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        sliderMobR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//
//        clesteL.setPosition(0);
//        clesteR.setPosition(0);
//
//        wristR.setPosition(0);
//        wristL.setPosition(0);
//
        TrajectorySequence rightBlue = drive.trajectorySequenceBuilder(startingPoseRightBlue)
                .splineToSplineHeading(new Pose2d(5, -32, Math.toRadians(90)), Math.toRadians(90))
//                .UNSTABLE_addTemporalMarkerOffset(0,()->{
//                    clesteL.setPosition(0);
//                    setRotate(-470,0.5);
//                    setSlide(-1900,0.7);
//                })
//                .waitSeconds(2)
//                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
//                    setRotate(-400,0.5);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
//                    setSlide(0,0.8);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    clesteL.setPosition(0.6);
//                })
//                .waitSeconds(1)
//                .setTangent(0)
//                .splineToSplineHeading(new Pose2d(45,-35,Math.toRadians(90)),Math.toRadians(30))
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    setSlide(-700, 0.8);
//                    setRotate(-60, 0.8);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
//                    setRotate(-60, 0);
//                })
//                .waitSeconds(1)
////                .setTangent(Math.toRadians(180))
////                .splineToSplineHeading(new Pose2d(4,-35,Math.toRadians(90)),Math.toRadians(180))
////                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0.7, ()->{
//                    setRotate(0, 0);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    clesteL.setPosition(0);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    setRotate(-30, 0.5);
//                    setSlide(-2200, 0.5);
//                })
//                .waitSeconds(0.5)
//                .splineToSplineHeading(new Pose2d(44,-35,Math.toRadians(270)),Math.toRadians(270))
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    clesteL.setPosition(0.6);
//                    setSlide(0, 0.5);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    setRotate(-200, 0.5);
//                })
//                .waitSeconds(0.5)
//                .turn(Math.toRadians(180))
//                .setTangent(0)
//                .splineToSplineHeading(new Pose2d(54,-35,Math.toRadians(90)),Math.toRadians(0))
//                .waitSeconds(1)
//                .setTangent(Math.toRadians(-90))
//                .splineToSplineHeading(new Pose2d(54,-50,Math.toRadians(90)),Math.toRadians(-90))
                .waitSeconds(1)
                .build();


        drive.setPoseEstimate(startingPoseRightBlue);
        waitForStart();
       drive.followTrajectorySequence(rightBlue);

    }

//    public void setRotate(int x,double pow){
//        motorAxL.setTargetPosition(x);
//        motorAxL.setPower(pow);
//        motorAxL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        motorAxR.setTargetPosition(x);
//        motorAxR.setPower(pow);
//        motorAxR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//    public void setSlide(int x,double pow){
//        sliderMobL.setTargetPosition(x);
//        sliderMobL.setPower(pow);
//        sliderMobL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        sliderMobR.setTargetPosition(x);
//        sliderMobR.setPower(pow);
//        sliderMobR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }

}