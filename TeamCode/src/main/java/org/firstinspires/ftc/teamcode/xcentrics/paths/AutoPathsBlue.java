package org.firstinspires.ftc.teamcode.xcentrics.paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

public class AutoPathsBlue extends AutoPaths{
    //scoring pose of the robot
    private final Pose scorePose = new Pose(60,82,Math.toRadians(135));
    //the x value for the diffrent balls in each pattren
    private static final int p1y = 83, p2y = 58, p3y = 33;
    //the y value for each patren
    private static final int b1x = 35, b2x = 30, b3x = 25;
    //the heading of the robot when intaking a ball
    private static final int intakeHeading = 180;
    private final Pose startPose = new Pose(57, 80, Math.toRadians(90)); // Start Pose of our robot.
    private Follower follower;

    private final Pose PPG1 = new Pose(b1x, p1y,r(intakeHeading)), PPG2 = new Pose(b2x,p1y,r(intakeHeading)), PPG3 = new Pose(b3x,p1y,r(intakeHeading));
    private final Pose PGP1 = new Pose(b1x,p2y,r(intakeHeading)), PGP2 = new Pose(b2x,p2y,r(intakeHeading)), PGP3 = new Pose(b3x,p2y,r(intakeHeading));
    private final Pose GPP1 = new Pose(b1x,p3y,r(intakeHeading)), GPP2 = new Pose(b2x,p3y,r(intakeHeading)), GPP3 = new Pose(b3x,p3y,r(intakeHeading));


    //auto path object
    public AutoPathsBlue(Follower follower){
        this.follower = follower;
    }
    //helper method becuase Math.toRadians is long
    private double r(int i){
        return Math.toRadians(i);
    }

    //paths for PPG pattern
    public void buildPathsPPG(){
        //get the first purple
        getBXX = follower.pathBuilder()
                .addPath(new BezierLine(startPose,PPG1))
                .setLinearHeadingInterpolation(startPose.getHeading(),PPG1.getHeading())
                .build();
        //get the second purple
        getXBX = follower.pathBuilder()
                .addPath(new BezierLine(scorePose,PPG2))
                .setLinearHeadingInterpolation(scorePose.getHeading(),PPG2.getHeading())
                .build();
        //get the last green
        getXXB = follower.pathBuilder()
                .addPath(new BezierLine(scorePose,PPG3))
                .setLinearHeadingInterpolation(scorePose.getHeading(),PPG3.getHeading())
                .build();
        //score the first purple
        scoreBXX = follower.pathBuilder()
                .addPath(new BezierLine(PPG1,scorePose))
                .setLinearHeadingInterpolation(PPG1.getHeading(),scorePose.getHeading())
                .build();
        //score the second purple
        scoreXBX = follower.pathBuilder()
                .addPath(new BezierLine(PPG2,scorePose))
                .setLinearHeadingInterpolation(PPG2.getHeading(),scorePose.getHeading())
                .build();
        //score the last green
        scoreXXB = follower.pathBuilder()
                .addPath(new BezierLine(PPG3,scorePose))
                .setLinearHeadingInterpolation(PPG3.getHeading(),scorePose.getHeading())
                .build();
    }
    public void buildPathsPGP(){
        //get the first purple
        getBXX = follower.pathBuilder()
                .addPath(new BezierLine(startPose,PGP1))
                .setLinearHeadingInterpolation(startPose.getHeading(),PGP1.getHeading())
                .build();
        //get the second green
        getXBX = follower.pathBuilder()
                .addPath(new BezierLine(scorePose,PGP2))
                .setLinearHeadingInterpolation(scorePose.getHeading(),PGP2.getHeading())
                .build();
        //get the last purple
        getXXB = follower.pathBuilder()
                .addPath(new BezierLine(scorePose,PGP3))
                .setLinearHeadingInterpolation(scorePose.getHeading(),PGP3.getHeading())
                .build();
        //score the first purple
        scoreBXX = follower.pathBuilder()
                .addPath(new BezierLine(PGP1,scorePose))
                .setLinearHeadingInterpolation(PGP1.getHeading(),scorePose.getHeading())
                .build();
        //score the second green
        scoreXBX = follower.pathBuilder()
                .addPath(new BezierLine(PGP2,scorePose))
                .setLinearHeadingInterpolation(PGP2.getHeading(),scorePose.getHeading())
                .build();
        //score the last purple
        scoreXXB = follower.pathBuilder()
                .addPath(new BezierLine(PGP3,scorePose))
                .setLinearHeadingInterpolation(PGP3.getHeading(),scorePose.getHeading())
                .build();
    }
    public void buildPathsGPP(){
        //grab the first green
        getBXX = follower.pathBuilder()
                .addPath(new BezierLine(startPose,GPP1))
                .setLinearHeadingInterpolation(startPose.getHeading(),GPP1.getHeading())
                .build();
        //grab the second purple
        getXBX = follower.pathBuilder()
                .addPath(new BezierLine(scorePose,GPP2))
                .setLinearHeadingInterpolation(scorePose.getHeading(),GPP2.getHeading())
                .build();
        //grab the last purple
        getXXB = follower.pathBuilder()
                .addPath(new BezierLine(scorePose,GPP3))
                .setLinearHeadingInterpolation(scorePose.getHeading(),GPP3.getHeading())
                .build();
        //score the first green
        scoreBXX = follower.pathBuilder()
                .addPath(new BezierLine(GPP1,scorePose))
                .setLinearHeadingInterpolation(GPP1.getHeading(),scorePose.getHeading())
                .build();
        //score the second purple
        scoreXBX = follower.pathBuilder()
                .addPath(new BezierLine(GPP2,scorePose))
                .setLinearHeadingInterpolation(GPP2.getHeading(),scorePose.getHeading())
                .build();
        //score the last purple
        scoreXXB = follower.pathBuilder()
                .addPath(new BezierLine(GPP3,scorePose))
                .setLinearHeadingInterpolation(GPP3.getHeading(),scorePose.getHeading())
                .build();
    }
}
