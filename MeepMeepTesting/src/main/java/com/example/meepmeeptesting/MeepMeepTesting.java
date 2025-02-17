package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d startingPoseRegioLeft = new Pose2d(-35.5, -60, Math.toRadians(90));

        RoadRunnerBotEntity RegioTestLeft = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
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
                        .splineToSplineHeading(new Pose2d(-56, -52, Math.toRadians(225)), Math.toRadians(180))


                        .build());


                        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(RegioTestLeft)
                .start();
    }
}