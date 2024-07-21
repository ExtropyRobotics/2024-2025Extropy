package org.firstinspires.ftc.teamcode.modules.Controller;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.State.PlaneState;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlaneController {
    Telemetry telemetry;
    Servo plane;

    double holdPos = 0.7;
    double releasePos = 0.3;
    public PlaneController(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        plane = hardwareMap.get(Servo.class,"plane");
    }
    public void setState (PlaneState state){
        switch (state){
            case HOLD:{
                setPlanePos(holdPos);
                break;
            }
            case RELEASE:{
                setPlanePos(releasePos);
                break;
            }
        }
    }
    private void setPlanePos (double pos){
        telemetry.addData("planePos",pos);
        plane.setPosition(pos);
    }
}
