package org.firstinspires.ftc.teamcode.modules.Controller;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.State.HandState;

public class HandController {
    Telemetry telemetry;
    Servo handLeft;
    Servo handRight;

    double closePos = 0.1;
    double openPos = 0.28;
    public HandController(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        handLeft = hardwareMap.get(Servo.class, "handLeft");
        handRight = hardwareMap.get(Servo.class, "handRight");
        handLeft.setDirection(Servo.Direction.REVERSE);
    }
    public void setState(HandState state){
        switch(state){
            case OPEN:{
                setHandPos(openPos);
                break;
            }
            case CLOSE: {
                setHandPos(closePos);
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
