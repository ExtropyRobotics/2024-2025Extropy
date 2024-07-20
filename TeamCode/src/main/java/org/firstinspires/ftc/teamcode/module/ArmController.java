package org.firstinspires.ftc.teamcode.module;

import static org.firstinspires.ftc.teamcode.module.HandState.*;
import static org.firstinspires.ftc.teamcode.module.LiftState.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmController{
    HandController hand;
    Telemetry telemetry;

    Servo svArmLeft;
    Servo svArmRight;

    DcMotor armLeft;
    DcMotor armRight;

    LiftState cState;

    double avoidPos = 0;
    double inPos = 0.1;
    double outPosHIGH = 0.85;
    double outPosMID = 0.7;
    double outPosLOW = 0.55;
    double armPos = outPosLOW;

    int highLift = 1860;
    int midLift = 1400;
    int lowLift = 900;
    int defaultLift = 0;
    int liftPos = defaultLift;

    public ElapsedTime timeElapsed = new ElapsedTime(); // to get time since start of opMode
    double time = 1; // time between actions

    public ArmController(HardwareMap hardwareMap, Telemetry telemetry, HandController hand){
        cState = DEFAULT;
        this.telemetry = telemetry;
        this.hand = hand;

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

        armLeft.setPower(0.5);
        armRight.setPower(0.5);

        setState(DEFAULT);
        hand.setState(CARRY);
    }
    public void setState(LiftState state){
        if(state!=cState){ // time = 0 when first into a state
            timeElapsed.reset();
            hand.setState(CARRY);
        }
        switch(state){
            case HIGH:{
                if(cState == DEFAULT) // if was previously default go into avoidPos
                    armPos = avoidPos;
//                else {
//                    if(timeElapsed.time()-time>=0) // if time has passed go into outPosHIGH
//                        armPos = outPosHIGH;
//                }

                liftPos = highLift;
                if(timeElapsed.time()-time>=0) // if time has passed go into outPosHIGH
                    armPos = outPosHIGH;

                setArmPos(armPos); // set arm servos pos
                setLiftPos(liftPos); // set lift motors pos
                cState = HIGH; // set the current state to match the one we are on
                break;
            }
            case MID:{
                if(cState == DEFAULT) // if was previously default go into avoidPos
                    armPos = avoidPos;
//                else {
//                    if(timeElapsed.time()-time>=0) // if time has passed go into outPosMID
//                        armPos = outPosMID;
//                }
                if(timeElapsed.time()-time>=0) // if time has passed go into outPosMID
                    armPos = outPosMID;

                liftPos = midLift;

                setArmPos(armPos); // set arm servos pos
                setLiftPos(liftPos); // set lift motors pos
                cState = MID; // set the current state to match the one we are on
                break;
            }
            case LOW:{
                if(cState == DEFAULT) // if was previously default go into avoidPos
                    armPos = avoidPos;
//                else {
//                    if(timeElapsed.time()-time>=0) // if time has passed go into outPosLOW
//                        armPos = outPosLOW;
//                }
                liftPos = lowLift;
                if(timeElapsed.time()-2>=0) // if time has passed go into outPosLow
                    armPos = outPosLOW;

                setArmPos(armPos); // set arm servos pos
                setLiftPos(liftPos); // set lift motors pos
                cState = LOW; // set the current state to match the one we are on
                break;
            }
            case DEFAULT: {
                if(cState != DEFAULT) // arm goes into avoid zone when first into here
                    armPos = avoidPos;
                else if(armLeft.getCurrentPosition()<=liftPos+20)
                    armPos = inPos;

                // if pos of arm is 20 ticks around liftPos
                // set arm to go into inPos

                if(timeElapsed.time()-1>=0) // after 1 sec set the liftPos to default
                    liftPos = defaultLift;

                setArmPos(armPos); // set arm servos pos
                setLiftPos(liftPos); // set lift motors pos
                cState = DEFAULT; // set the current state to match the one we are on
                break;
            }
        }
        telemetry.addData("state ", cState);
    }


    public void setLiftPos(int pos){
        liftPos = pos;
        armLeft.setTargetPosition(pos);
        armRight.setTargetPosition(pos);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void setArmPos(double pos){
        armPos = pos;
        svArmLeft.setPosition(pos);
        svArmRight.setPosition(pos);
    }

}
