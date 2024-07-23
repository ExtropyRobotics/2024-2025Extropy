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
    @Override
    public void runOpMode(){
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        ArmController arm = new ArmController(hardwareMap, telemetry, this);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d(0,0,0))
                .UNSTABLE_addTemporalMarkerOffset(5, ()->{
                    arm.setState(HIGH);
//                    telemetry.addLine("into high");
//                    telemetry.update();
                })
                .UNSTABLE_addTemporalMarkerOffset(10,()->{
                    arm.setState(MID);
//                    telemetry.addLine("into mid");
//                    telemetry.update();
                })
                .UNSTABLE_addTemporalMarkerOffset(15, ()->{
                    arm.setState(DEFAULT);
//                    telemetry.addLine("into low");
//                    telemetry.update();
                })
                .waitSeconds(30)
                .build();

        arm.setState(DEFAULT);
        waitForStart();

        drive.followTrajectorySequence(traj);
    }
}