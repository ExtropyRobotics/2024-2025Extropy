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
    DcMotor slider = null;

    Servo claw = null;
    Servo tilt = null;


    Pose2d startingPoseLeftRed = new Pose2d(-36, -60,Math.toRadians(90));
    Pose2d startingPoseRightBlue = new Pose2d(5, -57,Math.toRadians(90));

    @Override public void runOpMode(){
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);


        motorAxL = hardwareMap.get(DcMotor.class, "motorAxLeft");
        motorAxR = hardwareMap.get(DcMotor.class, "motorAxRight");
        slider = hardwareMap.get(DcMotor.class, "slider");
        claw = hardwareMap.get(Servo.class, "claw");
        tilt = hardwareMap.get(Servo.class, "tilt");


        motorAxL.setDirection(DcMotorSimple.Direction.REVERSE);

        motorAxL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxL.setPower(0.5);
        motorAxL.setTargetPosition(0);
        motorAxL.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorAxR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorAxR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorAxR.setPower(0.5);
        motorAxR.setTargetPosition(0);
        motorAxR.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        TrajectorySequence leftRed = drive.trajectorySequenceBuilder(startingPoseLeftRed)
                .setTangent(Math.toRadians(43))
                .splineToSplineHeading(new Pose2d(-8, -36, Math.toRadians(90)), Math.toRadians(43))
                .UNSTABLE_addTemporalMarkerOffset(-0.5 , ()->{
                    // get high with arm
                    setSlide(-550, 0.3);
                    setRotate(250, 0.5);
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0 , ()->{
                    // place on bar
                    setRotate(420, 1);
                })
                .waitSeconds(0.4)
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
                    claw.setPosition(0.25);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.3, ()->{
                    // retract and place sample
//                    wrist.setPosition();
                    setSlide(0, 0.5);
                })
                .waitSeconds(2.5)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    // set rotate to low to above next yellow
                    setRotate(150,0.5);
                    tilt.setPosition(0.55);
                })
                .setTangent(Math.toRadians(190))
                .splineToSplineHeading(new Pose2d(-50, -40, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    setSlide(-800, 0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    // lower on yellow
                    setRotate(30, 0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.3, ()->{
                    // take yellow
                    claw.setPosition(0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.6, ()->{
                    setRotate(200,0.5);
                    setSlide(-400, 0.5);
                })
                .waitSeconds(1)
                .setTangent(Math.toRadians(-100))
                .splineToSplineHeading(new Pose2d(-56,-56,Math.toRadians(215)),Math.toRadians(-100))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    setRotate(520, 0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.7, ()->{
                    setSlide(-1900, 0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, ()->{
                    setRotate(510, 0.5);
                    tilt.setPosition(0.9);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()->{
                    claw.setPosition(0.25);
                })
                .UNSTABLE_addTemporalMarkerOffset(1.2, ()->{
                    tilt.setPosition(0.55);
                })
                .waitSeconds(5)

                .setTangent(Math.toRadians(120))
                .splineToSplineHeading(new Pose2d(-60, -40, Math.toRadians(90)), Math.toRadians(120))
                .UNSTABLE_addTemporalMarkerOffset(-0.5, ()->{
                    setRotate(540,0.5);
                    setSlide(0,0.5);
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()->{
                    setRotate(10,0.5);
                })
                .waitSeconds(2)
//                .UNSTABLE_addTemporalMarkerOffset(1.7, ()->{
//                    tilt.setPosition(0.55);
//                    claw.setPosition(0.45);
//                })
//                .waitSeconds(1.2)
//                .UNSTABLE_addTemporalMarkerOffset(0.2, ()->{
//                    claw.setPosition(0.25);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    tilt.setPosition(0.35);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.2,()->{
//                    claw.setPosition(0.25);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4 , ()->{
//                    // tip the servo and release
////                    wrist.setPosition();
////                    cleste.setPosition();
//                })
//                .waitSeconds(1)
                //                .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                    // lower arm over yellow
////                    setSlide();
////                    setRotate();
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    // close and rotate arm
////                    cleste.setPosition();
////                    setRotate();
//                })
//                .waitSeconds(1)
                .setTangent(Math.toRadians(-30))
                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
//                .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                    // lift arm to max
////                    setSlide();
////                    setRotate();
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4 , ()->{
//                    // tip the servo and release
////                    wrist.setPosition();
////                    cleste.setPosition();
//                })
//                .waitSeconds(1)
//                .setTangent(Math.toRadians(110))
//                .splineToSplineHeading(new Pose2d(-60,-40,Math.toRadians(120)),Math.toRadians(140))
//                .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                    // lower arm over yellow
////                    setSlide();
////                    setRotate();
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    // close and rotate arm
////                    cleste.setPosition();
////                    setRotate();
//                })
//                .waitSeconds(1)
//                .setTangent(Math.toRadians(-30))
//                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
//                .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                    // lift arm to max
////                    setSlide();
////                    setRotate();
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.4 , ()->{
//                    // tip the servo and release
////                    wrist.setPosition();
////                    cleste.setPosition();
//                })
                .waitSeconds(2)




//                .splineToSplineHeading(new Pose2d(-8, -36, Math.toRadians(90)), Math.toRadians(0))
//                .UNSTABLE_addTemporalMarkerOffset(0,()->{
 //                   cleste.setPosition(1);
 //                  setRotate(-470,0.5);
//                    setSlide(-1900,0.7);
//                })
//                .waitSeconds(2)
//                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
//                    setRotate(-410,0.5);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    clesteL.setPosition(0.87);
//                    clesteR.setPosition(0.87);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
////                    setSlide(0,0.8);
//                })
//               .waitSeconds(1.5)
//                .setTangent(Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(-50, -40, Math.toRadians(90)), Math.toRadians(180))
//                .UNSTABLE_addTemporalMarkerOffset(-1.3, ()->{
//                    setRotate(30,0.3);
////                    setSlide(-1250,0.8);
//                    cleste.setPosition(0.87);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(-0.6, ()->{
//                    setRotate(30,0);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(-0.2, ()->{
//                    clesteL.setPosition(1);
//                    clesteR.setPosition(1);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
//                    setRotate(-460,0.5);
////                    setSlide(-500,0.8);
//                })
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(-90))
//                .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
//                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
////                    setSlide(-2600,0.8);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0,()->{
//                    clesteL.setPosition(0.87);
//                    clesteR.setPosition(0.87);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
//                    setRotate(-460,0.5);
////                    setSlide(-500,0.8);
//                })
//                .setTangent(Math.toRadians(180))
//                .turn(Math.toRadians(235))
//                .UNSTABLE_addTemporalMarkerOffset(-0.8, ()->{
//                    setRotate(0,0.3);
////                    setSlide(-1200,0.8);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    setRotate(0, 0);
//                })
//                .setTangent(Math.toRadians(90))
//                .splineToSplineHeading(new Pose2d(-60, -40, Math.toRadians(90)), Math.toRadians(180))
//               .UNSTABLE_addTemporalMarkerOffset(0,()->{
//                    setRotate(30,0);
//                })
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0,()->{
//                    clesteL.setPosition(1);
//                    clesteR.setPosition(1);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
//                    setRotate(-460,0.5);
////                    setSlide(-500,0.8);
//                })
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(-45))
//                .splineToSplineHeading(new Pose2d(-54,-52,Math.toRadians(215)),Math.toRadians(-90))
//                .UNSTABLE_addTemporalMarkerOffset(-0.7,()->{
////                    setSlide(-2600,0.8);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0,()->{
//                    clesteL.setPosition(0.87);
//                    clesteR.setPosition(0.87);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
//                    setRotate(-470,0.5);
////                    setSlide(-500,0.8);
//                })
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(90))
//                .splineToSplineHeading(new Pose2d(-30, -5, Math.toRadians(0)), Math.toRadians(0))
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
////                    setSlide(-2000, 0.8);
//                })
//                .waitSeconds(5)
                .build();


        drive.setPoseEstimate(startingPoseLeftRed);
        tilt.setPosition(0.35);
        claw.setPosition(0.5);
        setRotate(0,0.5);
        setSlide(30,0.5);
        waitForStart();
        sleep(2000);
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
        slider.setTargetPosition(x);
        slider.setPower(pow);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
}
