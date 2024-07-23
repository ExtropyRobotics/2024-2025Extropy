package org.firstinspires.ftc.teamcode.modules.Controller;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.State.WristState;

public class WristController {
    Telemetry telemetry;

    Servo svWristLeft;
    Servo svWristRight;

    double highPos = 0.5;
    double midPos = 0.55;
    double lowPos = 0.65;
    double carryPos = 0.35;
    double getPos = 0.925;

    public WristController(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        svWristLeft = hardwareMap.get(Servo.class, "svWristLeft");
        svWristRight = hardwareMap.get(Servo.class, "svWristRight");

        svWristLeft.setDirection(Servo.Direction.REVERSE);
    }
    public void setState(WristState state){
        switch(state){
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
            case CARRY: {
                setWristPos(carryPos);
                break;
            }
            case GET:{
                setWristPos(getPos);
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
