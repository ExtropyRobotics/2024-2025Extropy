package com.example.meepmeep;

import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

    public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity cazRL1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, -59,Math.toRadians(90)))
                                .splineToSplineHeading(new Pose2d(-43, -25, Math.toRadians(90)), Math.toRadians(120))
                                .UNSTABLE_addTemporalMarkerOffset(-0.5,()->{
//                                    arm.setState(LiftState.PLACE_PURPLE);
                                })
                                .waitSeconds(0.5)
                                .setTangent(Math.toRadians(70))
                                .splineToSplineHeading(new Pose2d(-61,-12, Math.toRadians(0)),Math.toRadians(180))
                                .UNSTABLE_addTemporalMarkerOffset(-1,() -> {
//                                    arm.setState(LiftState.TAKE_WHITE);
                                })
                                .setTangent(0)
                                .splineToSplineHeading(new Pose2d(16, -10, Math.toRadians(0)), Math.toRadians(0))
                                .UNSTABLE_addTemporalMarkerOffset(0,() -> {
//                                    arm.setState(LiftState.LOW);
                                })
                                .waitSeconds(5)
                                .UNSTABLE_addTemporalMarkerOffset(0.5,()->{
//                                    arm.setArmPos(arm.CONSTANTS.armLOW);
                                })
                                .splineToSplineHeading(new Pose2d(42, -30,Math.toRadians(0)), Math.toRadians(-60))
                                .waitSeconds(1)
                                .setTangent(Math.toRadians(90))
                                .UNSTABLE_addTemporalMarkerOffset(-1,()->{
//                                    arm.setArmPos(arm.CONSTANTS.armAVOID);
//                                    arm.setWristState(WristState.CARRY);
//                                    arm.setHandState(HandState.BOTH_CLOSE);
                                })
                                .UNSTABLE_addTemporalMarkerOffset(-0.5,()->{
//                                    arm.setLiftPos(0);
                                })
                                .splineToSplineHeading(new Pose2d(58, -9,Math.toRadians(180)), Math.toRadians(0))

                                .build());

        RoadRunnerBotEntity cazRR1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                               drive.trajectorySequenceBuilder(new Pose2d(-35, -59,Math.toRadians(90)))
                                       .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                       .setTangent(Math.toRadians(90))
                                       .splineToSplineHeading(new Pose2d(-35, -35, Math.toRadians(180)), Math.toRadians(90))
                                       .waitSeconds(0.5)//lasa pixelul mov
                                       .UNSTABLE_addDisplacementMarkerOffset(1,()->{})
                                       .setTangent(Math.toRadians(90))
                                       .splineToSplineHeading(new Pose2d(-62, -12, Math.toRadians(0)), Math.toRadians(180))
                                       .waitSeconds(0.5)//ia pixel alb
                                       .setTangent(Math.toRadians(0))
                                       .UNSTABLE_addDisplacementMarkerOffset(1,()->{})
                                       .splineToSplineHeading(new Pose2d(14, -12, Math.toRadians(0)),Math.toRadians(0))
                                       .splineToSplineHeading(new Pose2d(40, -42, Math.toRadians(0)),Math.toRadians(-90))
                                       .waitSeconds(0.5)
                                       .setTangent(Math.toRadians(90))
                                       .splineToSplineHeading(new Pose2d(50, -12, Math.toRadians(180)),Math.toRadians(0))
                                       .build());

        RoadRunnerBotEntity cazRM1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(-35, -60,Math.toRadians(90)))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        //lasa pixelul mov
                                        .splineToSplineHeading(new Pose2d(-35, -12, Math.toRadians(90)), Math.toRadians(90))
                                        .waitSeconds(0.5)
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .setTangent(Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-62, -12, Math.toRadians(0)), Math.toRadians(180))
//                                        //ia pixelul alb
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(23,-12, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(43,-35, Math.toRadians(0)),Math.toRadians(-90))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(50, -12, Math.toRadians(180)),Math.toRadians(0))
                                        .build());

        RoadRunnerBotEntity cazRL2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, -60,Math.toRadians(90)))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
//                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(12, -35, Math.toRadians(0)), Math.toRadians(90))
                                        .waitSeconds(0.5)//lasa pixel mov
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .setTangent(Math.toRadians(15))
                                        .splineToSplineHeading(new Pose2d(42, -28, Math.toRadians(0)),Math.toRadians(15))
                                        .waitSeconds(0.5)//lasa pixel galben
                                        .setTangent(Math.toRadians(-120))
                                        .splineToSplineHeading(new Pose2d(12.5, -57, Math.toRadians(0)),Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-32, -57, Math.toRadians(0)),Math.toRadians(180))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(-62, -35, Math.toRadians(0)),Math.toRadians(120))
                                        .waitSeconds(0.5)//ia pixel alb
                                        .setTangent(Math.toRadians(-60))
                                        .splineToSplineHeading(new Pose2d(-32, -57, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .splineToSplineHeading(new Pose2d(12.5, -57, Math.toRadians(0)),Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(42, -28, Math.toRadians(0)),Math.toRadians(60))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(42, -57, Math.toRadians(180)),Math.toRadians(-90))
                                        .build());

        RoadRunnerBotEntity cazRR2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, -60,Math.toRadians(90)))
                                        .splineToSplineHeading(new Pose2d(12, -35, Math.toRadians(180)), Math.toRadians(90))
