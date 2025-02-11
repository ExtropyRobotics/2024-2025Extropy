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

    private DcMotor slider = null;
    private DcMotor axDown = null;
    private DcMotor axUp = null;
    Servo wrist = null;
    Servo claw = null;

    int axPoz = 0;
    int sliderPoz = 0;

    int axDirection = 0;
    int sliderDirection = 0;

    int maxAmps = 2000;
    Gamepad gamepad2;


    public ArmControler(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad){
        this.telemetry = telemetry;
        this.gamepad2 = gamepad;
        slider = hardwareMap.get(DcMotor.class, "brat");
        axDown = hardwareMap.get(DcMotor.class, "axDown");
        axUp = hardwareMap.get(DcMotor.class, "axUp");
//        wrist = hardwareMap.get(Servo.class, "rotation");
//        claw = hardwareMap.get(Servo.class ,"claw");

        axUp.setDirection(DcMotorSimple.Direction.REVERSE);
        axUp.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        axUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        axUp.setTargetPosition(0);
        axUp.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx)axUp).setCurrentAlert(maxAmps, CurrentUnit.MILLIAMPS);

        axDown.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        axDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        axDown.setTargetPosition(0);
        axDown.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx)axDown).setCurrentAlert(maxAmps, CurrentUnit.MILLIAMPS);

        slider.setDirection(DcMotorSimple.Direction.REVERSE);
        slider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setTargetPosition(0);
        slider.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ((DcMotorEx)slider).setCurrentAlert(maxAmps, CurrentUnit.MILLIAMPS);
    }
    public void resetEncoders(){
        axUp.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        axDown.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void setPower(double pow){
        axUp.setPower(pow);
        axDown.setPower(pow);
        slider.setPower(pow);
    }
    public int setAxPoz (int targetPozAx){
        if(targetPozAx - axUp.getCurrentPosition() > 0) axDirection = 1;
        if(targetPozAx - axUp.getCurrentPosition() < 0) axDirection = -1;

        axPoz = targetPozAx;
//        if(getAmps(axDown) > maxAmps){
//            if(axDirection != 0){
//                axPoz = axUp.getCurrentPosition();
//            }
//        }
        axUp.setTargetPosition(axPoz);
        axDown.setTargetPosition(axPoz);
        return axDirection;
    }
    public int setSliderPoz (int targetPozSlider){
        if(targetPozSlider - slider.getCurrentPosition() > 0) sliderDirection = 1;
        if(targetPozSlider - slider.getCurrentPosition() < 0 ) sliderDirection = -1;

//        // maybe limit with current
//        if(sliderDirection == 1 && getAmps(slider) > maxAmps) {
//            sliderPoz = slider.getCurrentPosition() - 100;
//            slider.setTargetPosition(sliderPoz);
//            return 1;
//        };
//        if(sliderDirection == -1 && getAmps(slider) > maxAmps) {
//            sliderPoz = slider.getCurrentPosition() + 100;
//            slider.setTargetPosition(sliderPoz);
//            return -1;
//        };

        sliderPoz = targetPozSlider;

        slider.setTargetPosition(sliderPoz);
        return sliderDirection;
    }

    public void hand(){
        boolean checkClawPress = false;
        boolean checkWristPress = false;
        boolean toggleClaw = false;
        boolean toggleWrist = false;

        if(gamepad2.a){
            if(!checkClawPress){
                if(toggleClaw == false){
                    claw.setPosition(0.5);
                    toggleClaw = true;
                }
                if(toggleClaw == true){
                    claw.setPosition(0.6);
                    toggleClaw = false;
                }checkClawPress = true;
            }
        }else checkClawPress = false;

        if(gamepad2.y){
            if(!checkWristPress){
                if(toggleWrist == false){
                    wrist.setPosition(0.5);
                    toggleWrist = true;
                }
                if(toggleWrist == true){
                    wrist.setPosition(0.6);
                    toggleWrist = false;
                }checkWristPress = true;
            }
        }else checkWristPress = false;
    }

    public void callTelemetry (){
        telemetry.addData("Directia la ax ", axDirection == 1 ? " up " : axDirection == -1 ? " down " : " static ");
        telemetry.addData("Directia la slider ",  sliderDirection == 1 ? " forward " : sliderDirection == -1 ? " backward " : " static ");

        telemetry.addData("AxUp", axUp.getCurrentPosition());
        telemetry.addData("AxDown", axDown.getCurrentPosition());
        telemetry.addData("Slider", slider.getCurrentPosition());

        telemetry.addData("Setted AxUp", axPoz);
        telemetry.addData("Setted Slider", sliderPoz);

        telemetry.addData("Load in amps up", ((DcMotorEx)axUp).getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("Load in amps down", ((DcMotorEx)axDown).getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("Load in amps slider", ((DcMotorEx)slider).getCurrent(CurrentUnit.MILLIAMPS));
    }
    private double getAmps(DcMotor motor){
        return ((DcMotorEx)motor).getCurrent(CurrentUnit.MILLIAMPS);
    }

}
