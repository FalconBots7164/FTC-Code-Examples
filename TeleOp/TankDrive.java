import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class TankDrive extends OpMode {

  DcMotor m1;
  DcMotor m2;

  //For four motor configuration, uncomment all m3/m4 code to use
  /*
  DcMotor m3;
  DcMotor m4;
  */

    @Override
    public void init() {

      m1 = hardwareMap.dcmotor.get("Motor 1");
      m2 = hardwareMap.dcmotor.get("Motor 2");
    
      // Four motor config
      /*
      m3 = hardwareMap.dcmotor.get("Motor 3");
      m4 = hardwareMap.dcmotor.get("Motor 4");
      */
      
      // If you wish to change the direction the motors are using, you can choose to change it with this method.
      // Be sure to uncomment the DcMotorSimple import statement above as well.
      /*
      m1.setDirection(DcMotorSimple.Direction.REVERSE);
      m2.setDirection(DcMotorSimple.Direction.FORWARD);
      */
  
    }

    @Override
    public void loop() {

      m1.setPower(gamepad1.right_stick_y);
      m2.setPower(gamepad1.left_stick_y);
      
      //Four motor config
      /*
      m3.setPower(gamepad1.right_stick_y);
      m4.setPower(gamepad1.left_stick_y);
      */

    }

}
