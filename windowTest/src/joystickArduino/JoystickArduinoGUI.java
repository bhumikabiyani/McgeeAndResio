package joystickArduino;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;


public class JoystickArduinoGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3618750666860802354L;
	private static JPanel contentPane;
	private static int oldControllerComboBox;
	private static Component[][] comList;
	private static String[] serialPortList;
	private static boolean start = false;
	private static boolean sendData = false;
	private static boolean oldChckBx = false;
	private static boolean joystickToServo = false;
	private static int finalController;
	private static int finalComponent;
	private static String finalComPort;
	private static SerialPort port;
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws SerialPortException 
	 */
	public static void main(String[] args) throws InterruptedException, SerialPortException {
		JoystickArduinoGUI frame = new JoystickArduinoGUI();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> controllerComboBox = new JComboBox<String>();
		controllerComboBox.setBounds(12, 106, 110, 22);
		contentPane.add(controllerComboBox);
		
		JComboBox<String> componentComboBox = new JComboBox<String>();
		componentComboBox.setBounds(158, 106, 110, 22);
		contentPane.add(componentComboBox);
		
		JComboBox<String> comPortComboBox = new JComboBox<String>();
		comPortComboBox.setBounds(294, 106, 56, 22);
		contentPane.add(comPortComboBox);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(307, 168, 97, 25);
		contentPane.add(btnStart);
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				start = true;
			}
			
		});
		
		JLabel lblController = new JLabel("Controller");
		lblController.setBounds(12, 77, 56, 16);
		contentPane.add(lblController);
		
		JLabel lblComponent = new JLabel("Axis");
		lblComponent.setBounds(158, 77, 74, 16);
		contentPane.add(lblComponent);
		
		JLabel lblComPort = new JLabel("COM port");
		lblComPort.setBounds(294, 77, 56, 16);
		contentPane.add(lblComPort);
		
		JLabel lblStartingSerialPort = new JLabel("Starting Serial Port...");
		lblStartingSerialPort.setBounds(12, 172, 147, 16);
		contentPane.add(lblStartingSerialPort);
		lblStartingSerialPort.setVisible(false);
		
		JLabel lblSendingData = new JLabel("Sending Data!");
		lblSendingData.setBounds(12, 187, 97, 16);
		contentPane.add(lblSendingData);
		lblSendingData.setVisible(false);
		
		JButton View_Data = new JButton("View Data");
		View_Data.setBounds(12, 209, 110, 24);
		contentPane.add(View_Data);
		
		JTextPane errorStarting = new JTextPane();
		errorStarting.setBackground(SystemColor.control);
		errorStarting.setEditable(false);
		errorStarting.setFont(new Font("Calibri", Font.PLAIN, 13));
		errorStarting.setForeground(Color.RED);
		errorStarting.setText("Error starting. Please make sure all fields are filled and all devices are plugged in.");
		errorStarting.setBounds(141, 148, 154, 92);
		contentPane.add(errorStarting);
		errorStarting.setVisible(false);
		
		JCheckBox chckbxTestJoystick = new JCheckBox("Test Joystick");
		chckbxTestJoystick.setBounds(294, 137, 113, 25);
		contentPane.add(chckbxTestJoystick);
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 1, 432, 252);
		contentPane.add(textArea);
		textArea.setVisible(false);
		
		View_Data.setVisible(false);
		
		View_Data.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				textArea.setVisible(true);
				
				controllerComboBox.setVisible(false);
				componentComboBox.setVisible(false);
				comPortComboBox.setVisible(false);
				btnStart.setVisible(false);
				lblController.setVisible(false);
				lblComponent.setVisible(false);
				lblComPort.setVisible(false);
				lblStartingSerialPort.setVisible(false);
				lblSendingData.setVisible(false);
				View_Data.setVisible(false);
				lblSendingData.setVisible(false);
				View_Data.setVisible(false);
				errorStarting.setVisible(false);
				chckbxTestJoystick.setVisible(false);
			}
			
		});
		
		
		frame.setVisible(true);	
		
		//End Frame Settings
		
		//Find controllers
		Controller[] con = ControllerEnvironment.getDefaultEnvironment().getControllers();
		//get components of each controller
		comList = new Component[con.length][];
		for(int i = 0; i < con.length; i++){
			controllerComboBox.addItem(con[i].getName());
			comList[i] = con[i].getComponents();
		}
		
		//Begin Serial Port Search
		serialPortList = SerialPortList.getPortNames();
		for(int i = 0; i < serialPortList.length; i++){
			comPortComboBox.addItem(serialPortList[i]);
		}
		//run an infinite loop to change the contents of the component combo box based on the controller selected.
		while(true){
			//if just testing joystick is enabled, make the combobox and label disappear.
			if(chckbxTestJoystick.isSelected() && oldChckBx != chckbxTestJoystick.isSelected()){
				comPortComboBox.setVisible(false);
				lblComPort.setVisible(false);
				oldChckBx = chckbxTestJoystick.isSelected();
			}else if(chckbxTestJoystick.isSelected() == false && oldChckBx != chckbxTestJoystick.isSelected()){
				comPortComboBox.setVisible(true);
				lblComPort.setVisible(true);
				oldChckBx = chckbxTestJoystick.isSelected();
			}
			
			if(oldControllerComboBox != controllerComboBox.getSelectedIndex()){
				componentComboBox.removeAllItems();
				oldControllerComboBox = controllerComboBox.getSelectedIndex();
				for(int i = 0; i < comList[controllerComboBox.getSelectedIndex()].length; i++){
					componentComboBox.addItem(comList[controllerComboBox.getSelectedIndex()][i].getName());
				}
				
			}
			//set final values and end the loop
			if (start == true && chckbxTestJoystick.isSelected() == false){
				if(controllerComboBox.getSelectedItem() == null || componentComboBox.getSelectedItem() == null || comPortComboBox.getSelectedItem() == null || con[controllerComboBox.getSelectedIndex()].poll() == false){
					errorStarting.setVisible(true);
					start = false;
				}else{
					errorStarting.setVisible(false);
					joystickToServo = true;
					sendData = true;
				}
			}else if (start == true && chckbxTestJoystick.isSelected() == true){
				if(controllerComboBox.getSelectedItem() == null || componentComboBox.getSelectedItem() == null || con[controllerComboBox.getSelectedIndex()].poll() == false){
					errorStarting.setVisible(true);
					start = false;
				}else{
					errorStarting.setVisible(false);
					sendData = true;
				}
			}
			
			if (sendData == true){
				
				finalController = controllerComboBox.getSelectedIndex();
				finalComponent = componentComboBox.getSelectedIndex();
				if(joystickToServo){
					finalComPort = comPortComboBox.getSelectedItem().toString();
				}
				break;
			}
			
		}
		//open port to arduino
		if(joystickToServo){
			port = new SerialPort(finalComPort);
			port.openPort();
			port.setParams(9600, 8, 1, 0);
			//wait while the port resets
			lblStartingSerialPort.setVisible(true);
			Thread.sleep(2000);
			lblSendingData.setVisible(true);
		}
		//Frame Stuff
		
		View_Data.setVisible(true);
		
		
		while(true){
			con[finalController].poll();
			if(joystickToServo){
				port.writeInt((int)(comList[finalController][finalComponent].getPollData() * 90) + 90);
			}
			System.out.println((int)(comList[finalController][finalComponent].getPollData() * 90) + 90);
			if(textArea.isVisible()){
				textArea.append(String.valueOf((int)(comList[finalController][finalComponent].getPollData() * 90) + 90) + "\n");
			}
			Thread.sleep(20);
		}
		
	}
}
