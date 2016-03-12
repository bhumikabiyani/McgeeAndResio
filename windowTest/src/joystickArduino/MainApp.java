package joystickArduino;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;


public class MainApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5399019670779785323L;
	private JPanel contentPane;
	private static Controller[] con;
	private static JMenu mnDevice;
	private static JMenuItem[] comList;
	private static Component[][] com;
	private static int comListNum = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setTitle("Pi Driver Station");
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState()|Frame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//Gets Controllers and components for each
				con = ControllerEnvironment.getDefaultEnvironment().getControllers();
				com = new Component[con.length][];
				
				for(int i = 0; i < con.length; i++){
					mnDevice.add(con[i].getName());
					com[i] = con[i].getComponents();
					comListNum += com[i].length;
				}
				comList = new JMenuItem[comListNum];
				
				//to traverse the entire com list
				for(int i =0 ; i < com.length; i++){
					for(int j = 0; j < com[i].length; j++){
						//this will access all items in the second part of the 2d array
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
		
		
		JMenu mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);
		
		mnDevice = new JMenu("Device");
		
		
		mnAdd.add(mnDevice);
		
		JMenu mnSetup = new JMenu("Setup");
		menuBar.add(mnSetup);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	}
}
