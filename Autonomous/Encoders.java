@Autonomous
public class Encoders extends LinearOpMode {
  DcMotor m1;
  DcMotor m2;
  
  public void moveRobot(double power, int ticks) {
    if(opModeIsActive) {
      int m1Target = m1.getCurrentPosition() + ticks;
      int m2Target = m2.getCurrentPosition() + ticks;
      m1.setTargetPosition(m1Target);
      m2.setTargetPosition(m2Target);
      m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      m1.setPower(power);
      m2.setPower(power);
      m1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      m2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
      while(opModeIsActive || m1.isBusy() || m2.isBusy()) {
        telemetry.addData("Motor 1 Ticks", m1.getCurrentPosition());
        telemetry.addData("Motor 2 Ticks", m2.getCurrentPosition());
        telemetry.update();
      }
    }
  }


  public void runOpMode() throws InterruptedException {
    m1 = hardwareMap.dcMotor.get("Motor 1");
    m2 = hardwareMap.dcMotor.get("Motor 2");
    telemetry.addData("Status", "Resetting Encoders");
    telemetry.update();
    m1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    m2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    telemetry.addData("Status", "Encoders Ready");
    telemetry.update();
    waitForStart();
    if(opModeIsActive) {
      moveForward(.5, 1000);
      moveForward(1, 2500);
      stop();
    }
  }
}
