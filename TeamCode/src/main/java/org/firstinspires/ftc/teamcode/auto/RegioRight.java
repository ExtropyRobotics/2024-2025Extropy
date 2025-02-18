package org.firstinspires.ftc.teamcode.auto;


import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ArmControler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Point;


@Autonomous (name = "! RegioRight")

public class RegioRight extends LinearOpMode {
    ArmControler brat;

    DcMotor axUp = null;
    DcMotor axDown = null;
    DcMotor slider = null;

    Pose2d startingPoseRegioRight = new Pose2d(10, -57, Math.toRadians(90));

    @Override
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        brat = new ArmControler(hardwareMap, telemetry);
//        axUp = hardwareMap.get(DcMotor.class, "axUp");
//        axDown = hardwareMap.get(DcMotor.class, "axDown");
//        slider = hardwareMap.get(DcMotor.class, "brat");
//
//        axUp.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        axUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        axUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        axUp.setPower(0.5);
//        axUp.setTargetPosition(0);
//        axUp.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        axDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        axDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        axDown.setPower(0.5);
//        axDown.setTargetPosition(0);
//        axDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        brat.setClaw(0.5);

        TrajectorySequence regioRight = drive.trajectorySequenceBuilder(startingPoseRegioRight)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    brat.setAxPoz(600);
                    brat.setSliderPoz(500);
                })
                .splineToSplineHeading(new Pose2d(10, -36, Math.toRadians(90)), Math.toRadians(90))
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    brat.setAxPoz(450);
                    brat.setClaw(0.4);
                })
                .waitSeconds(3)
                .UNSTABLE_addTemporalMarkerOffset(-2, ()->{
                    brat.setWristParalel(0.15, 1);
                })
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


        drive.setPoseEstimate(startingPoseRegioRight);

        waitForStart();
        brat.callTelemetry();
        brat.setPower(0.5);
        sleep(500);
        drive.followTrajectorySequence(regioRight);
    }

//    public void setAx(int x,double pow){
//        axUp.setTargetPosition(x);
//        axUp.setPower(pow);
//        axUp.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        axDown.setTargetPosition(x);
//        axDown.setPower(pow);
//        axDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
//
//    public void setSlide(int x,double pow){
//        slider.setTargetPosition(x);
//        slider.setPower(pow);
//        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//    }
}