package joystickArduino;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkClient {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Socket socket = new Socket(InetAddress.getLocalHost(), 1234);
		Thread.sleep(100);
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		String temp = (String) ois.readObject();
		socket.close();
		System.out.println(temp);
	}

}
