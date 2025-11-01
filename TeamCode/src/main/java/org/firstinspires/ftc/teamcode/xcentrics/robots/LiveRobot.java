package org.firstinspires.ftc.teamcode.xcentrics.robots;

import com.bylazar.telemetry.TelemetryManager;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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
