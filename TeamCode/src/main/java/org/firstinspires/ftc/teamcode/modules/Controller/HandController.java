package org.firstinspires.ftc.teamcode.modules.Controller;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.Constants;
import org.firstinspires.ftc.teamcode.modules.State.HandState;
import org.firstinspires.ftc.teamcode.modules.State.OpModeType;

public class HandController {
    Constants CONSTANTS;
    Telemetry telemetry;
    Servo handLeft;
    Servo handRight;

    public HandController(HardwareMap hardwareMap, Telemetry telemetry, OpModeType type){
        if(type == OpModeType.TELE_OP || type == OpModeType.AUTO_OP) {
            CONSTANTS = new Constants();
        }
        else{
            throw new RuntimeException("NOT AN OFFICIAL GROUP!!!\n" +
                    "Try something like : \n" +
                    "@Autonomous(name = 'someOpModeName', group = 'Autonomous')\n" +
                    "or\n" +
                    "@TeleOp(name = 'someOpModeName', group = 'TeleOp')\n");
        }
        this.telemetry = telemetry;
        handLeft = hardwareMap.get(Servo.class, "handLeft");
        handRight = hardwareMap.get(Servo.class, "handRight");
        handLeft.setDirection(Servo.Direction.REVERSE);
    }
    public void setState(HandState state){
        switch(state){
            case OPEN:{
                setHandPos(CONSTANTS.handOPEN);
                break;
            }
            case CLOSE: {
                setHandPos(CONSTANTS.handCLOSE);
                break;
            }
            default: break;
        }
    }
    private void setHandPos(double pos){
        // 0.2 open
        //
        telemetry.addData("handPos",pos);
        handLeft.setPosition(pos);
        handRight.setPosition(pos);
    }
}
