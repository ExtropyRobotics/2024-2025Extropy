package org.firstinspires.ftc.teamcode.modules.Controller;

import static org.firstinspires.ftc.teamcode.modules.State.HandState.*;
import static org.firstinspires.ftc.teamcode.modules.State.WristState.*;
import static org.firstinspires.ftc.teamcode.modules.State.LiftState.*;
import static org.firstinspires.ftc.teamcode.modules.State.ActionState.*;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.State.ActionState;
import org.firstinspires.ftc.teamcode.modules.State.HandState;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.modules.State.WristState;

public class ArmController{
    Telemetry telemetry;
    WristController wrist;
    HandController hand;

    Servo svArmLeft;
    Servo svArmRight;

    DcMotor armLeft;
    DcMotor armRight;

    LiftState cState;
    WristState wristState;
    HandState handState;
    ActionState actionState;


    double avoidPos = 0.1;
    double inPos = 0.2;
    double outPosHIGH = 0.925;
    double outPosMID = 0.875;
    double outPosLOW = 0.8;
    double armPos = inPos;

    int highLift = 1860;
    int midLift = 1550;
    int lowLift = 1200;
    int defaultLift = 0;
    int liftPos = defaultLift;

    public ElapsedTime timeElapsed = new ElapsedTime(); // to get time since start of opMode
    double time = 1; // time between actions

    public ArmController(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.wristState = CARRY;
        this.handState = CLOSE;
        this.wrist = new WristController(hardwareMap, telemetry);
        this.hand = new HandController(hardwareMap, telemetry);

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

        armLeft.setPower(1);
        armRight.setPower(1);

        cState = DEFAULT;

        setWristState(wristState);
        setHandState(handState);
        setState(cState);
    }
    public void setState(LiftState state){
        if(state!=cState){ // time = 0 when first into a state
            timeElapsed.reset();
            actionState = ONGOING;
        }
        else {
            actionState = FINISHED;
        }
        switch(state){
            case HIGH:{
                if(cState == DEFAULT){ // if was previously default go into avoidPos
                    armPos = avoidPos;
                    wristState = CARRY;
                    handState = CLOSE;
                }
                else if(timeElapsed.time()-time>=0 || armLeft.getCurrentPosition()>=liftPos-20){ // if time has passed go into outPosHIGH
                    wristState = PLACE_HIGH;
                    armPos = outPosHIGH;
                    actionState = ONGOING;
                }
                liftPos = highLift;
                cState = HIGH; // set the current state to match the one we are on
                break;
            }
            case MID:{
                if(cState == DEFAULT){ // if was previously default go into avoidPos
                    armPos = avoidPos;
                    wristState = CARRY;
                    handState = CLOSE;
                }
                else if(timeElapsed.time()-time>=0 || armLeft.getCurrentPosition()>=liftPos-20) { // if time has passed go into outPosMID
                    armPos = outPosMID;
                    wristState = PLACE_MID;
                    actionState = ONGOING;
                }

                liftPos = midLift;
                cState = MID; // set the current state to match the one we are on
                break;
            }
            case LOW: {
                if (cState == DEFAULT) { // if was previously default go into avoidPos
                    armPos = avoidPos;
                    wristState = CARRY;
                    handState = CLOSE;
                }
                else if (timeElapsed.time() - 2 >= 0 || armLeft.getCurrentPosition()>=liftPos-20){ // if time has passed go into outPosLow
                    armPos = outPosLOW;
                    wristState = PLACE_LOW;
                    actionState = ONGOING;
                }
                liftPos = lowLift;
                cState = LOW; // set the current state to match the one we are on
                break;
            }
            case DEFAULT: {
                if(cState != DEFAULT){ // arm goes into avoid zone when first into here
                    armPos = avoidPos;
                    wristState = CARRY;
                    handState = CLOSE;
                }
                else if(armLeft.getCurrentPosition()<=20) {
                    armPos = inPos;
                    actionState = ONGOING;
                }
                // if pos of arm is 20 ticks around liftPos
                // set arm to go into inPos

                if(timeElapsed.time()-1>=0){ // after 1 sec set the liftPos to default
                    liftPos = defaultLift;
                    actionState = ONGOING;
                }
                cState = DEFAULT; // set the current state to match the one we are on
                break;
            }
        }
        if(actionState == ONGOING){
            setArmPos(armPos); // set arm servos pos
            setLiftPos(liftPos); // set lift motors pos
            setWristState(wristState); // set wrist servos pos
            setHandState(handState); // set hand servo pos
            actionState = FINISHED;
        }
        telemetry.addData("actionState ", actionState); // display current this.state
        telemetry.addData("liftState ", cState); // display current this.state
        telemetry.addData("wristState ", wristState); // display current this.state
        telemetry.addData("handState ", handState); // display current this.state
    }
    public void setHandState(HandState state){
        this.handState = state;
        hand.setState(handState);
    }
    public void setWristState(WristState state){
        wristState = state;
        wrist.setState(wristState);
    }
    private void setLiftPos(int pos){
        liftPos = pos;
        armLeft.setTargetPosition(pos);
        armRight.setTargetPosition(pos);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void setArmPos(double pos){
        armPos = pos;
        svArmLeft.setPosition(pos);
        svArmRight.setPosition(pos);
    }

}
