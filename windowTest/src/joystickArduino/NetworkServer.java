package joystickArduino;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(1234);
		Socket socket = ss.accept();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.flush();
		oos.writeObject("Hello, World!");
		ss.close();
	}

}
