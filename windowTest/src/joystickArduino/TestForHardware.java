package joystickArduino;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class TestForHardware {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.loadLibrary("jinput-linux");
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		System.out.println("    List of Available Controllers: ");
		for(int i = 0; i < con.length; i++){
			System.out.println(i + "  " + con[i].getName());
		}
		System.out.println("    (End of Program)");
	}

}
