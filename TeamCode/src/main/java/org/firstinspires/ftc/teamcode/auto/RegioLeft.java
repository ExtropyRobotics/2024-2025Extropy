package org.firstinspires.ftc.teamcode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ArmControler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name = "RegioLeft")

public class RegioLeft extends LinearOpMode {

    public int targetAx = 0;
    public int targetSlider = 0;
    public double close = 0.5;
    public double open = 0.4;
    public double clawPoz = close;
    public double wristPlace = 0.1;
    public double maxVel = 25;
    public double power = 0.4;

    Pose2d startingPoseRegioLeft = new Pose2d(-36, -60,Math.toRadians(90));

    public class ArmThreadLeft extends Thread{
        ArmControler brat;
        public ArmThreadLeft(ArmControler brat){
            this.brat = brat;

            brat.setPowerSlider(1);
            brat.setPower(power);
            brat.setAxPoz(targetAx);
            brat.setSliderPoz(targetSlider);
            brat.setClaw(close);
            brat.setWrist(wristPlace);
        }
        @Override
        public void run(){
            while(opModeInInit()){
                telemetry.addLine("in init");
                telemetry.update();
            }
            while(opModeIsActive()){
                brat.setAxPoz(targetAx);
                brat.setSliderPoz(targetSlider);
                brat.setClaw(clawPoz);
                brat.setWrist(wristPlace);

                brat.callTelemetry();
                telemetry.update();
            }
        }
    }

    @Override
    public void runOpMode(){
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(),telemetry);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmThreadLeft thread = new ArmThreadLeft(new ArmControler(hardwareMap,telemetry));

        TrajectorySequence RegioLeft = drive.trajectorySequenceBuilder(startingPoseRegioLeft)

                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
                    targetAx = 440;
                    targetSlider = 1600;
                    wristPlace = 0;
                })
                .setTangent(Math.toRadians(43))
                .splineToSplineHeading(new Pose2d(-8, -33, Math.toRadians(90)), Math.toRadians(43))
                .waitSeconds(2)
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    clawPoz = 0.4;
                })
                .splineToConstantHeading(new Vector2d(-8, -40), Math.toRadians(270))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    targetAx = 390;
                    targetSlider = 0;
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    targetSlider = 800;
                    targetAx = 200;
                })
                .setTangent(Math.toRadians(190))
                .splineToConstantHeading(new Vector2d(-53, -40), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    wristPlace = 0.61;
                })
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    power = 0.2;
                    targetAx = 90;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
                    power = 0.4;
                    clawPoz = 0.5;
                    targetSlider = 0;
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()->{
                    targetAx = 820;
                    wristPlace = 0.4;
                })
                .waitSeconds(1.5)
                .setTangent(Math.toRadians(225))
                .splineToSplineHeading(new Pose2d(-61, -58, Math.toRadians(225)), Math.toRadians(-100))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    targetSlider = 2099;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
                    wristPlace = 0.8;
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetAx = 800;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, ()->{
                    clawPoz = 0.4;
                })
                .UNSTABLE_addTemporalMarkerOffset(1.1, ()->{
                    wristPlace = 0.4;
                })
                .UNSTABLE_addTemporalMarkerOffset(1.5, ()->{
                    targetSlider = 0;
                })
                .waitSeconds(1)
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-62, -40, Math.toRadians(90)), Math.toRadians(110))
                .UNSTABLE_addTemporalMarkerOffset(-0.1, ()->{
                    wristPlace = 0.61;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.1, ()->{
                    targetSlider = 800;
                    targetAx = 170;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.3, ()->{
                    targetAx = 90;
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()->{
                    clawPoz = 0.5;
                    targetSlider = 0;
                })
                .UNSTABLE_addTemporalMarkerOffset(1.5, ()->{
                    targetAx = 820;
                    wristPlace = 0.4;
                })
                .waitSeconds(1.5)
                .setTangent(Math.toRadians(-45))
                .splineToSplineHeading(new Pose2d(-61, -58, Math.toRadians(225)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    targetSlider = 2099;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
                    wristPlace = 0.8;
                })
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-0.2, ()->{
                    targetAx = 805;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.8, ()->{
                    clawPoz = 0.4;
                })
                .UNSTABLE_addTemporalMarkerOffset(1.1, ()->{
                    wristPlace = 0.4;
                })
                .UNSTABLE_addTemporalMarkerOffset(1.5, ()->{
                    targetSlider = 0;
                    wristPlace = 0;
                })
                .waitSeconds(3)
                .setTangent(Math.toRadians(90))
                .setVelConstraint(new TranslationalVelocityConstraint(40))
                .splineToSplineHeading(new Pose2d(-64, 5, Math.toRadians(0)), Math.toRadians(90))
                .setTangent(Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(-64, -52, Math.toRadians(80)), Math.toRadians(270))
                .UNSTABLE_addTemporalMarkerOffset(-1.2, ()->{
                    targetAx = 0;
                })
                .waitSeconds(1)
                .build();

        drive.setPoseEstimate(startingPoseRegioLeft);
        thread.start();
        waitForStart();
        sleep(500);
        drive.followTrajectorySequence(RegioLeft);

        }
}