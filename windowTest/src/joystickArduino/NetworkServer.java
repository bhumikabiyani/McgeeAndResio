package joystickArduino;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkServer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(1234);
		Socket socket = ss.accept();
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());;
		oos.writeObject("Hello, World!");
		oos.close();
		ss.close();
	}

}
