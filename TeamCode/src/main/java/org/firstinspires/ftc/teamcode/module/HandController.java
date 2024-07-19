package org.firstinspires.ftc.teamcode.module;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HandController {
    Telemetry telemetry;

    Servo handLeft;
    Servo handRight;
    Servo svWristLeft;
    Servo svWristRight;

    double closePos = 0.1;
    double openPos = 0.28;

    double highPos = 0.25;
    double midPos = 0.4;
    double lowPos = 0.35;
    double carryPos = 0.65;
    double getPos = 0.975;

    public HandController(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        svWristLeft = hardwareMap.get(Servo.class, "svWristLeft");
        svWristRight = hardwareMap.get(Servo.class, "svWristRight");
        handLeft = hardwareMap.get(Servo.class, "handLeft");
        handRight = hardwareMap.get(Servo.class, "handRight");

        handLeft.setDirection(Servo.Direction.REVERSE);
        svWristLeft.setDirection(Servo.Direction.REVERSE);
    }
    public void setState(HandState state){
        switch(state){
            case OPEN:{
                setHandPos(openPos);
                break;
            }
            case CLOSE:
            {
                setHandPos(closePos);
                break;
            }
            case CARRY:
            {
                setWristPos(carryPos);
                break;
            }
            case GET:{
                setWristPos(getPos);
                break;
            }
            case PLACE_HIGH:{
                setWristPos(highPos);
                break;
            }
            case PLACE_MID:{
                setWristPos(midPos);
                break;
            }
            case PLACE_LOW:{
                setWristPos(lowPos);
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
    private void setHandPos(double pos){
        // 0.2 open
        //
        telemetry.addData("handPos",pos);
        handLeft.setPosition(pos);
        handRight.setPosition(pos);
    }
}
