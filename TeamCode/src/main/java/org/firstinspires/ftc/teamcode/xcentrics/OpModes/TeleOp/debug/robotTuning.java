package org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.debug;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.xcentrics.OpModes.TeleOp.LiveTeleopBase;
@TeleOp(name = "robot tuning")
public class robotTuning extends LiveTeleopBase {
    @Override
    public void on_init() {

    }

    @Override
    public void on_start() {

    }

    @Override
    public void on_stop() {

    }

    @Override
    public void on_loop() {
    robot.updateTelemetry();
    }
}
