package org.firstinspires.ftc.teamcode.modules.Controller;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.AutoOpConstants;
import org.firstinspires.ftc.teamcode.modules.Constants;
import org.firstinspires.ftc.teamcode.modules.State.ActionState;
import org.firstinspires.ftc.teamcode.modules.State.HandState;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.modules.State.OpModeType;
import org.firstinspires.ftc.teamcode.modules.State.WristState;
import org.firstinspires.ftc.teamcode.modules.TeleOpConstants;

public class ArmController extends Thread{
    LinearOpMode opMode;
    OpModeType opModeType;

    Constants CONSTANTS;

    Telemetry telemetry;
    WristController wrist;
    HandController hand;

    Servo svArmLeft;
    Servo svArmRight;

    DcMotor armLeft;
    DcMotor armRight;

    LiftState currentState;
    LiftState givenState;
    WristState wristState;
    HandState handState;
    ActionState actionState;

    boolean startedAuto = false;

    public ElapsedTime timeElapsed = new ElapsedTime(); // to get time since start of opMode
    double time = 1; // time between actions

    public ArmController(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode mode){
        if(opMode.getClass().getAnnotation(TeleOp.class) != null && opMode.getClass().getAnnotation(TeleOp.class).group().equals("TeleOp")){
            opModeType = OpModeType.TELE_OP;
            CONSTANTS = new TeleOpConstants();
        }else if(opMode.getClass().getAnnotation(Autonomous.class) != null && opMode.getClass().getAnnotation(Autonomous.class).group().equals("Autonomous")){
            opModeType = OpModeType.AUTO_OP;
            CONSTANTS = new AutoOpConstants();
        }
        else{
            throw new RuntimeException("NOT AN OFFICIAL GROUP!!!\n" +
                    "Try something like : \n" +
                    "@Autonomous(name = 'someOpModeName', group = 'Autonomous')\n" +
                    "or\n" +
                    "@TeleOp(name = 'someOpModeName', group = 'TeleOp')\n");
        }

        this.opMode = mode;
        this.telemetry = telemetry;

        this.wrist = new WristController(hardwareMap, telemetry);
        this.hand = new HandController(hardwareMap, telemetry);

        this.wristState = WristState.CARRY;
        this.handState = HandState.CLOSE;

        this.currentState = LiftState.DEFAULT;
        this.givenState = LiftState.DEFAULT;

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

        armLeft.setPower(0.8);
        armRight.setPower(0.8);

        setWristState(wristState);
        setHandState(handState);
        setState(currentState);
    }

    public void run(){
        while(!opMode.isStopRequested()){
            setLiftState(givenState);
        }
    }

    public void setState(LiftState state) throws Error {
        if(opModeType == OpModeType.TELE_OP)
            setLiftState(state);
        if(opModeType == OpModeType.AUTO_OP){
            if(!startedAuto){
                this.start();
                startedAuto = true;
            }
            givenState = state;
        }
    }
    public void setLiftState(LiftState state){
        telemetry.addData("rememberedState ", currentState);
        telemetry.addData("functionState ", state);
        telemetry.addData("givenState ", givenState);
        if(state != currentState){ // time = 0 when first into a state
            timeElapsed.reset();
            actionState = ActionState.ONGOING; // remember the action is started
        }else{
            actionState = ActionState.FINISHED;
        }
        switch(state){
            case HIGH: {
                if (currentState == LiftState.DEFAULT) { // if was previously default go into avoidPos
                    CONSTANTS.armPos = CONSTANTS.avoidPos;

                    wristState = WristState.CARRY;
                    handState = HandState.CLOSE;
                } else if (timeElapsed.time() - time >= 0 || armLeft.getCurrentPosition() >= CONSTANTS.liftPos - 20) { // if time has passed go into outPosHIGH
                    CONSTANTS.armPos = CONSTANTS.outPosHIGH;

                    wristState = WristState.PLACE_HIGH;
                    actionState = ActionState.FINISHED;
                }
                CONSTANTS.liftPos = CONSTANTS.highLift;
                currentState = LiftState.HIGH; // set the current state to match the one we are on
                break;
            }
            case MID: {
                if (currentState == LiftState.DEFAULT) { // if was previously default go into avoidPos
                    CONSTANTS.armPos = CONSTANTS.avoidPos;

                    wristState = WristState.CARRY;
                    handState = HandState.CLOSE;
                } else if (timeElapsed.time() - time >= 0 || armLeft.getCurrentPosition() >= CONSTANTS.liftPos - 20) { // if time has passed go into outPosMID
                    CONSTANTS.armPos = CONSTANTS.outPosMID;

                    wristState = WristState.PLACE_MID;
                    actionState = ActionState.FINISHED;
                }

                CONSTANTS.liftPos = CONSTANTS.midLift;
                currentState = LiftState.MID; // set the current state to match the one we are on
                break;
            }
            case LOW: {
                if (currentState == LiftState.DEFAULT) { // if was previously default go into avoidPos
                    CONSTANTS.armPos = CONSTANTS.avoidPos;

                    wristState = WristState.CARRY;
                    handState = HandState.CLOSE;
                } else if (timeElapsed.time() - 2 >= 0 || armLeft.getCurrentPosition() >= CONSTANTS.liftPos - 20) { // if time has passed go into outPosLow
                    CONSTANTS.armPos = CONSTANTS.outPosLOW;

                    wristState = WristState.PLACE_LOW;
                    actionState = ActionState.FINISHED;
                }
                CONSTANTS.liftPos = CONSTANTS.lowLift;
                currentState = LiftState.LOW; // set the current state to match the one we are on
                break;
            }
            case DEFAULT: {
                if (currentState != LiftState.DEFAULT) { // arm goes into avoid zone when first into here
                    CONSTANTS.armPos = CONSTANTS.avoidPos;

                    wristState = WristState.CARRY;
                    handState = HandState.CLOSE;
                } else if (armLeft.getCurrentPosition() <= 20) {
                    CONSTANTS.armPos = CONSTANTS.inPos;

                    actionState = ActionState.ONGOING;
                }
                // if pos of arm is 20 ticks around liftPos
                // set arm to go into inPos

                if (timeElapsed.time() >= 1) { // after 1 sec set the liftPos to default
                    CONSTANTS.liftPos = CONSTANTS.defaultLift;
                    actionState = ActionState.FINISHED;
                }
                currentState = LiftState.DEFAULT; // set the current state to match the one we are on
                break;
            }
        }
        if(actionState == ActionState.ONGOING){
            setArmPos(CONSTANTS.armPos); // set arm servos pos
            setLiftPos(CONSTANTS.liftPos); // set lift motors pos
            setWristState(wristState); // set wrist servos pos
            setHandState(handState); // set hand servo pos
            actionState = ActionState.FINISHED;
        }
        if(!this.isAlive()){
            telemetry.addData("actionState ", actionState); // display current this.state
            telemetry.addData("liftState ", currentState); // display current this.state
            telemetry.addData("wristState ", wristState); // display current this.state
            telemetry.addData("handState ", handState); // display current this.state
        }
        telemetry.update();
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
        CONSTANTS.liftPos = pos;
        armLeft.setTargetPosition(pos);
        armRight.setTargetPosition(pos);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    private void setArmPos(double pos){
        CONSTANTS.armPos = pos;
        svArmLeft.setPosition(pos);
        svArmRight.setPosition(pos);
    }
}
