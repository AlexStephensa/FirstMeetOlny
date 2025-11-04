package org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.xcentrics.paths.TeleOpPaths;
@TeleOp(name = "TeleOpLive")
public class TeleOpLive extends LiveTeleopBase{
    private TeleOpPaths paths;
    private static boolean autoDrive = false;

    @Override
    public void on_init() {
    }

    @Override
    public void on_start() {
    robot.follower.startTeleOpDrive();
    }

    @Override
    public void on_stop() {

    }

    @Override
    public void on_loop() {

        //autoDrive
//        if(gamepad1.a){
//            robot.launcher.spinUp();
//            robot.follower.followPath(paths.buildPaths(robot.isRed()));
//            autoDrive = true;
//        }
//        if(!robot.follower.isBusy()){
//            autoDrive = false;
//        }

        //stop following path
        if(gamepad1.ps){
            robot.follower.breakFollowing();
        }

        //teleop controls
       // if(!autoDrive) {
            robot.follower.setTeleOpDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
        //}

        //spin up launcher
        if(gamepad2.right_bumper){
            robot.launcher.spinUp();
        }

        //launch ball
        if(gamepad2.a){
            robot.launcher.launch();
        }

        //idle launcher
        if(gamepad2.b){
            robot.launcher.idle();
        }

        if(gamepad2.x){
            robot.intake.intake();
        }



    }
}
