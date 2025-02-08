package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        Pose2d startingPoseLeftRed = new Pose2d(-36, -60,Math.toRadians(90));
        Pose2d startingPoseRightBlue = new Pose2d(10, -57,Math.toRadians(90));

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(startingPoseRightBlue)
                        .splineToSplineHeading(new Pose2d(10, -32, Math.toRadians(90)), Math.toRadians(90))
                        .addTemporalMarkerOffset(-0.3, ()->{
                            // lift rotate arm to pos
                        })
                        .addTemporalMarkerOffset(0, ()->{
                            // lower on bar the arm
                        })
                        .addTemporalMarkerOffset(0.3, ()->{
                            // retract and release
                        })
                        .waitSeconds(1)
                        .addTemporalMarkerOffset(0, ()->{
                            // set rotate to low to above next yellow
                        })
                        .setTangent(Math.toRadians(-10))
                        .splineToSplineHeading(new Pose2d(55, -40, Math.toRadians(120)),Math.toRadians(-10))
                        .addTemporalMarkerOffset(0, ()->{
                            // lower on yellow
                        })
                        .addTemporalMarkerOffset(0.3, ()->{
                            // take yellow
                        })
                        .addTemporalMarkerOffset(0.6, ()->{
                            // rotate arm
                        })
                        .waitSeconds(1)
                        .turn(Math.toRadians(150))
                        .addTemporalMarkerOffset(-0.4, ()->{
                            // lift arm to max
                        })
                        .addTemporalMarkerOffset(0.4 , ()->{
                            // release
                        })
                        .waitSeconds(1)
                        .turn(Math.toRadians(160))
                        .addTemporalMarkerOffset(-0.4, ()->{
                            // lift arm to max
                        })
                        .addTemporalMarkerOffset(0.4 , ()->{
                            // catch
                        })
                        .waitSeconds(1)
                        .turn(Math.toRadians(200))
                        .addTemporalMarkerOffset(-0.4, ()->{
                            // lift arm to max
                        })
                        .addTemporalMarkerOffset(0.4 , ()->{
                            // release
                        })
                        .waitSeconds(1)
                        .turn(Math.toRadians(130))
                        .addTemporalMarkerOffset(-0.4, ()->{
                            // lift arm to max
                        })
                        .addTemporalMarkerOffset(0.4 , ()->{
                            // catch
                        })
                        .waitSeconds(1)
                        .turn(Math.toRadians(230))
                        .addTemporalMarkerOffset(-0.4, ()->{
                            // lift arm to max
                        })
                        .addTemporalMarkerOffset(0.4 , ()->{
                            // release
                        })
                        .waitSeconds(1)
//                        .waitSeconds(1)
//                        .setTangent(Math.toRadians(120))
//                        .splineToSplineHeading(new Pose2d(-60, -40, Math.toRadians(90)), Math.toRadians(120))
//                        .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                            // lower arm over yellow
//                        })
//                        .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                            // close and rotate arm
//                        })
//                        .waitSeconds(1)
//                        .setTangent(Math.toRadians(-30))
//                        .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
//                        .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                            // lift arm to max
//                        })
//                        .UNSTABLE_addTemporalMarkerOffset(0.4 , ()->{
//                            // tip the servo and release
//                        })
//                        .waitSeconds(1)
//                        .setTangent(Math.toRadians(110))
//                        .splineToSplineHeading(new Pose2d(-60,-40,Math.toRadians(120)),Math.toRadians(140))
//                        .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                            // lower arm over yellow
//                        })
//                        .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
//                            // close and rotate arm
//                        })
//                        .waitSeconds(1)
//                        .setTangent(Math.toRadians(-30))
//                        .splineToSplineHeading(new Pose2d(-52,-52,Math.toRadians(215)),Math.toRadians(-90))
//                        .UNSTABLE_addTemporalMarkerOffset(-0.4, ()->{
//                            // lift arm to max
//                        })
//                        .UNSTABLE_addTemporalMarkerOffset(0.4 , ()->{
//                            // tip the servo and release
//                        })
                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}