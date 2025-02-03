package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@TeleOp(name = "!!TeleOp2023 startPos-centered drive")
public class Posi extends LinearOpMode {

    boolean toggleClaw = false;
    boolean checkClawPress = false;

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor backLeft;

    DcMotor motorAx = null;
    DcMotor slider = null;
    DcMotor liftRight = null;
    DcMotor liftLeft = null;

    Servo handLeft = null;
    Servo handRight = null;
    Servo clawLeft = null;
    Servo clawRight = null;

    int targetPozAx = 0;
    int targetPozSl = 0;
    int targetPozLiftR = 0;
    int targetPozLiftL = 0;


    public BHI260IMU imu;

    @Override
    public void runOpMode() {

        double driveTurn;

        double gamepadXCoordinate;
        double gamepadYCoordinate;
        double gamepadHypot;
        double gamepadDegree;
        double robotDegree;
        double movementDegree;
        double gamepadXControl;
        double gamepadYControl;

        frontLeft = hardwareMap.dcMotor.get("motorStangaFata");
        frontRight = hardwareMap.dcMotor.get("motorDreaptaFata");
        backRight = hardwareMap.dcMotor.get("motorDreaptaSpate");
        backLeft = hardwareMap.dcMotor.get("motorStangaSpate");

        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ImuOrientationOnRobot orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        BHI260IMU.Parameters parameters = new BHI260IMU.Parameters(orientation);

        imu = hardwareMap.get(BHI260IMU.class, "imu");
        imu.initialize(parameters);

        motorAx.setTargetPosition(targetPozAx);
        slider.setTargetPosition(targetPozSl);
        liftRight.setTargetPosition(targetPozLiftR);
        liftLeft.setTargetPosition(targetPozLiftL);

        waitForStart();
        //nigger
        while (opModeIsActive()) {
            driveTurn = gamepad1.right_stick_x;
            // de rezolvat inacuratetea la grade gamepad
            gamepadXCoordinate = -gamepad1.left_stick_x; //this simply gives our x value relative to the driver
            gamepadYCoordinate = gamepad1.left_stick_y; //this simply gives our y vaue relative to the driver

            gamepadHypot = Range.clip(Math.hypot(gamepadXCoordinate, gamepadYCoordinate), 0, 1);

            gamepadDegree = Math.toDegrees(Math.atan2(gamepadYCoordinate, gamepadXCoordinate));
            robotDegree = getAngle();
            movementDegree = gamepadDegree - robotDegree;

            gamepadXControl = Math.cos(Math.toRadians(movementDegree)) * gamepadHypot;
            gamepadYControl = Math.sin(Math.toRadians(movementDegree)) * gamepadHypot;

            frontRight.setPower(gamepadYControl * Math.abs(gamepadYControl) - gamepadXControl * Math.abs(gamepadXControl) + driveTurn);
            backRight.setPower(gamepadYControl * Math.abs(gamepadYControl) + gamepadXControl * Math.abs(gamepadXControl) + driveTurn);
            frontLeft.setPower(gamepadYControl * Math.abs(gamepadYControl) + gamepadXControl * Math.abs(gamepadXControl) - driveTurn);
            backLeft.setPower(gamepadYControl * Math.abs(gamepadYControl) - gamepadXControl * Math.abs(gamepadXControl) - driveTurn);

            telemetry.addData("rogot degrees ", robotDegree);
            telemetry.addData("gamepad degree ", gamepadDegree);
            telemetry.addData("movement degree ", movementDegree);

            //      Claw
            if(gamepad2.a){
                if(checkClawPress) {
                    telemetry.addData("toggling position", 0);
                    telemetry.update();
                    if (toggleClaw) {
                        clawLeft.setPosition(0.7);
                        clawRight.setPosition(0.7);
                    } else {
                        clawLeft.setPosition(1);
                        clawRight.setPosition(1);
                    }
                    toggleClaw = !toggleClaw;
                    checkClawPress = false;
                }
            }else{
                telemetry.addData("released button", 0);
                telemetry.update();
                checkClawPress = true;
            }


        }
    }

    public double getAngle() {
        return imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }
}