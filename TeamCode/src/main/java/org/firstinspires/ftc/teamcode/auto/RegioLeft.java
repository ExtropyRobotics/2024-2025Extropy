package org.firstinspires.ftc.teamcode.auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.ArmControler;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


@Autonomous (name = "RegioLeft")

public class RegioLeft extends LinearOpMode {

    public int targetAx = 0;
    public int targetSlider = 0;
    public double clawClose = 0.15;
    public double clawOpen = 0;
    public double clawPoz = clawClose;
    public double wristBack = 0.497;
    public double wristParallel = 0.6;
    public double wristWall = 0.5178;
    public double wristPoz = wristBack;
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
            brat.setClaw(clawPoz);
            brat.setWrist(wristPoz);
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
                brat.setWrist(wristPoz);

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

        TrajectorySequence RegioLeft= drive.trajectorySequenceBuilder(startingPoseRegioLeft)

                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetAx = 438;
                    targetSlider = 1400;
                    wristPoz = wristBack;
                })
                .waitSeconds(0.3)
                .splineToConstantHeading(new Vector2d(-10, -33), Math.toRadians(90))
                .waitSeconds(0.05)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    clawPoz = clawOpen;
                })
                .waitSeconds(0.1)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetSlider = 0;
                })
                .setTangent(Math.toRadians(270))
                .setVelConstraint(new TranslationalVelocityConstraint(maxVel))
                .splineToConstantHeading(new Vector2d(-10, -37), Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(-50.5, -40), Math.toRadians(90))
                .resetVelConstraint()
                .UNSTABLE_addTemporalMarkerOffset(0.5, ()->{
                    targetSlider = 100;
                    wristPoz = wristParallel;
                })
                .UNSTABLE_addTemporalMarkerOffset(0.7, ()->{
                    clawPoz = clawClose;
                })
                .waitSeconds(5)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetSlider = 1;
                    targetAx = 0;
                })
                .waitSeconds(20)
                .build();

        drive.setPoseEstimate(startingPoseRegioLeft);
        thread.start();
        waitForStart();
        sleep(500);
        drive.followTrajectorySequence(RegioLeft);

        }
}