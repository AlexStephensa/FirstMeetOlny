package org.firstinspires.ftc.teamcode.xcentrics.components.live;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.xcentrics.components.Component;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import static org.firstinspires.ftc.teamcode.xcentrics.components.live.CameraConfig.GPP_TAG_ID;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.CameraConfig.PGP_TAG_ID;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.CameraConfig.PPG_TAG_ID;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.CameraConfig.cameraOn;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.CameraConfig.currentDetections;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.CameraConfig.foundID;

import java.util.ArrayList;

@Configurable
class CameraConfig{
    public static final int PPG_TAG_ID = 23;
    public static final int PGP_TAG_ID = 22;
    public static final int GPP_TAG_ID = 21;
    public static int foundID = -1;
    public static ArrayList<AprilTagDetection> currentDetections;
    public static boolean cameraOn = true;
}
public class Camera extends Component {
    /// vision things//
    private VisionPortal visionPortal;
    private AprilTagProcessor aprilTag;

    public Camera(Robot robot){
        super(robot);
    }

    public void registerHardware(HardwareMap hardwareMap){
        super.registerHardware(hardwareMap);

        /// vision portal ///
        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class,"Webcam 1"))
                .addProcessor(aprilTag)
                .build();

    }
    public void startup(){
        super.startup();
        /// init things ///
        aprilTag = new AprilTagProcessor.Builder().build();
        //adjust image decimation to trade-off detection-range for detection rate
        aprilTag.setDecimation(2);
    }
    public void useCamera(boolean useCamera){
        cameraOn = useCamera;
    }

    public void update(OpMode opMode){
        super.update(opMode);
        if(CameraConfig.cameraOn){
            //go through the list of detected tags and look for a matching tag
            currentDetections = aprilTag.getDetections();
            for(AprilTagDetection detection : currentDetections){
                //look to see if we have size info on this tag
                if(detection.metadata != null){
                    //check to see what tag it is
                    if(detection.id == PPG_TAG_ID){
                        foundID = 21;
                        break;
                    } else if(detection.id == PGP_TAG_ID){
                        foundID = 22;
                        break;
                    } else if(detection.id == GPP_TAG_ID){
                        foundID = 23;
                        break;
                    }
                } else {
                    //tag not in library
                    addData("Tag ID is not in tag library: ",detection.id);
                }
            } // end of loop
        }

    }
    public void shutdown(){
        super.shutdown();
        cameraOn = false;
        visionPortal.close();
    }
    public int foundID(){
        return foundID;
    }
    public void updateTelemetry(Telemetry telemetry){
        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }
        addData("# AprilTags Detected", currentDetections.size());
    }

}
