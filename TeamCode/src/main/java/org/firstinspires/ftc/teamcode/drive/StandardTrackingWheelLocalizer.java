//package org.firstinspires.ftc.teamcode.drive;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import org.firstinspires.ftc.teamcode.util.Encoder;
//
//import java.util.Arrays;
//import java.util.List;
//
///*
// * Sample tracking wheel localizer implementation assuming the standard configuration:
// *
// *    /--------------\
// *    |     ____     |
// *    |     ----     |
// *    | ||        || |
// *    | ||        || |
// *    |              |
// *    |              |
// *    \--------------/
// *
// */
//@Config
//public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
//    public static double TICKS_PER_REV = 0;
//    public static double WHEEL_RADIUS = 2; // in
//    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
//
//<<<<<<< Updated upstream
//    public static double LATERAL_DISTANCE = 10; // in; distance between the left and right wheels
//    public static double FORWARD_OFFSET = 4; // in; offset of the lateral wheel
//=======
//    public static double LATERAL_DISTANCE = 13.127; // in; distance between the left and right wheels
//    public static double FORWARD_OFFSET = 4.7244094488; // in; offset of the lateral wheel
//
//    public static double X_MULTIPLIER = 0.9995; // Multiplier in the X direction
//    public static double Y_MULTIPLIER = 0.995; // Multiplier in the Y direction
//>>>>>>> Stashed changes
//
//    private Encoder leftEncoder, rightEncoder, frontEncoder;
//
//    private List<Integer> lastEncPositions, lastEncVels;
//
//    public StandardTrackingWheelLocalizer(HardwareMap hardwareMap, List<Integer> lastTrackingEncPositions, List<Integer> lastTrackingEncVels) {
//        super(Arrays.asList(
//                new Pose2d(0, LATERAL_DISTANCE / 2, 0), // left
//                new Pose2d(0, -LATERAL_DISTANCE / 2, 0), // right
//                new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front
//        ));
//
//        lastEncPositions = lastTrackingEncPositions;
//        lastEncVels = lastTrackingEncVels;
//
//<<<<<<< Updated upstream
//        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftEncoder"));
//        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightEncoder"));
//        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "frontEncoder"));
//=======
//        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightBack"));
//        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightFront"));
//        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftFront"));
//
//        frontEncoder.setDirection(Encoder.Direction.REVERSE);
//        rightEncoder.setDirection(Encoder.Direction.REVERSE);
//        leftEncoder.setDirection(Encoder.Direction.REVERSE);
//
//>>>>>>> Stashed changes
//
//        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
//    }
//
//    public static double encoderTicksToInches(double ticks) {
//        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
//    }
//
//    @NonNull
//    @Override
//    public List<Double> getWheelPositions() {
//        int leftPos = leftEncoder.getCurrentPosition();
//        int rightPos = rightEncoder.getCurrentPosition();
//        int frontPos = frontEncoder.getCurrentPosition();
//
//        lastEncPositions.clear();
//        lastEncPositions.add(leftPos);
//        lastEncPositions.add(rightPos);
//        lastEncPositions.add(frontPos);
//
//        return Arrays.asList(
//                encoderTicksToInches(leftPos),
//                encoderTicksToInches(rightPos),
//                encoderTicksToInches(frontPos)
//        );
//    }
//
//    @NonNull
//    @Override
//    public List<Double> getWheelVelocities() {
//        int leftVel = (int) leftEncoder.getCorrectedVelocity();
//        int rightVel = (int) rightEncoder.getCorrectedVelocity();
//        int frontVel = (int) frontEncoder.getCorrectedVelocity();
//
//        lastEncVels.clear();
//        lastEncVels.add(leftVel);
//        lastEncVels.add(rightVel);
//        lastEncVels.add(frontVel);
//
//        return Arrays.asList(
//                encoderTicksToInches(leftVel),
//                encoderTicksToInches(rightVel),
//                encoderTicksToInches(frontVel)
//        );
//    }
//}
