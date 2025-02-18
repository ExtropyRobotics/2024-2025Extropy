package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.ArmControler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name = "! RegioRight")
@Config
public class RegioRight extends LinearOpMode {
    public Pose2d startingPoseRegioRight = new Pose2d(10, -57, Math.toRadians(90));
    public int targetAx = 0;
    public int targetSlider = 0;
    public double close = 0.5;
    public double open = 0.4;
    public double clawPoz = close;
    public double wristPlace = 0.1;
    public double maxVel = 25;

    public class ArmThread extends Thread{
        ArmControler brat;
        public ArmThread(ArmControler brat){
            this.brat = brat;

            brat.setWrist(0);
            brat.setPower(1);
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
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmThread thread = new ArmThread(new ArmControler(hardwareMap,telemetry));

        TrajectorySequence regioRight = drive.trajectorySequenceBuilder(startingPoseRegioRight)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetAx = 450;
                    targetSlider = 1400;
                    wristPlace = 0.6;
                })
                .waitSeconds(0.8)
                .splineToSplineHeading(new Pose2d(10, -26, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(2)
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    clawPoz = 0.4;
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.5, ()->{
                    targetAx = 390;
                    targetSlider = 0;
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-10))
                .splineToSplineHeading(new Pose2d(34, -38, Math.toRadians(270)), Math.toRadians(30))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel - 5))
                .splineToConstantHeading(new Vector2d(38, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(48, -45), Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel - 5))
                .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(59, -45), Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel - 5))
                .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(66, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(66, -45), Math.toRadians(270))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    targetAx = 150;
                    targetSlider = 200;
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.1, ()->{
                    clawPoz = 0.5;
                })
                .setTangent(Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(48, -47), Math.toRadians(180))
                .setTangent(Math.toRadians(160))
                .splineToSplineHeading(new Pose2d(10, -36, Math.toRadians(90)), Math.toRadians(160))
                .setTangent(Math.toRadians(-30))
                .splineToSplineHeading(new Pose2d(48, -47, Math.toRadians(270)), Math.toRadians(-30))
                .build();

        drive.setPoseEstimate(startingPoseRegioRight);
        thread.start();
        telemetry.addLine("inside initialization");
        telemetry.addData("state ", thread.getState());
        telemetry.update();

        waitForStart();

        sleep(500);
        drive.followTrajectorySequence(regioRight);
    }
}