//                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
//                                        .splineToSplineHeading(new Pose2d(42, -42, Math.toRadians(0)), Math.toRadians(-90))
//                                        .waitSeconds(0.5)
//                                        .setTangent(Math.toRadians(-90))
//                                        .splineToSplineHeading(new Pose2d(12.5, -57, Math.toRadians(0)),Math.toRadians(180))
//                                        .splineToSplineHeading(new Pose2d(-32, -57, Math.toRadians(0)),Math.toRadians(180))
//                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
//                                        .splineToSplineHeading(new Pose2d(-62, -35, Math.toRadians(0)),Math.toRadians(90))
//                                        .waitSeconds(0.5)//ia pixel alb
//                                        .setTangent(Math.toRadians(-90))
//                                        .splineToSplineHeading(new Pose2d(-32, -57, Math.toRadians(0)),Math.toRadians(0))
//                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
//                                        .splineToSplineHeading(new Pose2d(12.5, -57, Math.toRadians(0)),Math.toRadians(0))
//                                        .splineToSplineHeading(new Pose2d(42, -28, Math.toRadians(0)),Math.toRadians(90))
//                                        .waitSeconds(0.5)
//                                        .setTangent(Math.toRadians(-90))
//                                        .splineToSplineHeading(new Pose2d(42, -57, Math.toRadians(180)),Math.toRadians(-90))
                                                .build());
        RoadRunnerBotEntity cazRM2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, -60,Math.toRadians(90)))
                                        .splineToSplineHeading(new Pose2d(12, -25, Math.toRadians(0)),Math.toRadians(90))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(42, -35, Math.toRadians(0)), Math.toRadians(-45))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(12.5, -57, Math.toRadians(0)),Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-32, -57, Math.toRadians(0)),Math.toRadians(180))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(-62, -35, Math.toRadians(0)),Math.toRadians(90))
                                        .waitSeconds(0.5)//ia pixel alb
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(-32, -57, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .splineToSplineHeading(new Pose2d(12.5, -57, Math.toRadians(0)),Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(42, -28, Math.toRadians(0)),Math.toRadians(90))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(42, -57, Math.toRadians(180)),Math.toRadians(90))
                                        .build());
        RoadRunnerBotEntity caz7 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-35, 59,Math.toRadians(-90)))
                                .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                .setTangent(Math.toRadians(-110))
                                .splineToSplineHeading(new Pose2d(-46, 12, Math.toRadians(-90)), Math.toRadians(-90))
                                .waitSeconds(0.5)
                                .turn(Math.toRadians(90))
                                .setReversed(true)
                                .splineToSplineHeading(new Pose2d(-61,12, Math.toRadians(0)),Math.toRadians(-180))
                                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                .waitSeconds(0.5)
                                .setTangent(0)
                                .splineToSplineHeading(new Pose2d(18, 12, Math.toRadians(0)), Math.toRadians(0))
                                .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                .splineToSplineHeading(new Pose2d(42, 30,Math.toRadians(0)), Math.toRadians(0))
                                .waitSeconds(1)
                                .setTangent(Math.toRadians(-90))
                                .splineToSplineHeading(new Pose2d(58, 9,Math.toRadians(180)), Math.toRadians(0))
                                .build ());

        RoadRunnerBotEntity caz8 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(-35, 59,Math.toRadians(-90)))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .splineToSplineHeading(new Pose2d(-35, 30, Math.toRadians(0)), Math.toRadians(-90))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(-61,12, Math.toRadians(0)),Math.toRadians(-180))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .waitSeconds(0.5)
                                        .setTangent(0)
                                        .splineToSplineHeading(new Pose2d(18, 12, Math.toRadians(0)), Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(42, 30,Math.toRadians(0)), Math.toRadians(0))
                                        .waitSeconds(1)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(58, 9,Math.toRadians(180)), Math.toRadians(0))
                                        .build ());
        RoadRunnerBotEntity caz9 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(-35, 59,Math.toRadians(-90)))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        //lasa pixelul mov
                                        .splineToSplineHeading(new Pose2d(-35, 12, Math.toRadians(-90)), Math.toRadians(-90))
                                        .waitSeconds(0.5)
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .setTangent(Math.toRadians(-180))
                                        .splineToSplineHeading(new Pose2d(-62, 12, Math.toRadians(0)), Math.toRadians(-180))
