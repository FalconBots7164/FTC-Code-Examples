import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class Servos extends OpMode {
  Servo servo1;
  Servo servo2;
  boolean toggle = false;
  boolean lock = false;
  
  public void init() {
    servo1 = hardwareMap.servo.get("Servo 1");
    servo2 = hardwareMap.servor.get("Servo 2");
  }
  
  public void loop() {
    //hold
    if (gamepad2.y) {
      servo1.setPosition(1);
    } else {
      servo1.setPosition(0);
    }
    
    //toggle
    if (gamepad1.a && !toggle && !lock) {
      servo2.setPosition(.5);
      toggle = true;
      lock = true;
    } else if (gamepad1.a && toggle && !lock) {
      servo2.setPosition(0);
      toggle = false; 
      lock = true;
    } else if (!gamepad1.a && lock) {
      lock = false;
    }
  }
}
