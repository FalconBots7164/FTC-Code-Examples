import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Autonomous
public class RevIMU extends LinearOpMode {

  double globalAngle;
  DcMotor m1;
  DcMotor m2;
  BNO055IMU imu;
  Orientation lastAngles = new Orientation();

  public void resetAngle() {
    lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    globalAngle = 0;
  }
  
  public double getAngle() {
    Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    double deltaAngle = angles.firstAngle - lastAngles.firstAngle;
    
    if (deltaAngle < -180) {
      deltaAngle += 360;
    } else if (deltaAngle > 180) {
      deltaAngle -= 360;
    }
    
    globalAngle += deltaAngle;
    lastAngles = angles; 
    
    return globalAngle;
  }
  
  public void rotateRobot(double power, int degrees) {
    resetAngle();
    
    if (degrees < 0) {
      m1.setPower(power);
      m2.setPower(-power);
    } else if (degrees > 0) {
      m1.setPower(-power);
      m2.setPower(power);
    } else {
      return;
    }
    
    if (degrees < 0) {
      while (opModeIsActive && getAngle() == 0) {}
      while (opModeIsActive && getAngle() > degrees) {}
    } else {
      while (opModeIsActive && getAngle() < degrees) {}
    }
      m1.setPower(0);
      m2.setPower(0);
      resetAngle();
  }
  
  @Override
  public void runOpMode() throws InterruptedException {
    m1 = hardwareMap.dcMotor.get("Motor 1");
    m2 = hardwareMap.dcMotor.get("Motor 2");
    imu = hardwareMap.get(BNO055IMU.class, "IMU");
    
    BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
    telemetry.addData("IMU", "Initializing...");
    telemetry.update();
    parameters.mode = BNO055IMU.SensorMode.IMU;
    parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
    parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
    parameters.loggingEnabled = false;
    parameters.loggingEnabled = true;
    parameters.loggingTag = "IMU";
    imu.initialize(parameters);
    telemetry.addData("IMU", "Ready");
    telemetry.update();
        
    waitforStart();
        
    if (opModeIsActive) {
      rotateRobot(1, 90);
      rotateRobot(.5, 150);
    }
  }
}
