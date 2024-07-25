package org.firstinspires.ftc.teamcode.opmodes.auto;

import static org.firstinspires.ftc.teamcode.modules.State.LiftState.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.camera.Camera;
import org.firstinspires.ftc.teamcode.camera.CazState;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.modules.Controller.ArmController;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Point;


@Autonomous(name = "!!!AutoTestingForThreads", group = "Autonomous")
public class AutoTest extends LinearOpMode{
    Point detection = new Point(0,0);
    @Override
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        ArmController arm = new ArmController(hardwareMap, telemetry, this);

//        Camera camera = new Camera(hardwareMap,telemetry, CazState.RED);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(-35, -59,Math.toRadians(90)))
                .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                .splineToSplineHeading(new Pose2d(-35, -30, Math.toRadians(0)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-50,-12, Math.toRadians(0)),Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                .waitSeconds(0.5)
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(16, -12, Math.toRadians(0)), Math.toRadians(0))
                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                .splineToSplineHeading(new Pose2d(42, -30,Math.toRadians(0)), Math.toRadians(-60))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(58, -9,Math.toRadians(180)), Math.toRadians(0))
                .waitSeconds(30)
                .build();

        arm.setState(DEFAULT);
        sleep(500);
        while(opModeInInit() && !isStopRequested()){
//            if(camera.isOK)detection = camera.getDetectionLocation();
            telemetry.addData("location ", detection);
            telemetry.update();
        }
        waitForStart();

        drive.followTrajectorySequence(traj);
    }
}