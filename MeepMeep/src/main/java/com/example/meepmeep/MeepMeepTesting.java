package com.example.meepmeep;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
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

        caz1.runAction(caz1.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(-35, -35, Math.toRadians(0)), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //plasare pixel mov servo1
                        return false;

                    }
                })
                .waitSeconds(0.5)
                .turn(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-14, -8, Math.toRadians(0)), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(10, -8), Math.toRadians(0))
                .afterTime(0., new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //isi ridica bratul pana la prima linie
                        return false;
                    }
                })
                .splineToConstantHeading(new Vector2d(44, -30), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //isi invarte incheietura si lasa pixelul galben pe panou
                        return false;
                    }
                })
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(44, -12))
                .strafeToConstantHeading(new Vector2d(-50, -12))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //isi invarte incheietura si ia un pixel alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                .strafeTo(new Vector2d(44, -12))
                .strafeTo(new Vector2d(43, -41))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //lasa pixelul alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                        .strafeTo(new Vector2d(44,-13))
                .build());


        caz3.runAction(caz3.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(-35, -8, Math.toRadians(90)), Math.toRadians(90))
                .afterTime(0.5, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //lasare pixel mov servo1
                        return false;

                    }
                })
                .waitSeconds(2)
                .turn(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-14, -8, Math.toRadians(0)), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(10, -8), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //isi ridica mana
                        return false;
                    }
                })
                .splineToConstantHeading(new Vector2d(44, -34), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //isi roteste incheietura si lasa pixelul galben
                        return false;
                    }
                })
                .waitSeconds(1.5)
                .strafeTo(new Vector2d(44 , -12))
                .strafeTo(new Vector2d(-47,-12))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //ia pixelul alb
                        return false;
                            }
                        })
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(44 , -12))
                .strafeTo(new Vector2d(44,-44))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //lasa pixelul alb
                        return false;
                    }
                })
                .waitSeconds(0.5)
                .strafeTo(new Vector2d(44,-60))
                .build());

        caz2.runAction(caz2.getDrive().actionBuilder(new Pose2d(-35, -58, Math.toRadians(90)))
                .splineToLinearHeading(new Pose2d(-35, -35, Math.toRadians(180)), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //lasare pixel mov servo1
                        return false;

                    }
                })
                .waitSeconds(1.5)
                .turn(Math.toRadians(-90))
                .splineToSplineHeading(new Pose2d(-14, -8, Math.toRadians(0)), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(10, -8), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //isi ridica mana
                        return false;
                    }
                })
                .splineToConstantHeading(new Vector2d(44, -42), Math.toRadians(0))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //isi roteste incheietura si lasa pixelul galben
                        return false;
                    }
                })
                .waitSeconds(1.5)
                .strafeToConstantHeading(new Vector2d(44 , -12))
                .strafeToConstantHeading(new Vector2d(-50 , -12))
                .afterTime(0, new Action() {
                    @Override
                    public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                        //ia pixelul alb
                        return false;
                    }
                })
                        .waitSeconds(0.5)
                .strafeToConstantHeading(new Vector2d(44 , -12))
                        .strafeTo(new Vector2d(44,-27))
                        .afterTime(0, new Action() {
                            @Override
                            public boolean run(@NotNull TelemetryPacket telemetryPacket) {
                                //pune pixelul alb
                                return false;
                            }
                        })
                        .waitSeconds(0.5)
                        .strafeTo(new Vector2d(44,-20))
                .build());








        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(caz1) // stanga
                .addEntity(caz2)// dreapta
                .addEntity(caz3)// mijloc
                .start();
    }
}