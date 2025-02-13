package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

public class ArmControler {
    Telemetry telemetry;

    private DcMotor slider;
    private DcMotor axDown;
    private DcMotor axUp;
    private Servo wrist;
    private Servo claw;

    private double pow = 0;
    private int axPoz = 0;
    private int sliderPoz = 0;

    private int axDirection = 0;
    private int sliderDirection = 0;

    private int maxAmps = 2000;
    private int errorMargin = 10;

    public ArmControler(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;

        slider = hardwareMap.get(DcMotor.class, "brat");
        axDown = hardwareMap.get(DcMotor.class, "axDown");
        axUp = hardwareMap.get(DcMotor.class, "axUp");
        wrist = hardwareMap.get(Servo.class, "wrist");
        claw = hardwareMap.get(Servo.class ,"claw");

        initializeMotor(axUp, DcMotorSimple.Direction.REVERSE);
        initializeMotor(axDown, DcMotorSimple.Direction.FORWARD);
        initializeMotor(slider, DcMotorSimple.Direction.REVERSE);
    }
    public void setPower(double pow){
        this.pow = pow;
        axUp.setPower(pow);
        axDown.setPower(pow);
        slider.setPower(pow);
    }
    public int setAxPoz (int targetPozAx){
        if(targetPozAx - axPoz > 5) axDirection = 1;
        if(targetPozAx - axPoz < -5) axDirection = -1;

//        if(axPoz > axUp.getCurrentPosition()-(sliderPoz > 300 ? errorMargin/5 : errorMargin) && axPoz < axUp.getCurrentPosition()+(sliderPoz > 300 ? errorMargin/5 : errorMargin)){
//            axUp.setPower(0);
//            axDown.setPower(0);
//        }
//        else {
//            axUp.setPower(pow);
//            axDown.setPower(pow);
//        }

        axPoz = targetPozAx;
        axUp.setTargetPosition(axPoz);
        axDown.setTargetPosition(axPoz);

        return axDirection;
    }
    public int setSliderPoz (int targetPozSlider){
        if(targetPozSlider - slider.getCurrentPosition() > 5) sliderDirection = 1;
        if(targetPozSlider - slider.getCurrentPosition() < -5) sliderDirection = -1;

        if(sliderPoz > slider.getCurrentPosition()-errorMargin && sliderPoz < slider.getCurrentPosition()+errorMargin)
            slider.setPower(0);
        else slider.setPower(pow);

        sliderPoz = targetPozSlider;
        slider.setTargetPosition(sliderPoz);

        return sliderDirection;
    }
    public void setWrist(double pos){
        wrist.setPosition(pos);
    }
    public void setClaw(double pos){
        claw.setPosition(pos);
    }
    public void setWristParalel(double offset, double multiplier){
        double rotateAngle = axUp.getCurrentPosition()/540.0 * 360;
        double pos = -rotateAngle / 480 * (0.35-1) * multiplier + offset;
        setWrist(pos);
    }
    public void callTelemetry (){
        telemetry.addData("01. Directia la ax ", axDirection == 1 ? " up " : axDirection == -1 ? " down " : " static ");
        telemetry.addData("02. Directia la slider ",  sliderDirection == 1 ? " forward " : sliderDirection == -1 ? " backward " : " static ");

        telemetry.addData("03. AxUp ", axUp.getCurrentPosition());
        telemetry.addData("04. AxDown ", axDown.getCurrentPosition());
        telemetry.addData("05. Slider ", slider.getCurrentPosition());
        telemetry.addData("06. Setted AxUp ", axPoz);
        telemetry.addData("07. Setted Slider ", sliderPoz);

        telemetry.addData("08. Load in amps up ", ((DcMotorEx)axUp).getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("09. Load in amps down ", ((DcMotorEx)axDown).getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("10. Load in amps slider ", ((DcMotorEx)slider).getCurrent(CurrentUnit.MILLIAMPS));
    }
    private double getAmps(DcMotor motor){
        return ((DcMotorEx)motor).getCurrent(CurrentUnit.MILLIAMPS);
    }
    private void initializeMotor(DcMotor motor, DcMotorSimple.Direction direction){
        motor.setDirection(direction);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx)axUp).setCurrentAlert(maxAmps, CurrentUnit.MILLIAMPS);
    }

}
