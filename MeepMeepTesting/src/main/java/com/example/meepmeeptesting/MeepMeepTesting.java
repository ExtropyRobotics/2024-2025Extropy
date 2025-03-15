package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d startingPoseRegioLeft = new Pose2d(-36, -60, Math.toRadians(90));
        Pose2d startingPoseRegioRight = new Pose2d(0, 0, Math.toRadians(-90));

        RoadRunnerBotEntity RegioTestLeft = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 13.47)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(0, 0, Math.toRadians(90)))
                        .setTangent(Math.toRadians(45))
                        .splineToConstantHeading(new Vector2d(20,10),Math.toRadians(135))
                        .splineToConstantHeading(new Vector2d(-20,20),Math.toRadians(150))
                        .splineToConstantHeading(new Vector2d(0,50),Math.toRadians(45))
                        .splineToConstantHeading(new Vector2d(25,-55),Math.toRadians(-150))

                        .build());

//        RoadRunnerBotEntity RegioTestRight = new DefaultBotBuilder(meepMeep)
//                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 13.47)
//                .followTrajectorySequence(drive -> drive.traectorySequenceBuilder(startingPoseRegioRight)
//                        .setTangent(Math.toRadians(135))
//                        .splineToSplineHeading(new Pose2d(-30,20,Math.toRadians(90)),Math.toRadians(135))
//                        .splineToConstantHeading(new Vector2d(40,50),Math.toRadians(90))
//                        .setTangent (Math.toRadians(-70))
//                        .splineToConstantHeading(new Vector2d(50,-30),Math.toRadians(-135))
//                        .splineToConstantHeading(new Vector2d(-50,-45),Math.toRadians(135))
//                        .build());

                        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                                .addEntity(RegioTestLeft)
//                                .addEntity(RegioTestRight)
                .start();
    }
}