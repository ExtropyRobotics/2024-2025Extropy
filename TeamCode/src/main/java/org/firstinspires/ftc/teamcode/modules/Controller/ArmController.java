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
import org.firstinspires.ftc.teamcode.modules.Constants;
import org.firstinspires.ftc.teamcode.modules.State.HandState;
import org.firstinspires.ftc.teamcode.modules.State.LiftState;
import org.firstinspires.ftc.teamcode.modules.State.OpModeType;
import org.firstinspires.ftc.teamcode.modules.State.WristState;

public class ArmController extends Thread{
    LinearOpMode opMode;
    OpModeType opModeType;
    public Constants CONSTANTS;

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

    public ElapsedTime timeElapsed = new ElapsedTime(); // to get time since start of opMode
    double time = 1; // time between actions

    public ArmController(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode mode){
        this.opMode = mode;
        CONSTANTS = new Constants();

        if(opMode.getClass().getAnnotation(TeleOp.class) != null && opMode.getClass().getAnnotation(TeleOp.class).group().equals("TeleOp")){
            opModeType = OpModeType.TELE_OP;
        }else if(opMode.getClass().getAnnotation(Autonomous.class) != null && opMode.getClass().getAnnotation(Autonomous.class).group().equals("Autonomous")){
            opModeType = OpModeType.AUTO_OP;
        }
        else{
            throw new RuntimeException("NOT AN OFFICIAL GROUP!!!\n" +
                    "Try something like : \n" +
                    "@Autonomous(name = 'someOpModeName', group = 'Autonomous')\n" +
                    "or\n" +
                    "@TeleOp(name = 'someOpModeName', group = 'TeleOp')\n");
        }

        this.telemetry = telemetry;

        this.wrist = new WristController(hardwareMap, telemetry, opModeType);
        this.hand = new HandController(hardwareMap, telemetry, opModeType);

        this.wristState = WristState.CARRY;
        this.handState = HandState.BOTH_CLOSE;

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
        if(opModeType != OpModeType.AUTO_OP)setState(currentState);
    }

