package joystickArduino;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JList;

public class FrameEdit extends JFrame {
	private static Controller[] con;
	private static JMenu mnDevice;
	private static JMenu mnAdd;
	private static Component[][] com;
	private static JRadioButtonMenuItem showAllControllers;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setTitle("Pi Driver Station");
//					frame.setUndecorated(true);
					frame.setVisible(true);
//					frame.setExtendedState(frame.getExtendedState()|Frame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
public FrameEdit() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
				
			}
			
		});
		
		
		mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);
		
		mnDevice = new JMenu("Device");
		
		showAllControllers = new JRadioButtonMenuItem("Show All Controllers");
		showAllControllers.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mnAdd.doClick();
				if(showAllControllers.isSelected()){
					mnDevice.removeAll();
					for(int i = 0; i < con.length; i++){
						mnDevice.add(con[i].getName());
					}
				}else {
					mnDevice.removeAll();
					for(int i = 0; i < con.length; i++){
						if(con[i].getType() == Controller.Type.GAMEPAD || con[i].getType() == Controller.Type.KEYBOARD){
							mnDevice.add(con[i].getName());
						}
						
					}
				}
			}
			
		});
		
		mnAdd.add(mnDevice);
		
		mnAdd.add(showAllControllers);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmMaximize = new JMenuItem("Maximize");
		mnView.add(mntmMaximize);
		mntmMaximize.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		
		JMenu mnSetup = new JMenu("Setup");
		menuBar.add(mnSetup);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 139, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 0, 139, 22);
		panel.add(comboBox);
		
		JButton btnAddAsButton = new JButton("Add As Button");
		btnAddAsButton.setBounds(0, 25, 127, 25);
		panel.add(btnAddAsButton);
		
		JButton btnAddAsAxis = new JButton("Add As Axis");
		btnAddAsAxis.setBounds(0, 50, 127, 25);
		panel.add(btnAddAsAxis);
		
		JPanel componentPanel = new JPanel();
		componentPanel.setBackground(Color.LIGHT_GRAY);
		componentPanel.setBounds(173, 0, 259, 227);
		contentPane.add(componentPanel);
		componentPanel.setLayout(null);
		
		JList buttonList = new JList();
		buttonList.setBounds(12, 13, 116, 201);
		componentPanel.add(buttonList);
		
		JList axisList = new JList();
		axisList.setBounds(129, 13, 118, 201);
		componentPanel.add(axisList);
		
		
	}
}
