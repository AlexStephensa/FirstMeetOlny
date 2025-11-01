package org.firstinspires.ftc.teamcode.xcentrics.robots;

import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.xcentrics.components.live.Camera;
import org.firstinspires.ftc.teamcode.xcentrics.components.live.Intake;
import org.firstinspires.ftc.teamcode.xcentrics.components.live.Launcher;

public class LiveRobot extends Robot{

    public Follower     Follower;
    public Camera       camera;
    public Intake       intake;
    public Launcher     launcher;
    TelemetryManager    panelsTelemetry;

    {
        name = "BORIS";
    }

    public LiveRobot(LinearOpMode opMode){
        super(opMode);
        Follower = Constants.createFollower(hwmap);
        camera   = new Camera(this);
        intake   = new Intake(this);
        launcher = new Launcher(this);
    }

    @Override
    public void update(){
        super.update();
    }

    @Override
    public void updateTelemetry(){
        super.updateTelemetry();
    }

}
