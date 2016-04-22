package joystickArduino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkClient {

	public static void main(String[] args) throws  IOException {
		String serverAdress = "localhost";
		Socket socket = new Socket(serverAdress, 9090);
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println(input.readLine());
	}

}
