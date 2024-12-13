import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Suferinta2")
public class Suferinta2 extends LinearOpMode {

    DcMotor sliderFix;
    DcMotor motorRF;
    DcMotor motorRB;
    DcMotor motorLB;
    DcMotor motorLF;
    DcMotor sliderMobil;
    DcMotor motorAx;

    @Override
    public void runOpMode() throws InterruptedException{

        sliderFix = hardwareMap.get(DcMotor.class, "Control1");
        motorRF = hardwareMap.get(DcMotor.class, "Control2");
        motorRB = hardwareMap.get(DcMotor.class, "Control3");
        motorLB = hardwareMap.get(DcMotor.class, "Expansion0");
        motorLF = hardwareMap.get(DcMotor.class, "Expansion1");
        sliderMobil = hardwareMap.get(DcMotor.class, "Expansion2");
        motorAx = hardwareMap.get(DcMotor.class, "Expansion3");

        waitForStart();

        double stickleftx = 0;
        double stickrightx = 0;
        double sticklefty = 0;
        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorLF.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);


        while(opModeIsActive()){

            sticklefty = -gamepad1.left_stick_y;
            stickleftx = gamepad1.left_stick_x;
            stickrightx = gamepad1.right_stick_x;

            motorLF.setPower(sticklefty + stickleftx + stickrightx);
            motorRB.setPower(sticklefty + stickleftx - stickrightx);
            motorLB.setPower(sticklefty - stickleftx + stickrightx);
            motorRF.setPower(sticklefty - stickleftx - stickrightx);

        }
    }

}
