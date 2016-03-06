package windowTest;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.JComboBox;

import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;


public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3618750666860802354L;
	private static JPanel contentPane;
	private static int oldControllerComboBox;
	private static Component[][] comList;
	public static String[] serialPortList;
	private static boolean start = false;
	private static int finalController;
	private static int finalComponent;
	private static String finalComPort;
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws SerialPortException 
	 */
	public static void main(String[] args) throws InterruptedException, SerialPortException {
		Frame frame = new Frame();
		
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
		View_Data.setVisible(false);
		
		
		TextArea textArea = new TextArea();
		textArea.setBounds(0, 0, 432, 253);
		contentPane.add(textArea);
		textArea.setVisible(false);
		
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
			if(oldControllerComboBox != controllerComboBox.getSelectedIndex()){
				componentComboBox.removeAllItems();
				oldControllerComboBox = controllerComboBox.getSelectedIndex();
				for(int i = 0; i < comList[controllerComboBox.getSelectedIndex()].length; i++){
					componentComboBox.addItem(comList[controllerComboBox.getSelectedIndex()][i].getName());
				}
				
			}
			//set final values and end the loop
			if (start == true){
				finalController = controllerComboBox.getSelectedIndex();
				finalComponent = componentComboBox.getSelectedIndex();
				finalComPort = comPortComboBox.getSelectedItem().toString();
				break;
			}
			
		}
		//open port to arduino
		SerialPort port = new SerialPort(finalComPort);
		port.openPort();
		port.setParams(9600, 8, 1, 0);
		//wait while the port resets
		lblStartingSerialPort.setVisible(true);
		Thread.sleep(2000);
		
		//Frame Stuff
		lblSendingData.setVisible(true);
		View_Data.setVisible(true);
		
		
		
		
		while(true){
			con[finalController].poll();
			port.writeInt((int)(comList[finalController][finalComponent].getPollData() * 90) + 90);
			System.out.println((int)(comList[finalController][finalComponent].getPollData() * 90) + 90);
			textArea.append(String.valueOf((int)(comList[finalController][finalComponent].getPollData() * 90) + 90) + "\n");
			Thread.sleep(20);
		}
		
	}
}
