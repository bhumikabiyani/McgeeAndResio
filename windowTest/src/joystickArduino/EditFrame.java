package joystickArduino;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

public class EditFrame extends JFrame {

	private JPanel contentPane;
	private static JMenu mnDevice;
	private static JMenu mnAdd;
	private static JRadioButtonMenuItem showAllControllers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFrame frame = new EditFrame();
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
	public EditFrame() {
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
		
		
		mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);
		
		mnDevice = new JMenu("Device");
		
		showAllControllers = new JRadioButtonMenuItem("Show All Controllers");
		
		mnAdd.add(mnDevice);
		
		mnAdd.add(showAllControllers);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmMaximize = new JMenuItem("Maximize");
		mnView.add(mntmMaximize);
		
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
	}

}
