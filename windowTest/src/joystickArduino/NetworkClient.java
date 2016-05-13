package joystickArduino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.InputStream;

public class NetworkClient {

	public static void main(String[] args) throws  IOException, ClassNotFoundException {
		String serverAdress = "localhost";
		Socket socket = new Socket(serverAdress, 9090);
		ObjectInputStream ois;
		ois = new ObjectInputStream(socket.getInputStream());
		byte[] temp = (byte[]) ois.readObject();
		System.out.println(temp[0]);
	}

}
