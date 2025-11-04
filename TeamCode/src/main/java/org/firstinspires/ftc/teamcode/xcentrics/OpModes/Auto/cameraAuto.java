package org.firstinspires.ftc.teamcode.xcentrics.OpModes.Auto;

import org.firstinspires.ftc.teamcode.xcentrics.paths.AutoPaths;
import org.firstinspires.ftc.teamcode.xcentrics.paths.AutoPathsBlue;
import org.firstinspires.ftc.teamcode.xcentrics.paths.AutoPathsRed;

public class cameraAuto extends LiveAutoBase{
    private AutoPaths paths;
    public static boolean isRed = false;
    public static int state = -1;
    @Override
    public void on_init() {

        if(isRed){
            paths = new AutoPathsRed(robot.follower);
        } else {
            paths = new AutoPathsBlue(robot.follower);
        }

    }

    @Override
    public void on_start() {

    }

    @Override
    public void on_stop() {

    }

    @Override
    public void on_loop() {
        robot.update();
    }
    private void stateMachene(){

        switch(state){
            case -1:
                //read the april tag to get the pattren
                robot.camera.useCamera(true);
                //update the robot
                robot.update();
                //build the paths based on the pattren detected
                if(robot.camera.foundID() == 21){
                    paths.buildPathsPPG();
                    incrament();
                    break;
                } else if(robot.camera.foundID() == 22){
                    paths.buildPathsPGP();
                    incrament();
                    break;
                } else if(robot.camera.foundID() == 23){
                    paths.buildPathsGPP();
                    incrament();
                    break;
                }
            case 2:
                //follow the paths based on the pattren detected
                robot.follower.followPath(paths.getBXX);
                robot.intake.intake();
                robot.update();
                incrament();
                break;
            case 3:
                //go to score pose
                if(!robot.follower.isBusy()){
                    robot.follower.followPath(paths.scoreBXX);
                    robot.update();
                    robot.launcher.setSpeed(1000);
                    incrament();
                    break;
                }
            case 4:
                //launch ball
                if((!robot.follower.isBusy()&&robot.launcher.canLaunch())){
                    robot.update();
                    robot.launcher.launch();
                    robot.update();
                    robot.intake.stopIntake();
                    robot.update();
                    incrament();
                    break;
                }
            case 5:
                //get the second ball
                robot.follower.followPath(paths.getXBX);
                robot.update();
                robot.intake.intake();
                incrament();
                break;
            case 6:
                //go to score pose
                if(!robot.follower.isBusy()){
                    robot.follower.followPath(paths.scoreXBX);
                    robot.update();
                    robot.launcher.setSpeed(1000);
                    robot.update();
                    incrament();
                    break;
                }
            case 7:
                //score second ball
                if((!robot.follower.isBusy()&&robot.launcher.canLaunch())){
                    robot.update();
                    robot.launcher.launch();
                    robot.update();
                    robot.intake.stopIntake();
                    robot.update();
                    incrament();
                    break;
                }
            case 8:
                //get the third ball
                robot.follower.followPath(paths.getXXB);
                robot.intake.intake();
                incrament();
                break;
            case 9:
                //go to score pose
                if(!robot.follower.isBusy()){
                    robot.follower.followPath(paths.scoreXXB);
                    robot.update();
                    robot.launcher.setSpeed(1000);
                    robot.update();
                    incrament();;
                    break;
                }
            case 10:
                //score the third ball
                if((!robot.follower.isBusy()&&robot.launcher.canLaunch())){
                    robot.update();
                    robot.launcher.launch();
                    robot.update();
                    robot.intake.stopIntake();
                    robot.update();
                    incrament();
                    break;
                }
            case 11:
                //move out of start zone
                robot.follower.followPath(paths.getXBX);
                robot.update();
                robot.intake.stopIntake();
                robot.update();
                robot.launcher.setSpeed(0);
                robot.update();
                break;
        }
    }
    private void incrament(){
        robot.update();
        state++;
    }
    private void setState(int i){
        state = i;
    }
}

