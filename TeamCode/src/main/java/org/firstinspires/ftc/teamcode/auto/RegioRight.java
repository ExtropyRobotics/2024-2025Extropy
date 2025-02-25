package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.ArmControler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name = "! RegioRight")
public class RegioRight extends LinearOpMode {
    public Pose2d startingPoseRegioRight = new Pose2d(10, -57, Math.toRadians(90));
    public int targetAx = 0;
    public int targetSlider = 0;
    public double close = 0.4;
    public double open = 0;
    public double clawPoz = close;
    public double wristPlace = 0.1;
    public double maxVel = 25;

    public class ArmThreadRight extends Thread{
        ArmControler brat;
        public ArmThreadRight(ArmControler brat){
            this.brat = brat;

            brat.setWrist(wristPlace);
            brat.setPower(0.3);
            brat.setPowerSlider(1);
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
        ArmThreadRight thread = new ArmThreadRight(new ArmControler(hardwareMap,telemetry));

        TrajectorySequence regioRight = drive.trajectorySequenceBuilder(startingPoseRegioRight)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetAx = 440;
                    targetSlider = 1300;
                    wristPlace = 0;
                })
                .waitSeconds(1.5)
                .splineToSplineHeading(new Pose2d(8, -26, Math.toRadians(90)), Math.toRadians(90))
                .waitSeconds(0.6)
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    clawPoz = 0;
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.5, ()->{
                    targetAx = 320;
                    targetSlider = 0;
                })
                .waitSeconds(0.3)
                .setTangent(Math.toRadians(-40))
                .splineToSplineHeading(new Pose2d(34, -38, Math.toRadians(270)), Math.toRadians(30))
                .splineToConstantHeading(new Vector2d(38, -15), Math.toRadians(90))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel))
                .splineToConstantHeading(new Vector2d(53, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(48, -41.5), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(50, -15), Math.toRadians(90))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel))
                .splineToConstantHeading(new Vector2d(66, -15), Math.toRadians(270))
                .resetVelConstraint()
                .splineToConstantHeading(new Vector2d(65, -41.5), Math.toRadians(270))
//                .setVelConstraint(new TranslationalVelocityConstraint(maxVel - 5))
//                .splineToConstantHeading(new Vector2d(59, -13), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(68, -13), Math.toRadians(270))
//                .resetVelConstraint()
//                .splineToConstantHeading(new Vector2d(68, -45), Math.toRadians(270))
                .UNSTABLE_addTemporalMarkerOffset(-3, ()->{
                    targetAx = 125;
                    targetSlider = 200;
                })
                .waitSeconds(0.1)
                .setTangent(Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(47, -49), Math.toRadians(180))
                .waitSeconds(0.2)
                .UNSTABLE_addTemporalMarkerOffset(-0.2, ()->{
                    wristPlace = 0.2;
                })
                .splineToConstantHeading(new Vector2d(47, -51.5), Math.toRadians(270))
                .UNSTABLE_addTemporalMarkerOffset(0.2, ()->{
                    clawPoz = 0.4;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.4, ()->{
                    targetAx = 440;
                    targetSlider = 0;
                    wristPlace = 0;

                })
                .waitSeconds(1)
                .setTangent(Math.toRadians(150))
                .splineToSplineHeading(new Pose2d(12, -49, Math.toRadians(90)), Math.toRadians(180))
                .UNSTABLE_addTemporalMarkerOffset(-1.3, ()->{
                    targetSlider = 1400;
                })
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d(5, -26), Math.toRadians(91))
                .waitSeconds(1)
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    clawPoz = 0;
                })
                .waitSeconds(0.1)
                .splineToConstantHeading(new Vector2d(10, -30), Math.toRadians(270))
                .UNSTABLE_addTemporalMarkerOffset(-1, ()->{
                    targetAx = 400;
                    targetSlider = 0;
                })
                .waitSeconds(0.5)
                .UNSTABLE_addTemporalMarkerOffset(-0.3, ()->{
                    targetSlider = 0;
                })
                .UNSTABLE_addTemporalMarkerOffset(-0.1, ()->{
                    targetAx = 0;
                    wristPlace = 0;
                    clawPoz = 0.4;
                })
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(-30))
                .splineToSplineHeading(new Pose2d(51, -49, Math.toRadians(270)), Math.toRadians(-30))
                .waitSeconds(1)
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