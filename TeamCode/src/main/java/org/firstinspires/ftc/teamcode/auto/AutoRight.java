package org.firstinspires.ftc.teamcode.auto;


import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Point;


@Autonomous (name = "Right")
@Disabled
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
    Pose2d startingPoseRightBlue = new Pose2d(10, -57, Math.toRadians(90));

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
                .splineToSplineHeading(new Pose2d(10, -36, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(3)
                .setTangent(Math.toRadians(-10))
                .splineToSplineHeading(new Pose2d(34, -38, Math.toRadians(270)), Math.toRadians(30))
                .setVelConstraint(new TranslationalVelocityConstraint(20))
                .splineToConstantHeading(new Vector2d(38, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(48, -45), Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(20))
                .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(59, -45), Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(20))
                .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(66, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(66, -45), Math.toRadians(270))
                .setTangent(Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(48, -47), Math.toRadians(180))
                .setTangent(Math.toRadians(160))
                .splineToSplineHeading(new Pose2d(10, -36, Math.toRadians(90)), Math.toRadians(160))
                .setTangent(Math.toRadians(-30))
                .splineToSplineHeading(new Pose2d(48, -47, Math.toRadians(270)), Math.toRadians(-30))
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