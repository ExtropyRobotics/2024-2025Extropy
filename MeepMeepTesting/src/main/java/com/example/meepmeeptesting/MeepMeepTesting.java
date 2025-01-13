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
                .splineToSplineHeading(new Pose2d(5, -32, Math.toRadians(90)), Math.toRadians(90))
                //.UNSTABLE_addTemporalMarkerOffset(0,()->{
//                    cleste.setPosition(0);
//                    setRotate(-470,0.5);
//                    setSlide(-1900,0.7);
         //       })
                .waitSeconds(2)
//                .UNSTABLE_addTemporalMarkerOffset(-0.20, ()->{
//                    //setRotate(-400,0.5);
//                })
                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(-0.1,()->{
//                    //setSlide(0,0.8);
//                })
                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    //cleste.setPosition(0.6);
//                })
                .waitSeconds(1)
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(50,-35,Math.toRadians(90)),Math.toRadians(30))
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                  //  setSlide(-700, 0.8);
//                    //setRotate(-60, 0.8);
//                })
//                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
//                    //setRotate(-60, 0);
//                })
                .waitSeconds(1)
                .setTangent(Math.toRadians(180))
                .splineToSplineHeading(new Pose2d(45,-35,Math.toRadians(90)),Math.toRadians(180))
                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    //setRotate(0, 0);
//                })
                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                    //cleste.setPosition(0);
//                })
                .waitSeconds(1)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    //setRotate(-30, 0.5);
//                    //setSlide(-2200, 0.5);
//                })
                .waitSeconds(0.5)
                .splineToSplineHeading(new Pose2d(44,-35,Math.toRadians(270)),Math.toRadians(270))
                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                    //cleste.setPosition(0.6);
//                   // setSlide(0, 0.5);
//                })
                .waitSeconds(0.5)
//                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
//                 //   setRotate(-200, 0.5);
//                })
                .waitSeconds(0.5)
                .turn(Math.toRadians(180))
                .setTangent(0)
                .splineToSplineHeading(new Pose2d(54,-35,Math.toRadians(90)),Math.toRadians(0))
                .waitSeconds(1)
                .setTangent(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(54,-50,Math.toRadians(90)),Math.toRadians(-90))
                .waitSeconds(1)
                .build());


                meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                        .addEntity(leftRed)
                .start();
    }
}



