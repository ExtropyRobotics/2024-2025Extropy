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
        Pose2d startingPoseRegioLeft = new Pose2d(-35.5, -60, Math.toRadians(90));
        Pose2d startingPoseRegioRight = new Pose2d(10, -57, Math.toRadians(90));

        RoadRunnerBotEntity RegioTestLeft = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 13.47)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startingPoseRegioLeft)

                        .setTangent(Math.toRadians(43))
                        .splineToSplineHeading(new Pose2d(-8, -36, Math.toRadians(90)), Math.toRadians(43))
                        .waitSeconds(1)
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
                        .splineToSplineHeading(new Pose2d(-56, -52, Math.toRadians(225)), Math.toRadians(270))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-57, -41, Math.toRadians(125)), Math.toRadians(110))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(270))
                        .splineToSplineHeading(new Pose2d(-56, -52, Math.toRadians(225)), Math.toRadians(270))
                        .waitSeconds(25)
                        .waitSeconds(1)

                        .build());

        RoadRunnerBotEntity RegioTestRight = new DefaultBotBuilder(meepMeep)
                .setConstraints(45, 45, Math.toRadians(180), Math.toRadians(180), 13.47)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startingPoseRegioRight)
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(-10))
                        .splineToSplineHeading(new Pose2d(34, -38, Math.toRadians(270)), Math.toRadians(30))
                        .setVelConstraint(new TranslationalVelocityConstraint(20))
                        .splineToConstantHeading(new Vector2d(38, -15), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(270))
                        .resetVelConstraint()
                        .splineToConstantHeading(new Vector2d(48, -45), Math.toRadians(270))
                        .setVelConstraint(new TranslationalVelocityConstraint(20))
                        .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(270))
                        .resetVelConstraint()
                        .splineToConstantHeading(new Vector2d(59, -45), Math.toRadians(270))
                        .setVelConstraint(new TranslationalVelocityConstraint(20))
                        .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(90))
                        .splineToConstantHeading(new Vector2d(66, -15), Math.toRadians(270))
                        .resetVelConstraint()
                        .splineToConstantHeading(new Vector2d(66, -45), Math.toRadians(270))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(180))
                        .splineToConstantHeading(new Vector2d(48, -47), Math.toRadians(180))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(160))
                        .splineToSplineHeading(new Pose2d(10, -26, Math.toRadians(90)), Math.toRadians(160))
                        .waitSeconds(2)
                        .setTangent(Math.toRadians(-30))
                        .splineToConstantHeading(new Vector2d(3, -35), Math.toRadians(270))
                        .waitSeconds(25)
                        .setTangent(Math.toRadians(0))
                        .splineToConstantHeading(new Vector2d(55, -50), Math.toRadians(0))
                        .build());

                        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
//                                .addEntity(RegioTestLeft)
                                .addEntity(RegioTestRight)
                .start();
    }
}