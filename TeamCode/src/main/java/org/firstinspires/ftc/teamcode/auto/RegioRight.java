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
    public Pose2d startingPoseRegioRight = new Pose2d(32.7, -60, Math.toRadians(90));
    public int targetAx = 0;
    public int targetSlider = 0;
    public double clawClose = 0.18;
    public double clawOpen = 0;
    public double clawPoz = clawClose;
    public double wristBack = 0;
    public double wristParallel = 0.4;
    public double wristWall = 0.2;
    public double wristStraight = 0.6;
    public double wristPoz = wristBack;
    public double maxVel = 19;
    public double power = 0.4;

    public class ArmThreadRight extends Thread{
        ArmControler brat;
        public ArmThreadRight(ArmControler brat){
            this.brat = brat;

            brat.setPower(0.3);
            brat.setPowerSlider(1);
            brat.setAxPoz(targetAx);
            brat.setSliderPoz(targetSlider);
            brat.setClaw(clawClose);
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
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        ArmThreadRight thread = new ArmThreadRight(new ArmControler(hardwareMap,telemetry));

        TrajectorySequence regioRight = drive.trajectorySequenceBuilder(startingPoseRegioRight)
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetAx = 433;
                    targetSlider = 1400;
                    wristPoz = wristBack;
                })
                .setTangent(Math.toRadians(135))
                .splineToConstantHeading(new Vector2d(10, -33), Math.toRadians(90))
                .waitSeconds(0.05)
                .UNSTABLE_addTemporalMarkerOffset(-0.1, ()->{
                    clawPoz = clawOpen;
                })
                .UNSTABLE_addTemporalMarkerOffset(0, ()->{
                    targetSlider = 200;
                })
                .waitSeconds(200)
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