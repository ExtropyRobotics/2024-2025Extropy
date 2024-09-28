package org.firstinspires.ftc.teamcode.opmodes.teleOp;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name ="TeleOp_Activitate_Fotbal")
public class TeleOp_Activitate_Fotbal extends LinearOpMode {

    DcMotor motorStangaF = null;
    DcMotor motorDreaptaF = null;
    DcMotor motorStangaS = null;
    DcMotor motorDreaptaS = null;

    double sticklefty = 0;
    double stickleftx = 0;
    double stickrightx = 0;


    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        motorStangaF = hardwareMap.get(DcMotor.class, "motorStangaFata");
        motorDreaptaF = hardwareMap.get(DcMotor.class, "motorDreaptaFata");
        motorStangaS = hardwareMap.get(DcMotor.class, "motorStangaSpate");
        motorDreaptaS = hardwareMap.get(DcMotor.class, "motorDreaptaSpate");

        motorStangaS.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStangaF.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while (opModeIsActive()) {
            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;

            motorStangaF.setPower(sticklefty + stickleftx + stickrightx);
            motorDreaptaF.setPower(sticklefty - stickleftx - stickrightx);
            motorDreaptaS.setPower(sticklefty + stickleftx - stickrightx);
            motorStangaS.setPower(sticklefty - stickleftx + stickrightx);

        }
    }

}
