package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Movement {
//
    DcMotor motorStF;
    DcMotor motorDrF;
    DcMotor motorStS;
    DcMotor motorDrS;
    int pos = 10;

    double speed;
    double turn;
    double strafe;

    public Movement(HardwareMap hardwareMap){
        motorStF = hardwareMap.get(DcMotor.class, "motorStF");
        motorDrF = hardwareMap.get(DcMotor.class, "motorDrF");
        motorStS = hardwareMap.get(DcMotor.class, "motorStS");
        motorDrS = hardwareMap.get(DcMotor.class, "motorDrS");

        motorStF.setDirection(DcMotor.Direction.REVERSE);
        motorStS.setDirection(DcMotor.Direction.REVERSE);
    }
    public void goPowers(Gamepad gamepad1){ // pt gamepads
        speed = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
        strafe = gamepad1.left_stick_x;

        motorStF.setPower(speed + turn + strafe);
        motorStS.setPower(speed + turn - strafe);
        motorDrF.setPower(speed - turn - strafe);
        motorDrS.setPower(speed - turn + strafe);
    }
    public void setPower(double x){
        motorStF.setPower(x);
        motorStS.setPower(x);
        motorDrF.setPower(x);
        motorDrS.setPower(x);
    }
    public int getPosition(){
        return pos;
    }
    public void setTargetPos(int targetPos){
        motorStF.setTargetPosition(targetPos);
    }
    public void turn(int x){

    }
}
