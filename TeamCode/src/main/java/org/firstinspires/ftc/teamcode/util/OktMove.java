package org.firstinspires.ftc.teamcode.util;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OktMove {
    DcMotor motorL;
    DcMotor motorR;

    public OktMove(HardwareMap hardwareMap){

        motorL = hardwareMap.get(DcMotor.class,"motorL");
        motorR = hardwareMap.get(DcMotor.class,"motorR");
        motorR.setDirection(DcMotor.Direction.REVERSE);

    }


}
