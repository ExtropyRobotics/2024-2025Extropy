package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {

    DcMotor motorBratSt;
    DcMotor motorBratDr;

        public Arm(HardwareMap hardwareMap){
            motorBratSt = hardwareMap.get (DcMotor.class, "motorBratSt");
            motorBratDr = hardwareMap.get (DcMotor.class, "motorBratDr");
        }
    public void liftPower(double p){
        motorBratDr.setPower(p);
        motorBratSt.setPower(p);
    }

}
