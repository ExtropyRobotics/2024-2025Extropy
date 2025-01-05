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

        leftRed.runAction(leftRed.getDrive().actionBuilder(new Pose2d(5, -57,Math.toRadians(90)))
                        .splineToSplineHeading(new Pose2d(5, -36, Math.toRadians(90)), Math.toRadians(90))
                        .setTangent(Math.toRadians(-30))
                        .splineToSplineHeading(new Pose2d(30,-36,Math.toRadians(90)),Math.toRadians(30))
                        .build());


                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                        .addEntity(leftRed)
                .start();
    }
}



