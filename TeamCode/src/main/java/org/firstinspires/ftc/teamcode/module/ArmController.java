package org.firstinspires.ftc.teamcode.module;

import static org.firstinspires.ftc.teamcode.module.LiftState.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmController{
    Telemetry telemetry;

    Servo svArmLeft;
    Servo svArmRight;

    DcMotor armLeft;
    DcMotor armRight;

    LiftState cState;

    double avoidPos = 0;
    double inPos = 0.1;
    double outPosHIGH = 0.70;
    double outPosMID = 0.55;
    double outPosLOW = 0.40;
    double armPos = outPosLOW;

    int highLift = 1850;
    int midLift = 1400;
    int lowLift = 900;
    int defaultLift = 0;
    int liftPos = defaultLift;

    public ElapsedTime timeElapsed = new ElapsedTime();
    double time = 1;

    public ArmController(HardwareMap hardwareMap, Telemetry telemetry){
        cState = DEFAULT;
        this.telemetry = telemetry;

        svArmLeft = hardwareMap.get(Servo.class, "svArmLeft");
        svArmRight = hardwareMap.get(Servo.class, "svArmRight");
        svArmLeft.setDirection(Servo.Direction.REVERSE);

        armLeft = hardwareMap.get(DcMotor.class, "armLeft");
        armRight = hardwareMap.get(DcMotor.class, "armRight");
        armLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        armRight.setDirection(DcMotorSimple.Direction.REVERSE);

        armLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        armRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setState(DEFAULT);
    }

//        switch(valoare){
//            case valoareAlta:{cod}
//            case valoareAlta2:{cod}
//            default:{cod}
//        }
//        if(valoare==valoareAlta){cod}
//        if(valoare==valoareAlta2){cod}
//        if(nicniune){default cod}

    public void setState(LiftState state){
        switch(state){
            case HIGH:{
                if(cState != HIGH) timeElapsed.reset();
                telemetry.addData("",HIGH);

                armPos = avoidPos;
                liftPos = highLift;
                if(timeElapsed.time()-time>=0)
                    armPos = outPosHIGH;

                setArmPos(armPos);
                setLiftPos(liftPos);
                cState = HIGH;
                break;
            }
            case MID:{
                if(cState != MID) timeElapsed.reset();
                telemetry.addData("",MID);

                armPos = avoidPos;
                liftPos = midLift;
                if(timeElapsed.time()-time>=0)
                    armPos = outPosMID;

                setArmPos(armPos);
                setLiftPos(liftPos);
                cState = MID;
                break;
            }
            case LOW:{
                if(cState != LOW) timeElapsed.reset();
                telemetry.addData("",LOW);

                armPos = avoidPos;
                liftPos = lowLift;
                if(timeElapsed.time()-time>=0)
                    armPos = outPosLOW;

                setArmPos(armPos);
                setLiftPos(liftPos);
                cState = LOW;
                break;
            }
            case DEFAULT: {
                if(cState != DEFAULT) timeElapsed.reset();
                telemetry.addData("",DEFAULT);

                if(armLeft.getCurrentPosition()<=liftPos+5)
                    armPos = inPos;
                if(timeElapsed.time()-1>=0)
                    liftPos = defaultLift;

                setLiftPos(liftPos);
                setArmPos(armPos);
                cState = DEFAULT;
                break;
            }
        }
        telemetry.addData("state given", state);
        telemetry.addData("state currently on", cState);
    }


    public void setLiftPos(int pos){
        // high: 1850
        // mid: 1400
        // low: 900
        // default: 0
        liftPos = pos;
        armLeft.setTargetPosition(pos);
        armRight.setTargetPosition(pos);
        armLeft.setPower(0.75);
        armRight.setPower(0.75);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setArmPos(double pos){
        // high: 0.7
        // mid: 0.55
        // low: 0.4
        // default: 0.04
        armPos = pos;
        telemetry.addData("armPos",pos);
        svArmLeft.setPosition(pos);
        svArmRight.setPosition(pos);
    }

}
