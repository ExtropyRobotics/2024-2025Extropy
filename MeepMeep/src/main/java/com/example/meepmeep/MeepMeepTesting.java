package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class    MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        System.setProperty("sun.java2d.opengl", "true");
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();

        //myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                //.splineToLinearHeading(new Pose2d(-36, -34, Math.toRadians(0)), Math.toRadians(90))
                //.waitSeconds(1.5)
                //.splineToConstantHeading(new Vector2d(46, -33), Math.toRadians(0))
                //.waitSeconds(3)
                //.splineToConstantHeading(new Vector2d(53, -56), Math.toRadians(0))
                //.build());
//        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, 56, Math.toRadians(-90)))
//                .splineToLinearHeading(new Pose2d(-36, 36, Math.toRadians(0)), Math.toRadians(90))
//                .waitSeconds(1.5)
//                .splineToConstantHeading(new Vector2d(44, 36), Math.toRadians(0))
//                .waitSeconds(3)
//                .splineToConstantHeading(new Vector2d(52, 58), Math.toRadians(0))
//                .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}