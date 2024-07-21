package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

public class AutoTest extends LinearOpMode{
    @Override
    public void runOpMode(){

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        TrajectorySequence traj = drive.trajectorySequenceBuilder(new Pose2d())
                .splineToSplineHeading(new Pose2d(0,0,Math.toRadians(0)),Math.toRadians(90))
                .build();


        waitForStart();

        drive.followTrajectorySequence(traj);
    }
}