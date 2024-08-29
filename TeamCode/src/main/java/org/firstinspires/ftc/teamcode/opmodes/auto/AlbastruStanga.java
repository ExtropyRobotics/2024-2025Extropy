package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.Camera;
import org.firstinspires.ftc.teamcode.camera.CazState;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.modules.Controller.ArmController;
import org.firstinspires.ftc.teamcode.modules.State.HandState;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.modules.State.WristState;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Point;


@Autonomous(name = "!!!!!!!AlbastruStanga", group = "Autonomous")
public class AlbastruStanga extends LinearOpMode{
    Point detection = new Point(0,0);
    Pose2d startingPose = new Pose2d(-35, -59,Math.toRadians(90));
    @Override
    public void runOpMode(){
        ArmController arm = new ArmController(hardwareMap, telemetry, this);
        Camera camera = new Camera(hardwareMap, telemetry, CazState.BLUE);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence cazStanga = drive.trajectorySequenceBuilder(startingPose)
                .splineToSplineHeading(new Pose2d(-35, -25, Math.toRadians(0)), Math.toRadians(90))
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
                    arm.setState(LiftState.PLACE_PURPLE);
                })
                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0.1,()->{
//                    arm.setHandState(HandState.BOTH_CLOSE);
//                })
//                .turn(Math.toRadians(180))
//                .UNSTABLE_addTemporalMarkerOffset(0.3,()->{
//                    arm.setWristState(WristState.CARRY);
//                })
//                .splineToSplineHeading(new Pose2d(-60, -25, Math.toRadians(0)), Math.toRadians(90))
//                .UNSTABLE_addTemporalMarkerOffset(0.7,()->{
//                    arm.setState(LiftState.LOW);
//                })
//                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0.7,()->{
//                    arm.setHandState(HandState.RIGHT_OPEN);
//                })
//                .setTangent(Math.toRadians(70))
//                .splineToSplineHeading(new Pose2d(-61,-12, Math.toRadians(0)),Math.toRadians(180))
//                .UNSTABLE_addTemporalMarkerOffset(-1,() -> {
//                    arm.setState(LiftState.TAKE_WHITE);
//                })
//                .setTangent(0)
//                .splineToSplineHeading(new Pose2d(16, -10, Math.toRadians(0)), Math.toRadians(0))
//                .UNSTABLE_addTemporalMarkerOffset(0,() -> {
//                    arm.setState(LiftState.LOW);
//                })
//                .waitSeconds(5)
//                .UNSTABLE_addTemporalMarkerOffset(0.5,()->{
//                    arm.setArmPos(arm.CONSTANTS.armLOW);
//                })
//                .splineToSplineHeading(new Pose2d(42, -30,Math.toRadians(0)), Math.toRadians(-60))
//                .waitSeconds(1)
//                .setTangent(Math.toRadians(90))
//                .UNSTABLE_addTemporalMarkerOffset(-1,()->{
//                    arm.setArmPos(arm.CONSTANTS.armAVOID);
//                    arm.setWristState(WristState.CARRY);
//                    arm.setHandState(HandState.BOTH_CLOSE);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(-0.5,()->{
//                    arm.setLiftPos(0);
//                })
//                .splineToSplineHeading(new Pose2d(58, -9,Math.toRadians(180)), Math.toRadians(0))
                .build();

        TrajectorySequence cazMijloc = drive.trajectorySequenceBuilder(startingPose)
                .splineToSplineHeading(new Pose2d(-35, -30, Math.toRadians(90)), Math.toRadians(90))
                .turn(Math.toRadians(-180))
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
                    arm.setState(LiftState.PLACE_PURPLE);
                })
                .waitSeconds(5)
                .build();
        TrajectorySequence cazDreapta = drive.trajectorySequenceBuilder(startingPose)
                .splineToSplineHeading(new Pose2d(-33.5, -25, Math.toRadians(180)), Math.toRadians(90))
                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
                    arm.setState(LiftState.PLACE_PURPLE);
                })
                .waitSeconds(5)
                .build();
        drive.setPoseEstimate(startingPose);
        arm.start();
        sleep(500);
        arm.setState(LiftState.DEFAULT);
//        arm.setHandState(HandState.BOTH_CLOSE);

        while(opModeInInit() && !isStopRequested()){
            if(camera.isOK)detection = camera.getDetectionLocation();
            telemetry.addData("location ", detection);
            telemetry.update();
        }
//        arm.setState(LiftState.MANUAL);

        waitForStart();

        if(detection.x == 0 && detection.y == 0)drive.followTrajectorySequence(cazStanga);
      else if(detection.x <= 240) drive.followTrajectorySequence(cazMijloc);
       else if(detection.x > 240) drive.followTrajectorySequence(cazDreapta);

        sleep(500000);
    }
}