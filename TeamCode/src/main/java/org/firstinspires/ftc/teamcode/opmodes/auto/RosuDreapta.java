//package org.firstinspires.ftc.teamcode.opmodes.auto;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.camera.Camera;
//import org.firstinspires.ftc.teamcode.camera.CazState;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.modules.Controller.ArmController;
//import org.firstinspires.ftc.teamcode.modules.State.LiftState;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//import org.opencv.core.Point;
//
//
//@Autonomous(name = "!!!!!!!RosuDreapta", group = "Autonomous")
//public class RosuDreapta extends LinearOpMode{
//    Point detection = new Point(0,0);
//    Pose2d startingPose = new Pose2d(-35, -59,Math.toRadians(90));
//
////    Pose2d LEFT_PLACE_PURPLE = new Pose2d(-45, -18, Math.toRadians(90));
////    Pose2d LEFT_TAKE_PIXEL = new Pose2d(-61,-12, Math.toRadians(0));
////    Pose2d LEFT_MIDDLE_POS = new Pose2d(16, -10, Math.toRadians(0));
////    Pose2d LEFT_TABLE_POS = new Pose2d(42, -30,Math.toRadians(0));
////    Pose2d LEFT_PARK_POS = new Pose2d(58, -9,Math.toRadians(180));
////
////
////    Pose2d MIDDLE_PLACE_PURPLE = new Pose2d(-35, -35, Math.toRadians(180));
////    Pose2d MIDDLE_TAKE_PIXEL = new Pose2d(-62, -12, Math.toRadians(0));
////    Pose2d MIDDLE_MIDDLE_POS = new Pose2d(14, -12, Math.toRadians(0));
////    Pose2d MIDDLE_TABLE_POS = new Pose2d(40, -42, Math.toRadians(0));
////    Pose2d MIDDLE_PARK_POS = new Pose2d(50, -12, Math.toRadians(180));
////
////
////    Pose2d RIGHT_PLACE_PURPLE = new Pose2d(-35, -12, Math.toRadians(90));
////    Pose2d RIGHT_TAKE_PIXEL = new Pose2d(-62, -12, Math.toRadians(0));
////    Pose2d RIGHT_MIDDLE_POS = new Pose2d(23,-12, Math.toRadians(0));
////    Pose2d RIGHT_TABLE_POS = new Pose2d(43,-35, Math.toRadians(0));
////    Pose2d RIGHT_PARK_POS = new Pose2d(50, -12, Math.toRadians(180));
//
//    @Override
//    public void runOpMode(){
//        ArmController arm = new ArmController(hardwareMap, telemetry, this);
//        Camera camera = new Camera(hardwareMap, telemetry, CazState.RED);
//
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//        TrajectorySequence cazStanga = drive.trajectorySequenceBuilder(startingPose)
//                .splineToSplineHeading(LEFT_PLACE_PURPLE, Math.toRadians(120))
//                .UNSTABLE_addTemporalMarkerOffset(-0.5,()->{
//                    arm.setState(LiftState.PLACE_PURPLE);
//                })
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(70))
////                .splineToSplineHeading(LEFT_TAKE_PIXEL,Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(-35, -25, Math.toRadians(0)), Math.toRadians(90))
//                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
//                    arm.setState(LiftState.PLACE_PURPLE);
//                })
//                .waitSeconds(2.5)
////                .setTangent(Math.toRadians(70))
////                .splineToSplineHeading(new Pose2d(-61,-12, Math.toRadians(0)),Math.toRadians(180))
////                .UNSTABLE_addTemporalMarkerOffset(-1,() -> {
////                    arm.setState(LiftState.TAKE_WHITE);
////                })
////                .setTangent(0)
////                .splineToSplineHeading(LEFT_MIDDLE_POS, Math.toRadians(0))
////                .UNSTABLE_addTemporalMarkerOffset(0,() -> {
////                    arm.setState(LiftState.LOW);
////                })
//                .waitSeconds(5)
////                .splineToSplineHeading(new Pose2d(16, -10, Math.toRadians(0)), Math.toRadians(0))
////                .UNSTABLE_addTemporalMarkerOffset(0,() -> {
////                    arm.setState(LiftState.LOW);
////                })
////                .waitSeconds(5)
////                .UNSTABLE_addTemporalMarkerOffset(0.5,()->{
////                    arm.setArmPos(arm.CONSTANTS.armLOW);
////                })
////                .splineToSplineHeading(LEFT_TABLE_POS, Math.toRadians(-60))
////                .waitSeconds(1)
////                .setTangent(Math.toRadians(90))
////                .UNSTABLE_addTemporalMarkerOffset(-1,()->{
////                    arm.setArmPos(arm.CONSTANTS.armAVOID);
////                    arm.setWristState(WristState.CARRY);
////                    arm.setHandState(HandState.BOTH_CLOSE);
////                })
////                .UNSTABLE_addTemporalMarkerOffset(-0.5,()->{
////                    arm.setLiftPos(0);
////                })
////                .splineToSplineHeading(LEFT_PARK_POS, Math.toRadians(0))
//                .build();
//
//
//        TrajectorySequence cazMijloc = drive.trajectorySequenceBuilder(startingPose)
//                // cod de mijlocs god knows
//                .setTangent(Math.toRadians(90))
//                .splineToSplineHeading(MIDDLE_PLACE_PURPLE, Math.toRadians(90))
//                .waitSeconds(0.5)//lasa pixelul mov
//                .UNSTABLE_addDisplacementMarkerOffset(1,()->{})
//                .setTangent(Math.toRadians(90))
//                .splineToSplineHeading(MIDDLE_TAKE_PIXEL, Math.toRadians(180))
//                .waitSeconds(0.5)//ia pixel alb
//                .setTangent(Math.toRadians(0))
//                .UNSTABLE_addDisplacementMarkerOffset(1,()->{})
//                .splineToSplineHeading(MIDDLE_MIDDLE_POS,Math.toRadians(0))
//                .splineToSplineHeading(MIDDLE_TABLE_POS,Math.toRadians(-90))
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(90))
//                .splineToSplineHeading(MIDDLE_PARK_POS,Math.toRadians(0))
//
//                .build();
//        TrajectorySequence cazDreapta = drive.trajectorySequenceBuilder(startingPose)
//                // cod de dreapta god knows
//
//                .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
//                //lasa pixelul mov
//                .splineToSplineHeading(RIGHT_PLACE_PURPLE, Math.toRadians(90))
//                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
//                .setTangent(Math.toRadians(180))
//                .splineToSplineHeading(RIGHT_TAKE_PIXEL, Math.toRadians(180))
//                //ia pixelul alb
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(0))
//                .splineToSplineHeading(RIGHT_MIDDLE_POS,Math.toRadians(0))
//                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
//                .splineToSplineHeading(RIGHT_TABLE_POS,Math.toRadians(-90))
//                .waitSeconds(0.5)
//                .setTangent(Math.toRadians(90))
//                .splineToSplineHeading(RIGHT_PARK_POS,Math.toRadians(0))
//                .build();
//
//        TrajectorySequence cazMijloc = drive.trajectorySequenceBuilder(startingPose)
//                .splineToSplineHeading(new Pose2d(-35, -30, Math.toRadians(90)), Math.toRadians(90))
//                .turn(Math.toRadians(-180))
//                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
//                    arm.setState(LiftState.PLACE_PURPLE);
//                })
//                .waitSeconds(5)
//                .build();
//        TrajectorySequence cazDreapta = drive.trajectorySequenceBuilder(startingPose)
//                .splineToSplineHeading(new Pose2d(-35, -25, Math.toRadians(180)), Math.toRadians(90))
//                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
//                    arm.setState(LiftState.PLACE_PURPLE);
//                })
//                .waitSeconds(5)
//                .build();
//>>>>>>> cb3980a34c5a02f0c0d488bb6407de77c8b0a7d0:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/auto/RosuDreapta.java
//        drive.setPoseEstimate(startingPose);
//        arm.start();
//        sleep(500);
//        arm.setState(LiftState.DEFAULT);
//
//        while(opModeInInit() && !isStopRequested()){
//            telemetry.clearAll();
//            if(camera.isOK)detection = camera.getDetectionLocation();
//            if(detection.x == 0 && detection.y == 0)telemetry.addData("caz" , "left");
//            if(detection.x <= 240) telemetry.addData("caz" , "mid");
//            if(detection.x > 240) telemetry.addData("caz" , "right");
//            telemetry.update();
//        }
//
//        waitForStart();
//        if(detection.x == 0 && detection.y == 0)drive.followTrajectorySequence(cazStanga);
//        if(detection.x < 240) drive.followTrajectorySequence(cazMijloc);
//        if(detection.x > 240) drive.followTrajectorySequence(cazDreapta);
//        sleep(500000);
//
//<<<<<<< HEAD:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/auto/AutoTest.java
//=======
//        if(detection.x == 0 && detection.y == 0)drive.followTrajectorySequence(cazStanga);
//        if(detection.x <= 240) drive.followTrajectorySequence(cazMijloc);
//        if(detection.x > 240) drive.followTrajectorySequence(cazDreapta);
//
//        sleep(500000);
//>>>>>>> cb3980a34c5a02f0c0d488bb6407de77c8b0a7d0:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/opmodes/auto/RosuDreapta.java
//    }
//}