package joystickArduino;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
		Thread.sleep(3000);
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		byte[] temp;
		while(true){
			temp = (byte[]) ois.readObject();
			System.out.println(temp[0]);
		}
	}

}