//                                        //ia pixelul alb
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(23,12, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(43,35, Math.toRadians(0)),Math.toRadians(30))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(50, 12, Math.toRadians(180)),Math.toRadians(0))
                                        .build());

        RoadRunnerBotEntity caz10 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, 60,Math.toRadians(-90)))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .setTangent(Math.toRadians(-90))
                                        .splineToSplineHeading(new Pose2d(12, 35, Math.toRadians(0)), Math.toRadians(-90))
                                        .waitSeconds(0.5)//lasa pixel mov
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .setTangent(Math.toRadians(-15))
                                        .splineToSplineHeading(new Pose2d(42, 28, Math.toRadians(0)),Math.toRadians(-15))
                                        .waitSeconds(0.5)//lasa pixel galben
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(12.5, 57, Math.toRadians(0)),Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-32, 57, Math.toRadians(0)),Math.toRadians(180))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(-61, 35, Math.toRadians(0)),Math.toRadians(-90))
                                        .waitSeconds(0.5)//ia pixel alb
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(-32, 57, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .splineToSplineHeading(new Pose2d(12.5, 57, Math.toRadians(0)),Math.toRadians(0))
                                        .waitSeconds(0.5)
                                        .setTangent(Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(42, 28, Math.toRadians(0)),Math.toRadians(-90))
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(42, 57, Math.toRadians(180)),Math.toRadians(90))
                                        .build());
        RoadRunnerBotEntity caz11 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, 60,Math.toRadians(-90)))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(46, 30, Math.toRadians(0)), Math.toRadians(0))
                                        .waitSeconds(0.5)//lasa pixelul mov
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .waitSeconds(0.5)//lasa pixel galben
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(12.5, 57, Math.toRadians(0)),Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-32, 57, Math.toRadians(0)),Math.toRadians(180))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(-61, 35, Math.toRadians(0)),Math.toRadians(-90))
                                        .waitSeconds(0.5)//ia pixel alb
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(-32, 57, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .splineToSplineHeading(new Pose2d(12.5, 57, Math.toRadians(0)),Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(46, 28, Math.toRadians(0)),Math.toRadians(-90))
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(42, 57, Math.toRadians(180)),Math.toRadians(90))
                                        .build());


        RoadRunnerBotEntity caz12 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(12, 60,Math.toRadians(-90)))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(12, 10, Math.toRadians(-90)),Math.toRadians(-90))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .waitSeconds(0.5)
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .setTangent(Math.toRadians(35))
                                        .splineToSplineHeading(new Pose2d(42, 28, Math.toRadians(0)),Math.toRadians(30))
                                        .waitSeconds(0.5)//lasa pixel galben
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(12.5, 57, Math.toRadians(0)),Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-32, 57, Math.toRadians(0)),Math.toRadians(180))
                                        .UNSTABLE_addTemporalMarkerOffset(0,() -> {})
                                        .splineToSplineHeading(new Pose2d(-61, 35, Math.toRadians(0)),Math.toRadians(-90))
                                        .waitSeconds(0.5)//ia pixel alb
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(-32, 57, Math.toRadians(0)),Math.toRadians(0))
                                        .UNSTABLE_addTemporalMarkerOffset(1,() -> {})
                                        .splineToSplineHeading(new Pose2d(12.5, 57, Math.toRadians(0)),Math.toRadians(0))
                                        .splineToSplineHeading(new Pose2d(46, 28, Math.toRadians(0)),Math.toRadians(-90))
                                        .setTangent(Math.toRadians(90))
                                        .splineToSplineHeading(new Pose2d(42, 57, Math.toRadians(180)),Math.toRadians(90))

                                        .build());
        RoadRunnerBotEntity caztest = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
        drive.trajectorySequenceBuilder(new Pose2d(-35, -59,Math.toRadians(90)))
                .splineToSplineHeading(new Pose2d(-35, -30, Math.toRadians(90)), Math.toRadians(90))
                .turn(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-58, -30, Math.toRadians(180)), Math.toRadians(0))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
 //               .addEntity(cazRL1)//stanga1 rosu
//                .addEntity(cazRR1)//dreapta1 rosu
//                .addEntity(cazRM1)//mijloc1 rosu
//                .addEntity(cazRL2)//stanga2 rosu
//                .addEntity(cazRR2)//dreapta2 rosu
//                .addEntity(cazRM2)//mijloc2 rosu
//                .addEntity(caz7)//dreapta1 albastru
//                .addEntity(caz8)//stanga1 albastru
//                .addEntity(caz9)//mijloc1 albastru
//                .addEntity(caz10)//stanga2 albastru
//                .addEntity(caz11)//dreapta2 albastru
//                .addEntity(caz12)//mijloc2 albastru
                .addEntity(caztest)

                .start();
    }
}