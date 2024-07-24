package org.firstinspires.ftc.teamcode.opmodes.auto;

import static org.firstinspires.ftc.teamcode.modules.State.LiftState.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.modules.Controller.ArmController;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous(name = "!!!AutoTestingForThreads", group = "Autonomous")
public class AutoTest extends LinearOpMode{
    Pose2d startingPos = new Pose2d(-35, -59,Math.toRadians(90));
    @Override
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        ArmController arm = new ArmController(hardwareMap, telemetry, this);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence traj = drive.trajectorySequenceBuilder(startingPos)
                .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                .splineToSplineHeading(new Pose2d(-35, -30, Math.toRadians(0)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-50,-8, Math.toRadians(0)),Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                .waitSeconds(0.5)
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(16, -8, Math.toRadians(0)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                .splineToSplineHeading(new Pose2d(42, -30,Math.toRadians(0)), Math.toRadians(-60))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(58, -9,Math.toRadians(180)), Math.toRadians(0))
//                .UNSTABLE_addTemporalMarkerOffset(5, ()->{
//                    arm.setState(HIGH);
////                    telemetry.addLine("into high");
////                    telemetry.update();
//                })
//                .UNSTABLE_addTemporalMarkerOffset(10,()->{
//                    arm.setState(MID);
////                    telemetry.addLine("into mid");
////                    telemetry.update();
//                })
//                .UNSTABLE_addTemporalMarkerOffset(15, ()->{
//                    arm.setState(DEFAULT);
////                    telemetry.addLine("into low");
////                    telemetry.update();
//                })
//                .waitSeconds(30)
                .build();

//        arm.setState(DEFAULT);

        drive.setPoseEstimate(startingPos);
        waitForStart();

        drive.followTrajectorySequence(traj);
    }
}