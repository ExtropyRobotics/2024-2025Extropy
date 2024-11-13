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
        double stickrighty = 0;
        motorLB.setDirection(DcMotorSimple.Direction.REVERSE);
        motorRB.setDirection(DcMotorSimple.Direction.REVERSE);

        while(opModeIsActive()){

         stickleftx = -gamepad1.left_stick_x;
         stickrighty = gamepad1.right_stick_y;

            motorLF.setPower(stickleftx + stickrighty);
            motorRB.setPower(stickleftx - stickrighty);
            motorLB.setPower(stickleftx + stickrighty);
            motorRF.setPower(stickleftx - stickrighty);

        }
    }

}
