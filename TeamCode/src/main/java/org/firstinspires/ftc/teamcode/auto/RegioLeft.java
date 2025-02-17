package org.firstinspires.ftc.teamcode.auto;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name = "RegioLeft")

public class RegioLeft extends LinearOpMode {

    DcMotor axUp = null;
    DcMotor axDown = null;
    DcMotor slider = null;

    Pose2d startingPoseRegioLeft = new Pose2d(-36, -60,Math.toRadians(90));

    @Override
    public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        axUp = hardwareMap.get(DcMotor.class, "axUp");
        axDown = hardwareMap.get(DcMotor.class, "axDown");
        slider = hardwareMap.get(DcMotor.class, "brat");


        axUp.setDirection(DcMotorSimple.Direction.REVERSE);

        axUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        axUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        axUp.setPower(0.5);
        axUp.setTargetPosition(0);
        axUp.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        axDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        axDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        axDown.setPower(0.5);
        axDown.setTargetPosition(0);
        axDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        TrajectorySequence RegioLeft = drive.trajectorySequenceBuilder(startingPoseRegioLeft)

                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    setSlide(-670, 0.3);
                    setRotate(650, 0.3);
                })
                .setTangent(Math.toRadians(43))
                .splineToSplineHeading(new Pose2d(-8, -33, Math.toRadians(90)), Math.toRadians(43))

                .waitSeconds(3)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    setSlide(0, 0.3);
                    setRotate(0, 0.3);
                })
                .setTangent(Math.toRadians(190))
                .splineToSplineHeading(new Pose2d(-48, -40, Math.toRadians(90)), Math.toRadians(180))
                .waitSeconds(1)
                .setTangent(Math.toRadians(225))
                .splineToSplineHeading(new Pose2d(-54, -54, Math.toRadians(225)), Math.toRadians(-100))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-58, -40, Math.toRadians(90)), Math.toRadians(110))
                .waitSeconds(1)
                .setTangent(Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(-56, -52, Math.toRadians(225)), Math.toRadians(180))
                .build();

        drive.setPoseEstimate(startingPoseRegioLeft);
        waitForStart();
        sleep(500);
        drive.followTrajectorySequence(RegioLeft);

        }


    public void setRotate(int x,double pow){
        axUp.setTargetPosition(x);
        axUp.setPower(pow);
        axUp.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        axDown.setTargetPosition(x);
        axDown.setPower(pow);
        axDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setSlide(int x,double pow){
        slider.setTargetPosition(x);
        slider.setPower(pow);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}