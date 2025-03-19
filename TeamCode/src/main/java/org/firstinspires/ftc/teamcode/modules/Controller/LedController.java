//package org.firstinspires.ftc.teamcode.modules.Controller;
//
//
//import static org.firstinspires.ftc.teamcode.modules.State.LedState.*;
//
//import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
//import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.modules.State.LedState;
//
//public class LedController {
//    Telemetry telemetry;
//
//    RevBlinkinLedDriver led;
//    Rev2mDistanceSensor left;
//    Rev2mDistanceSensor right;
//
//    double distLeft = 0;
//    double distRight = 0;
//
//    boolean swap = true;
//
//    public LedController(HardwareMap hardwareMap, Telemetry telemetry) {
//        this.telemetry = telemetry;
//        led = hardwareMap.get(RevBlinkinLedDriver.class,"Led");
//        left = hardwareMap.get(Rev2mDistanceSensor.class,"senzorLeft");
//        right = hardwareMap.get(Rev2mDistanceSensor.class,"senzorRight");
//    }
//    public void setState (LedState state){
//        switch (state){
//            case RED:{
//                setPattern(RevBlinkinLedDriver.BlinkinPattern.RED);
//                break;
//            }
//            case BLUE:{
//                setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE);
//                break;
//            }
//            case GREEN:{
//                setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
//                break;
//            }
//            default:break;
//        }
//    }
//    public void setPattern(RevBlinkinLedDriver.BlinkinPattern pattern) {
//        led.setPattern(pattern);
//    }
//    public void runDetection(){
//        if(swap)distLeft = left.getDistance(DistanceUnit.CM);
//        if(!swap)distRight = right.getDistance(DistanceUnit.CM);
//
//        if (distLeft < 7 && distRight < 5)
//            setState(GREEN);
//        if (distLeft > 7 && distRight > 5)
//            setState(RED);
//        if ((distLeft < 7 || distRight < 5) && !(distLeft < 7 && distRight < 5))
//           setState(BLUE);
//
//        swap = !swap;
//    }
//}
