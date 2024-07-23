package com.example.meepmeep;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;


import org.jetbrains.annotations.NotNull;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);
        System.setProperty("sun.java2d.opengl", "true");
        RoadRunnerBotEntity caz1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)

                .build();
        RoadRunnerBotEntity caz2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity caz3 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity caz4 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity caz5 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity caz6 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .build();
        RoadRunnerBotEntity caz7 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)

                .build();
        RoadRunnerBotEntity caz8 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)

                .build();

        caz4.runAction(caz1.getDrive().actionBuilder(new Pose2d(11, -56, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(11, -34, Math.toRadians(0)), Math.toRadians(90))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //lasa pixelul mov
                        return false;
                    }
                })
                .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(43, -29), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //pune pixelul galben pe tabla
                        return false;
                    }
                })
                .waitSeconds(1)
                .strafeTo(new Vector2d(43, -12))
                .strafeTo(new Vector2d(-51,-12))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //ia pixelul alb
                        return false;
                    }
                })
                .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(-35, -37), Math.toRadians(0))
                .strafeTo(new Vector2d(45,-37))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //pune pixelul alb pe tabla
                        return false;
                    }
                })
                .waitSeconds(1)
                .strafeTo(new Vector2d(45,-57))
                .build());
        caz5.runAction(caz1.getDrive().actionBuilder(new Pose2d(11, -56, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(34, -33, Math.toRadians(0)), Math.toRadians(90))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //lasa pixelul mov
                        return false;
                    }
                })
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(44,-41))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //pune pixelul galben pe tabla
                        return false;
                    }
                })
                .waitSeconds(1)
                .strafeTo(new Vector2d(44,-12))
                .strafeTo(new Vector2d(-48,-12))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //ia pixel alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .strafeTo(new Vector2d(44,-12))
                .strafeTo(new Vector2d(44,-33))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //pune pixelul alb pe panou
                                return false;
                            }
                        })
                        .waitSeconds(1)
                        .strafeTo(new Vector2d(44,-19))
                .build());

        caz6.runAction(caz1.getDrive().actionBuilder(new Pose2d(11, -56, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(11, -11), Math.toRadians(90))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa pixelul mov
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .turn(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(43, -34), Math.toRadians(0))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa pixelul galben pe panou
                                return false;
                            }
                        })
                        .waitSeconds(1)
                        .strafeTo(new Vector2d(43,-12))
                .strafeTo(new Vector2d(-49,-12))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //ia pixelul alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(-36, -36), Math.toRadians(0))
                .strafeTo(new Vector2d(43,-36))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa pixelul alb
                                return false;
                            }
                        })
                        .waitSeconds(1)
                .strafeTo(new Vector2d(43,-50))
                        .build());

        caz7.runAction(caz1.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(-35, -36, Math.toRadians(0)), Math.toRadians(0))

                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //plasare pixel mov servo1
                        return false;

                    }
                })
                .waitSeconds(0.5)
                        .strafeTo(new Vector2d(-35,-12))
                        .strafeTo(new Vector2d(-50,-12))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //ia pixel alb
                                //coordonate -37,-56
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(11, -12), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(38, -29), Math.toRadians(0))

                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa ambi pixeli pe tabla
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(56, -13), Math.toRadians(-50))
                .build());

        caz8.runAction(caz1.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(-35, -35, Math.toRadians(180)), Math.toRadians(0))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa pixelul mov
                                return false;
                            }
                        })
                        .turn(Math.toRadians(180))
                        .strafeTo(new Vector2d(-50,-35))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //ia pixel alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(-29, -59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(10, -59), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(43, -40), Math.toRadians(0))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa ambi pixeli
                                return false;
                            }
                        })
                        .strafeTo(new Vector2d(43,-50))
                        .build());

        caz1.runAction(caz1.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                .splineToConstantHeading(new Vector2d(-35, -12), Math.toRadians(90))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa pixelul mov
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                        .turn(Math.toRadians(-90))
                        .strafeTo(new Vector2d(-49,-12))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //ia pixel alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d(-23, -58), Math.toRadians(360))
                .splineToConstantHeading(new Vector2d(14, -58), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(43, -37), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //lasa ambi pixeli pe tabla
                        return false;
                    }
                })
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(43,-50))
                        .build());

//-36,-12


//=======
//public class MeepMeepTesting {
//    public static void main(String[] args) {
//        MeepMeep meepMeep = new MeepMeep(800);
//
//        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
//                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
//                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
//                .followTrajectorySequence(drive ->
//                        drive.trajectorySequenceBuilder(new Pose2d(0, 0, 0))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .forward(30)
//                                .turn(Math.toRadians(90))
//                                .build()
//                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
//                .addEntity(caz4)//stanga2
//                .addEntity(caz5)//dreapta2
//                .addEntity(caz6)//mijloc2
                .addEntity(caz7)//stanga1
//                .addEntity(caz8)//dreapta1
//                .addEntity(caz1)//mijloc1

                .start();
    }
}