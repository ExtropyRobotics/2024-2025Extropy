package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

@TeleOp(name = "__TestMotors", group="_")
public class TestMotors extends LinearOpMode {
    public void runOpMode(){
        telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry(),telemetry);

        DcMotor leftFront = hardwareMap.get(DcMotor.class,"leftFront");
        DcMotor leftBack = hardwareMap.get(DcMotor.class,"leftBack");
        DcMotor rightFront = hardwareMap.get(DcMotor.class,"rightFront");
        DcMotor rightBack = hardwareMap.get(DcMotor.class,"rightBack");

        Servo servo = hardwareMap.get(Servo.class, "servo");

        LynxModule control = hardwareMap.get(LynxModule.class,"Control Hub");

        waitForStart();

        leftFront.setPower(0.5);
        for(int i = 0; i<1000 && !isStopRequested(); i++){
            telemetry.addLine("leftFront");
            telemetry.addData("voltage : ", control.getInputVoltage(VoltageUnit.MILLIVOLTS));
            telemetry.addData("load in amps : ", ((DcMotorEx)leftFront).getCurrent(CurrentUnit.MILLIAMPS));
            telemetry.update();
            sleep(1);
        }
        leftFront.setPower(0);
        sleep(500);

        leftBack.setPower(0.5);
        for(int i = 0; i<1000 && !isStopRequested(); i++){
            telemetry.addLine("leftBack");
            telemetry.addData("voltage : ",control.getInputVoltage(VoltageUnit.MILLIVOLTS));
            telemetry.addData("load in amps : ", ((DcMotorEx)leftBack).getCurrent(CurrentUnit.MILLIAMPS));
            telemetry.update();
            sleep(1);
        }
        leftBack.setPower(0);
        sleep(500);

        rightFront.setPower(0.5);
        for(int i = 0; i<1000 && !isStopRequested(); i++){
            telemetry.addLine("rightFront");
            telemetry.addData("voltage : ",control.getInputVoltage(VoltageUnit.MILLIVOLTS));
            telemetry.addData("load in amps : ", ((DcMotorEx)rightFront).getCurrent(CurrentUnit.MILLIAMPS));
            telemetry.update();
            sleep(1);
        }
        rightFront.setPower(0);
        sleep(500);

        rightBack.setPower(0.5);
        for(int i = 0; i<1000 && !isStopRequested(); i++){
            telemetry.addLine("rightBack");
            telemetry.addData("voltage : ",control.getInputVoltage(VoltageUnit.MILLIVOLTS)); // for getting the current voltage of the control hub);
            telemetry.addData("load in amps : ", ((DcMotorEx)rightBack).getCurrent(CurrentUnit.MILLIAMPS));
            telemetry.update();
            sleep(1);
        }
        rightBack.setPower(0);
        sleep(500);


    }
}