    @Override
    public void run(){
        while(!opMode.isStopRequested() && this.isAlive()){
            setLiftState(givenState);
        }
    }
    public void setState(LiftState state){
        if(opModeType == OpModeType.TELE_OP)
            setLiftState(state);
        if(opModeType == OpModeType.AUTO_OP){
            givenState = state;
        }
    }
    public void setLiftState(LiftState state){
        if(state != currentState){ // time = 0 when first into state
            timeElapsed.reset();
        }
        switch(state){
            case HIGH: {
                if (currentState == LiftState.DEFAULT) { // if was previously default go into avoidPos
                    CONSTANTS.armPos = CONSTANTS.armAVOID;

                    wristState = WristState.CARRY;
                    handState = HandState.BOTH_CLOSE;
                } else if (timeElapsed.time() - time >= 0 || armLeft.getCurrentPosition() >= CONSTANTS.liftPos - 20) { // if time has passed go into outPosHIGH
                    CONSTANTS.armPos = CONSTANTS.armHIGH;

                    wristState = WristState.PLACE_HIGH;
                }
                CONSTANTS.liftPos = CONSTANTS.liftHIGH;
                currentState = LiftState.HIGH; // set the current state to match the one we are on
                break;
            }
            case MID: {
                if (currentState == LiftState.DEFAULT) { // if was previously default go into avoidPos
                    CONSTANTS.armPos = CONSTANTS.armAVOID;

                    wristState = WristState.CARRY;
                    handState = HandState.BOTH_CLOSE;
                } else if (timeElapsed.time() - time >= 0 || armLeft.getCurrentPosition() >= CONSTANTS.liftPos - 20) { // if time has passed go into outPosMID
                    CONSTANTS.armPos = CONSTANTS.armMID;

                    wristState = WristState.PLACE_MID;
                }

                CONSTANTS.liftPos = CONSTANTS.liftMID;
                currentState = LiftState.MID; // set the current state to match the one we are on
                break;
            }
            case LOW: {
                if (currentState == LiftState.DEFAULT) { // if was previously default go into avoidPos
                    CONSTANTS.armPos = CONSTANTS.armAVOID;

                    wristState = WristState.CARRY;
                    handState = HandState.BOTH_CLOSE;
                } else if (timeElapsed.time() - 2 >= 0 || armLeft.getCurrentPosition() >= CONSTANTS.liftPos - 20) { // if time has passed go into outPosLow
                    CONSTANTS.armPos = CONSTANTS.armLOW;

                    wristState = WristState.PLACE_LOW;
                }
                CONSTANTS.liftPos = CONSTANTS.liftLOW;
                currentState = LiftState.LOW; // set the current state to match the one we are on
                break;
            }
            case DEFAULT: {
                if (currentState != LiftState.DEFAULT) { // arm goes into avoid zone when first into here
                    CONSTANTS.armPos = CONSTANTS.armAVOID;

                    wristState = WristState.CARRY;
                    handState = HandState.BOTH_CLOSE;
                } else if (armLeft.getCurrentPosition() <= 20) {
                    CONSTANTS.armPos = CONSTANTS.armDEFAULT;
                }
                // if pos of arm is 20 ticks around liftPos
                // set arm to go into inPos

                if (timeElapsed.time() >= 1) { // after 1 sec set the liftPos to default
                    CONSTANTS.liftPos = CONSTANTS.liftDEFAULT;
                }
                currentState = LiftState.DEFAULT; // set the current state to match the one we are on
                break;
            }
            case HANG_UP:{
                if(currentState != state)CONSTANTS.armPos = CONSTANTS.armAVOID;
                if(armLeft.getCurrentPosition()>=1000)CONSTANTS.armPos = CONSTANTS.armHIGH;

                CONSTANTS.liftPos = CONSTANTS.liftHANG_UP;
                wristState = WristState.CARRY;
                currentState = LiftState.HANG_UP;
                break;
            }
            case HANG_DOWN: {
                if(timeElapsed.time()>=1)CONSTANTS.liftPos = CONSTANTS.liftHANG_DOWN;

                CONSTANTS.armPos = CONSTANTS.armHIGH;
                wristState = WristState.CARRY;
                currentState = LiftState.HANG_DOWN;
                break;
            }
            case PLACE_PURPLE:{
                CONSTANTS.armPos = CONSTANTS.armSTACK;
                wristState = WristState.GET_LOW;

                if(timeElapsed.time() >= 0.5)handState = HandState.LEFT_OPEN;
                currentState = LiftState.PLACE_PURPLE;
                break;
            }
            case TAKE_WHITE:{
                wristState = WristState.GET_STACK;
                handState = HandState.LEFT_OPEN;
                CONSTANTS.armPos = CONSTANTS.armSTACK;
                if(timeElapsed.time()>0.5)handState = HandState.BOTH_CLOSE;
                if(timeElapsed.time()>0.75)wristState = WristState.CARRY;
                if(timeElapsed.time()>1)CONSTANTS.armPos = CONSTANTS.armAVOID;

                currentState = LiftState.TAKE_WHITE;

                break;
            }
        }

        setArmPos(CONSTANTS.armPos); // set arm servos pos
        setLiftPos(CONSTANTS.liftPos); // set lift motors pos
        setWristState(wristState); // set wrist servos pos
        setHandState(handState); // set hand servo pos
    }
    public void setHandState(HandState state){
        this.handState = state;
        hand.setState(handState);
    }
    public void setWristState(WristState state){
        wristState = state;
        wrist.setState(wristState);
    }
    public void setArmPos(double pos){
        CONSTANTS.armPos = pos;
        svArmLeft.setPosition(pos);
        svArmRight.setPosition(pos);
    }
    public void setLiftPos(int pos){
        CONSTANTS.liftPos = pos;
        armLeft.setTargetPosition(pos);
        armRight.setTargetPosition(pos);
        armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
