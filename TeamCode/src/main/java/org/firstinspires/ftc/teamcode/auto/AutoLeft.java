package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Point;


@Autonomous (name = "Left")


public class AutoLeft extends LinearOpMode {

    DcMotor motorAxL = null;
    DcMotor motorAxR = null;
    DcMotor sliderL = null;
    DcMotor sliderR = null;
    Servo clesteL = null;
    Servo clesteR = null;
    Servo wristR = null;
    Servo wristL = null;

    Point detection = new Point(0,0);
    Pose2d startingPoseLeftRed = new Pose2d(-36, -60,Math.toRadians(90));
    Pose2d startingPoseRightBlue = new Pose2d(5, -57,Math.toRadians(90));

    @Override public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        sliderL = hardwareMap.get(DcMotor.class, "sliderLeft");
        sliderR = hardwareMap.get(DcMotor.class, "sliderRight");

        motorAxL = hardwareMap.get(DcMotor.class, "axLeft");
        motorAxR = hardwareMap.get(DcMotor.class, "axRight");

        clesteL = hardwareMap.get(Servo.class, "servoc2");
        clesteR = hardwareMap.get(Servo.class, "servoc3");

        wristR = hardwareMap.get(Servo.class,"servoe0");
        wristL = hardwareMap.get(Servo.class,"servoe1");

        wristR.setDirection(Servo.Direction.REVERSE);
        clesteR.setDirection(Servo.Direction.REVERSE);
        motorAxR.setDirection(DcMotorSimple.Direction.REVERSE);

        motorAxR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorAxL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        sliderL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        motorAxL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sliderL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        wristR.setPosition(0);
        wristL.setPosition(0);

        clesteL.setPosition(1);
        clesteR.setPosition(1);

        TrajectorySequence leftRed = drive.trajectorySequenceBuilder(startingPoseLeftRed)
                .splineToSplineHeading(new Pose2d(-5, -36, Math.toRadians(90)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    clesteL.setPosition(1);
                    clesteR.setPosition(1);
                    setRotate(-470,0.5);
//                    setSlide(-1900,0.7);
                })
                .waitSeconds(2)
                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
                    setRotate(-410,0.5);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
                    clesteL.setPosition(0.87);
                    clesteR.setPosition(0.87);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
//                    setSlide(0,0.8);
                })
                .waitSeconds(1.5)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-50, -40, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-1.3, ()->{
                    setRotate(30,0.3);
//                    setSlide(-1250,0.8);
                    clesteL.setPosition(0.87);
                    clesteR.setPosition(0.87);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.6, ()->{
                    setRotate(30,0);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.2, ()->{
                    clesteL.setPosition(1);
                    clesteR.setPosition(1);
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-460,0.5);
//                    setSlide(-500,0.8);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
//                    setSlide(-2600,0.8);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    clesteL.setPosition(0.87);
                    clesteR.setPosition(0.87);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-460,0.5);
//                    setSlide(-500,0.8);
                })
                .setTangent(Math.toRadians(180))
                .turn(Math.toRadians(235))
                .UNSTABLE_addTemporalMarkerOffset(-0.8, ()->{
                    setRotate(0,0.3);
//                    setSlide(-1200,0.8);
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
                    clesteL.setPosition(1);
                    clesteR.setPosition(1);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-460,0.5);
//                    setSlide(-500,0.8);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
//                    setSlide(-2600,0.8);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0,()->{
                    clesteL.setPosition(0.87);
                    clesteR.setPosition(0.87);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    setRotate(-470,0.5);
//                    setSlide(-500,0.8);
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-30, -10, Math.toRadians(0)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    setSlide(-2000, 0.8);
                })
                .waitSeconds(5)
                .build();


        drive.setPoseEstimate(startingPoseLeftRed);
        waitForStart();
        drive.followTrajectorySequence(leftRed);

    }





    public void setRotate(int x,double pow){
        motorAxL.setTargetPosition(x);
        motorAxL.setPower(pow);
        motorAxL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorAxR.setTargetPosition(x);
        motorAxR.setPower(pow);
        motorAxR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setSlide(int x,double pow){
        sliderL.setTargetPosition(x);
        sliderL.setPower(pow);
        sliderL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        sliderR.setTargetPosition(x);
        sliderR.setPower(pow);
        sliderR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
