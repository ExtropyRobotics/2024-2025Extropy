package org.firstinspires.ftc.teamcode.opmodes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
@TeleOp(name = "TeleOp_Teste_motoare/servo")
public class TeleOp_Motor_Servo extends LinearOpMode {

DcMotor motorTest = null;
Servo servoTest = null;

@Override
    public void runOpMode() {
    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    motorTest = hardwareMap.get(DcMotor.class, "motorTest");
    servoTest = hardwareMap.get(Servo.class, "servoTest");
    waitForStart();

    if (gamepad1.a){
        motorTest.setPower(0.5);
    }
    if (gamepad1.b){
        servoTest.setPosition(1);
    }
    if (gamepad1.y){
        servoTest.setPosition(0);
    }
  }
}
