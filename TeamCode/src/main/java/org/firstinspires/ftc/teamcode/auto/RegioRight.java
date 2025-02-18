package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.firstinspires.ftc.teamcode.ArmControler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name = "! RegioRight")
public class RegioRight extends LinearOpMode {
    Pose2d startingPoseRegioRight = new Pose2d(10, -57, Math.toRadians(90));
    int targetAx = 0;
    int targetSlider = 0;
    double close = 0.5;
    double open = 0.4;
    double clawPoz = close;
    double wristPlace = 0.1;
    double maxVel = 25;

    public class ArmThread extends Thread{
        ArmControler brat;
        public ArmThread(ArmControler brat){
            this.brat = brat;

            brat.setPower(0.5);
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
                brat.setWristParalel(wristPlace,1);

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
                    targetAx = 400;
                    targetSlider = 900;
                    wristPlace = 0.1;
                })
                .splineToSplineHeading(new Pose2d(10, -28, Math.toRadians(90)), Math.toRadians(90))
                .UNSTABLE_addTemporalMarkerOffset(0.2, ()->{
                    clawPoz = open;
                })
                .UNSTABLE_addTemporalMarkerOffset(1, ()->{
                    targetSlider = 0;
                })
                .UNSTABLE_addTemporalMarkerOffset(1.8, ()->{
                    targetAx = 20;
                    wristPlace = 0.4;
                })
                .waitSeconds(3)
                .setTangent(Math.toRadians(-10))
                .splineToSplineHeading(new Pose2d(34, -38, Math.toRadians(270)), Math.toRadians(30))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel))
                .splineToConstantHeading(new Vector2d(38, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(48, -45), Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel))
                .splineToConstantHeading(new Vector2d(48, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(59, -45), Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel))
                .splineToConstantHeading(new Vector2d(59, -15), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(66, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(66, -45), Math.toRadians(270))
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
        while(!isStopRequested()){

        }
    }
}