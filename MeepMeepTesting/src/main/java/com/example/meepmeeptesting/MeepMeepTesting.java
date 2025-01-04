package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        System.setProperty("sun.java2d.opengl", "true");
        RoadRunnerBotEntity leftRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity rightRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity leftBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity rightBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        leftRed.runAction(leftRed.getDrive().actionBuilder(new Pose2d(-36, -60,Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(10, -34, Math.toRadians(90)), Math.toRadians(0))

                .waitSeconds(1.5)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(-48, -34, Math.toRadians(90)), Math.toRadians(-180))
                .waitSeconds(1.5)
                .setTangent(Math.toRadians(-105))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-105))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-56, -35, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-56, -24, Math.toRadians(180)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-56, -24, Math.toRadians(0)), Math.toRadians(90))
                .build());

        rightRed.runAction(rightRed.getDrive().actionBuilder(new Pose2d(12, -60,Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(12, -34, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(47, -34, Math.toRadians(90)), Math.toRadians(0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-170))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-170))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(10))
                .splineToSplineHeading(new Pose2d(58, -36, Math.toRadians(90)), Math.toRadians(10))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-170))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-170))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(15))
                .splineToSplineHeading(new Pose2d(58, -25, Math.toRadians(0)), Math.toRadians(15))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-165))
                .splineToSplineHeading(new Pose2d(-56, -58, Math.toRadians(220)), Math.toRadians(-165))
                .waitSeconds(0.5)
                .build());

        leftBlue.runAction(leftBlue.getDrive().actionBuilder(new Pose2d(36, 61,Math.toRadians(-90)))
                        .setTangent(Math.toRadians(-120))
                .splineToSplineHeading(new Pose2d(9, 36, Math.toRadians(-90)), Math.toRadians(-120))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(47, 36, Math.toRadians(-90)), Math.toRadians(0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(75))
                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(70))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(58, 34, Math.toRadians(-90)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(58, 25, Math.toRadians(0)), Math.toRadians(-90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(90))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(58, 12, Math.toRadians(180)), Math.toRadians(-90))
                .build());

        rightBlue.runAction(rightBlue.getDrive().actionBuilder(new Pose2d(-10, 60,Math.toRadians(-90)))
                        .splineToSplineHeading(new Pose2d(-10, 36, Math.toRadians(-90)), Math.toRadians(-90))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(180))
                        .splineToSplineHeading(new Pose2d(-47, 36, Math.toRadians(-90)), Math.toRadians(-180))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(15))
                        .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(15))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(-170))
                        .splineToSplineHeading(new Pose2d(-58, 36, Math.toRadians(-90)), Math.toRadians(-170))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(15))
                        .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(15))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(-165))
                        .splineToSplineHeading(new Pose2d(-58, 27, Math.toRadians(180)), Math.toRadians(-165))
                        .waitSeconds(0.5)
                        .setTangent(Math.toRadians(17))
                        .splineToSplineHeading(new Pose2d(58, 58, Math.toRadians(45)), Math.toRadians(17))
                        .build());


                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                        .addEntity(leftRed)
//                        .addEntity(rightRed)
//                        .addEntity(leftBlue)
//                        .addEntity(rightBlue)
                .start();
    }
}



