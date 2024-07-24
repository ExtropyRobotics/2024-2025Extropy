package org.firstinspires.ftc.teamcode.modules.Controller;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.AutoOpConstants;
import org.firstinspires.ftc.teamcode.modules.Constants;
import org.firstinspires.ftc.teamcode.modules.State.OpModeType;
import org.firstinspires.ftc.teamcode.modules.State.WristState;
import org.firstinspires.ftc.teamcode.modules.TeleOpConstants;

public class WristController {
    Constants CONSTANTS;
    Telemetry telemetry;

    Servo svWristLeft;
    Servo svWristRight;

    public WristController(HardwareMap hardwareMap, Telemetry telemetry, OpModeType type){
        if(type == OpModeType.TELE_OP){
            CONSTANTS = new TeleOpConstants();
        }else if(type == OpModeType.AUTO_OP){
            CONSTANTS = new AutoOpConstants();
        }
        else{
            throw new RuntimeException("NOT AN OFFICIAL GROUP!!!\n" +
                    "Try something like : \n" +
                    "@Autonomous(name = 'someOpModeName', group = 'Autonomous')\n" +
                    "or\n" +
                    "@TeleOp(name = 'someOpModeName', group = 'TeleOp')\n");
        }
        this.telemetry = telemetry;
        svWristLeft = hardwareMap.get(Servo.class, "svWristLeft");
        svWristRight = hardwareMap.get(Servo.class, "svWristRight");

        svWristLeft.setDirection(Servo.Direction.REVERSE);
    }
    public void setState(WristState state){
        switch(state){
            case PLACE_HIGH:{
                setWristPos(CONSTANTS.wristHIGH);
                break;
            }
            case PLACE_MID:{
                setWristPos(CONSTANTS.wristMID);
                break;
            }
            case PLACE_LOW:{
                setWristPos(CONSTANTS.wristLOW);
                break;
            }
            case CARRY: {
                setWristPos(CONSTANTS.wristCARRY);
                break;
            }
            case GET:{
                setWristPos(CONSTANTS.wristGET);
                break;
            }
        }
    }
    private void setWristPos(double pos){
        // 0.32 up max
        // 1 down max

        // high:h
        // mid:
        // low:
        // default: 1 -> down
        telemetry.addData("wristPos",pos);
        svWristLeft.setPosition(pos);
        svWristRight.setPosition(pos);
    }
}
