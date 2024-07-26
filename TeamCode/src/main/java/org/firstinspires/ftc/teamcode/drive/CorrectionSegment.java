//package org.firstinspires.ftc.teamcode.drive;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//
//import org.firstinspires.ftc.teamcode.trajectorysequence.sequencesegment.SequenceSegment;
//
//public class CorrectionSegment extends SequenceSegment {
//    private Pose2d targetPose;
//
//    public CorrectionSegment(Pose2d targetPose) {
//        this.targetPose = targetPose;
//    }
//
//    public Pose2d getTargetPose() {
//        return targetPose;
//    }
//
//    @Override
//    public double getDuration() {
//        return Double.POSITIVE_INFINITY; // infinity cuz cool
//    }
//}