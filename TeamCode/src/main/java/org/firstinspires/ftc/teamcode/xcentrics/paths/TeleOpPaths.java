package org.firstinspires.ftc.teamcode.xcentrics.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class TeleOpPaths {
    private static Pose currentPose;
    private static Pose scorePoseRed = new Pose(12,0,Math.toRadians(0));
    private static Pose scorePoseBlue = new Pose(-12,0,Math.toRadians(180));
    public static PathChain score;

    Follower follower;

    //constructor
    public TeleOpPaths(Follower follower){
        this.follower = follower;
    }

    //build paths
    public void buildPaths(boolean isRed){
        currentPose = follower.getPose();
        if(isRed){
            score = follower.pathBuilder()
                    .addPath(new BezierLine(currentPose, scorePoseRed))
                    .setLinearHeadingInterpolation(currentPose.getHeading(), scorePoseRed.getHeading())
                    .build();
        } else {
            score = follower.pathBuilder()
                    .addPath(new BezierLine(currentPose, scorePoseBlue))
                    .setLinearHeadingInterpolation(currentPose.getHeading(), scorePoseBlue.getHeading())
                    .build();
        }

    }
}
