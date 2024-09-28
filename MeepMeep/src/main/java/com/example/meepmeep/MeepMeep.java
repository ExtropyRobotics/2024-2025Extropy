package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeep{
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(700);

        RoadRunnerBotEntity cazleftred = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        RoadRunnerBotEntity cazrightred = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        RoadRunnerBotEntity cazleftblue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        RoadRunnerBotEntity cazrightblue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
        cazleftred.runAction(cazleftred.getDrive().actionBuilder(new Pose2d(-32, -60,Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(-10, -34, Math.toRadians(90)), Math.toRadians(0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-48, -36, Math.toRadians(-90)), Math.toRadians(-180))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-120))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-45))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-56, -35, Math.toRadians(-90)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-56, -24, Math.toRadians(0)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-56, -24, Math.toRadians(0)), Math.toRadians(90))
                .build());



        cazrightred.runAction(cazleftred.getDrive().actionBuilder(new Pose2d(12, -60,Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(12, -34, Math.toRadians(90)), Math.toRadians(90))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(38, -25, Math.toRadians(180)), Math.toRadians(45))
                        .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(58, -36, Math.toRadians(-90)), Math.toRadians(30))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                        .waitSeconds(1)
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(58, -25, Math.toRadians(180)), Math.toRadians(30))
                        .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                        .waitSeconds(1)
                .build());
        cazleftblue.runAction(cazleftred.getDrive().actionBuilder(new Pose2d(34, 67,Math.toRadians(-90)))
                        .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(9, 32, Math.toRadians(-90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(47, 36, Math.toRadians(90)), Math.toRadians(0))
                        .waitSeconds(0.5)
                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(90))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(58, 34, Math.toRadians(90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(90))
                        .waitSeconds(1)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(58, 25, Math.toRadians(180)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(90))
                        .waitSeconds(1)
                        .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(58, 12, Math.toRadians(180)), Math.toRadians(-90))
                .build());

        cazrightblue.runAction(cazleftred.getDrive().actionBuilder(new Pose2d(-13, 60,Math.toRadians(-90)))
                        .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-8, 31, Math.toRadians(-90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-47, 36, Math.toRadians(270)), Math.toRadians(-90))
                .build());

        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
            //    .addEntity(cazleftred)
            //    .addEntity(cazrightred)
                //.addEntity(cazleftblue)
                .addEntity(cazrightblue)
                .start();
    }
}