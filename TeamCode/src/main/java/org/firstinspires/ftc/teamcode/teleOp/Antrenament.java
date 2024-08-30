package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Movement;
import org.firstinspires.ftc.teamcode.util.Arm;

@TeleOp(name = "Antrenament")
public class Antrenament extends LinearOpMode {

    DcMotor motorStF = null;
    DcMotor motorDrF = null;
    DcMotor motorStS = null;
    DcMotor motorDrS = null;

    int targetPoz = 1;

    @Override
    public void runOpMode() throws InterruptedException{

        motorStF = hardwareMap.get(DcMotor.class, "motorStF");
        motorDrF = hardwareMap.get(DcMotor.class, "motorDrF");
        motorStS = hardwareMap.get(DcMotor.class, "motorStS");
        motorDrS = hardwareMap.get(DcMotor.class, "motorDrS");

        motorStF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorStF.setDirection(DcMotorSimple.Direction.REVERSE);

        motorStF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorStF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorStF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();



        while(opModeIsActive()){


            if(gamepad2.left_stick_y > 0){
                targetPoz += 1;
            }
            if(gamepad2.left_stick_y < 0){
                targetPoz -= 1;
            }

            motorStF.setTargetPosition(targetPoz);

            motorStF.setPower(0.4);

            motorStF.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            telemetry.addData("current Position", targetPoz);
            telemetry.addData("motorStF Position", motorStF.getCurrentPosition());
            telemetry.update();

        }
    }
}



//@TeleOp(name = "Antrenament")
//public class Antrenament extends LinearOpMode {

//    Movement mv;
//    Arm am;

//    @Override
//    public void runOpMode() throws InterruptedException{

//        mv = new Movement(hardwareMap);
//        am = new Arm (hardwareMap);
//        int currentPos = 0;

//        waitForStart();

//        while(opModeIsActive()){
//            mv.goPowers(gamepad1);
//            currentPos = mv.getPosition();
//            mv.setTargetPos(5);
//            am.liftPower(0.5);
//        }
//
//    }
//}
