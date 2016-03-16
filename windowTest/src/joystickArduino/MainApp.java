package joystickArduino;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class MainApp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5399019670779785323L;
	private JPanel contentPane;
	private static Controller[] con;
	private static JMenu mnDevice;
	private static JMenu mnAdd;
	private static Component[][] com;
	private static JRadioButtonMenuItem showAllControllers;
	
	/**
	 * Launch the application.
	 */

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setTitle("Pi Driver Station");
//					frame.setUndecorated(true);
					frame.setVisible(true);
//					frame.setExtendedState(frame.getExtendedState()|Frame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//Gets Controllers and components for each
				con = ControllerEnvironment.getDefaultEnvironment().getControllers();
				com = new Component[con.length][];
				mnDevice.removeAll();
				for (int i = 0; i < com.length; i++){
					if(con[i].getType() == Controller.Type.GAMEPAD || con[i].getType() == Controller.Type.KEYBOARD){
						mnDevice.add(con[i].getName());
					}
				}
		
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		
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
		
		JMenu mnSetup = new JMenu("Setup");
		menuBar.add(mnSetup);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	}
}
