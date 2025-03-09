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
        Pose2d startingPoseRegioRight = new Pose2d(15, -60, Math.toRadians(90));

        RoadRunnerBotEntity RegioTestLeft = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 13.47)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(8, -26, Math.toRadians(90)))
                        .setTangent(Math.toRadians(43))
                        .splineToSplineHeading(new Pose2d(-8, -33, Math.toRadians(90)), Math.toRadians(43))
                        .waitSeconds(2)
                        .splineToConstantHeading(new Vector2d(-8, -40), Math.toRadians(270))
                        .setTangent(Math.toRadians(190))
                        .splineToConstantHeading(new Vector2d(-53, -39), Math.toRadians(180))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(225))
                        .splineToSplineHeading(new Pose2d(-61, -58, Math.toRadians(225)), Math.toRadians(-100))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-62, -39, Math.toRadians(90)), Math.toRadians(110))
                        .waitSeconds(1.5)
                        .setTangent(Math.toRadians(-45))
                        .splineToSplineHeading(new Pose2d(-61, -58, Math.toRadians(225)), Math.toRadians(180))
                        .waitSeconds(3)
                        .setTangent(Math.toRadians(90))
                        .setVelConstraint(new TranslationalVelocityConstraint(40))
                        .splineToSplineHeading(new Pose2d(-64, 5, Math.toRadians(0)), Math.toRadians(90))
                        .setTangent(Math.toRadians(270))
                        .splineToSplineHeading(new Pose2d(-64, -52, Math.toRadians(80)), Math.toRadians(270))
                        .waitSeconds(1)

                        .build());

        RoadRunnerBotEntity RegioTestRight = new DefaultBotBuilder(meepMeep)
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 13.47)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startingPoseRegioRight)
                        .setVelConstraint(new TranslationalVelocityConstraint(19))
                        .splineToConstantHeading(new Vector2d(5, -33), Math.toRadians(90))
                        .setTangent(Math.toRadians(-90))
                        .splineToSplineHeading(new Pose2d(20, -40, Math.toRadians(-90)), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(30, -40), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(35, -30), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(40, -10), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(47, -15), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(47, -50), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(47, -20), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(55, -10), Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(58, -15), Math.toRadians(-90))
                        .splineToConstantHeading(new Vector2d(58, -55), Math.toRadians(-90))
                        .setTangent(Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(6, -33), Math.toRadians(90))
                        .build());

                        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
//                                .addEntity(RegioTestLeft)
                                .addEntity(RegioTestRight)
                .start();
    }
}