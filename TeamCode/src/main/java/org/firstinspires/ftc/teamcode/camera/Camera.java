/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.camera;

import static org.firstinspires.ftc.teamcode.camera.CazState.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Point;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
public class Camera{
    Telemetry telemetry;
    OpenCvCamera camera;
    RosuPipeline DetectRosu;
    AlbastruPipeline DetectAlbastru;
    public boolean isOK = false;

    Point location = new Point(0,0);

    CazState state;

    public Camera(HardwareMap hardwareMap ,Telemetry telemetry,CazState state){
        this.state = state;
        this.telemetry = telemetry;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        DetectAlbastru = new AlbastruPipeline(this.telemetry);
        DetectRosu = new RosuPipeline(this.telemetry);

        if(state == RED)camera.setPipeline(DetectRosu);
        if(state == BLUE)camera.setPipeline(DetectAlbastru);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener(){

            @Override
            public void onOpened(){
                camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
                FtcDashboard.getInstance().startCameraStream(camera, 0);
                telemetry.addLine("oppened succesfully");
                isOK = true;
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addLine("error");
                isOK = false;
            }
        });
    }
    public Point getDetectionLocation(){
        if(state == RED)return DetectRosu.where();
        if(state == BLUE)return DetectAlbastru.where();
        return location;
    }

}