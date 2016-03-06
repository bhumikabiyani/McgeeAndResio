package windowTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.TextArea;
import java.awt.Button;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		
		JLabel lblController = new JLabel("Controller");
		lblController.setBounds(12, 77, 56, 16);
		contentPane.add(lblController);
		
		JLabel lblComponent = new JLabel("Component");
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
			}
			
		});
	}
}
