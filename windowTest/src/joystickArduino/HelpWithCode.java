package joystickArduino;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import net.java.games.input.Component;
import net.java.games.input.Controller;

public class HelpWithCode {
	private static FileOutputStream fos;
	private static ObjectOutputStream oos;

	private static ObjectInputStream ois;
	private static FileInputStream fis;
	private static ArrayList<AddedComponent> addedComponents = new ArrayList<AddedComponent>();
	
	protected static void saveComponents(String path) {
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(addedComponents);
			fos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Saved To File: " + path);
	}

	protected static void openComponents(String path) {
		try{
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
			addedComponents = (ArrayList<AddedComponent>) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	protected static class AddedComponent {
		Component component;
		Controller controller;
		boolean isButton;
		boolean isAxis;

		void setComponent(Component c) {
			component = c;
		}

		Component getComponent() {
			return component;
		}

		void setDevice(Controller c) {
			controller = c;
		}

		Controller getDevice() {
			return controller;
		}

		void setAsButton() {
			isButton = true;
			isAxis = false;
		}

		void setAsAxis() {
			isButton = false;
			isAxis = true;
		}
	}
}
