package org.firstinspires.ftc.teamcode.xcentrics.components.live;

import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.D;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.F;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.I;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.P;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.idleSpeed;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.launchSpeed;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.targetSpeed;
import static org.firstinspires.ftc.teamcode.xcentrics.components.live.LauncherConfig.tolerance;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.xcentrics.components.Component;
import org.firstinspires.ftc.teamcode.xcentrics.robots.Robot;
import org.firstinspires.ftc.teamcode.xcentrics.util.CRServoQUS;
import org.firstinspires.ftc.teamcode.xcentrics.util.DcMotorQUS;
@Configurable
class LauncherConfig{
    public static double P = 0;
    public static double I = 0;
    public static double D = 0;
    public static double F = 0;
    public static int targetSpeed = 0;
    public static int tolerance = 50;
    public static int idleSpeed = 100;
    public static int launchSpeed = 1000;
}
public class Launcher extends Component{
    /// Motors ///
    public DcMotorQUS launcher;
    /// Servos ///
    public CRServoQUS feeder;

    halt halt = new halt();
    public static boolean canLaunch = false,launch = false;
    {
        name ="launcher";
    }

    public Launcher(Robot robot){
        super(robot);
    }
    @Override
    public void registerHardware(HardwareMap hardwareMap){
        super.registerHardware(hardwareMap);

        /// Motors ///
        launcher = new DcMotorQUS(hardwareMap.get(DcMotorEx.class,"fly"));

        /// Servos ///
        feeder = new CRServoQUS(hardwareMap.get(CRServo.class,"feeder"));
    }

    @Override
    public void update(OpMode opMode) {
        super.update(opMode);
        //update pidf coefs for flywhee;
        launcher.motor.setVelocityPIDFCoefficients(P, I, D, F);
        //update target velocity
        launcher.queue_velocity(targetSpeed);
        launcher.update();
        //update canLaunch
        canLaunch = (targetSpeed >= targetSpeed + tolerance && targetSpeed <= targetSpeed - tolerance);
        //launch
        if (launch && canLaunch) {
            launch();
        }
    }


    @Override
    public void startup(){
        super.startup();
        launcher.motor.setDirection(DcMotorSimple.Direction.FORWARD);
        launcher.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void shutdown(){
        super.shutdown();
        launcher.queue_velocity(0);
        feeder.queue_power(0);
    }
    public void launch(){
        feeder.queue_power(1);
        halt.halt(0.5);
        feeder.queue_power(0);
        launch = false;
    }

    public void setSpeed(int speed){
        targetSpeed = speed;
    }

    public boolean canLaunch(){
        return canLaunch;
    }
    public void spinUp(){
        setSpeed(launchSpeed);
    }
    public void idle(){
        setSpeed(idleSpeed);
    }
    public void updateTelemetry(Telemetry telemetry){
        super.updateTelemetry(telemetry);
        addData("Launcher speed: ",launcher.motor.getVelocity());
        addData("Feeder speed: ",feeder.servo.getPower());
        addData("Can launch: ",canLaunch);
        addData("Launch: ",launch);
    }
}
