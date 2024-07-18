package org.firstinspires.ftc.teamcode.camera;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class RosuPipeline extends OpenCvPipeline
{
    Telemetry telemetry;
    int cx = 0;
    int cy = 0;
    public RosuPipeline(Telemetry telemetry){
        this.telemetry = telemetry;
    }
    @Override
    public Mat processFrame(Mat input){
        Mat start = new Mat();
        input.copyTo(start);
        Mat mask = input;
        Mat hsv = new Mat();
        Mat edges = new Mat();
        Imgproc.blur(mask , mask , new Size(30,30));
        Imgproc.cvtColor(mask, mask, Imgproc.COLOR_RGB2HSV);

        Core.inRange(mask , new Scalar(0 , 100 , 40) , new Scalar(15 , 255 , 255) , mask);
        Mat destination = new Mat();
        Core.bitwise_and(input , input , destination , mask);

        Imgproc.Canny(destination , edges , 400 , 500);

        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        int maximum = 0;
        int idMax = 0;
        MatOfPoint maxCnt = new MatOfPoint();
        Moments mm;
        for(int i = 0 ; i<contours.size() ; i++){
            int area = (int)Imgproc.contourArea(contours.get(i));
            mm = Imgproc.moments(contours.get(i));
            cx = (int)(mm.m10/mm.m00);
            cy = (int)(mm.m01/mm.m00);
            if(area>maximum && cy>70) {
                maximum = area;
                maxCnt = contours.get(i);
                idMax = i;
            }
        }
        mm = Imgproc.moments(maxCnt);
        cx = (int)(mm.m10/mm.m00);
        cy = (int)(mm.m01/mm.m00);

        Imgproc.drawContours(start , contours , idMax , new Scalar(0,0,0) , 7);
        Imgproc.line(start, new Point(230,0), new Point(230 , 240),new Scalar(255,10,10),3);
        hsv.release();
        edges.release();
        return start;
    }
    public Point where(){
        return new Point(cx, cy);
    }
}