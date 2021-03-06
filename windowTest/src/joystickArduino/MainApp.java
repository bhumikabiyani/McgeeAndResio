package joystickArduino;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author McGee and Daniel
 */

//TODO *****************SOME OF THIS NEEDS TO BE SPLIT INTO CLASSES AND METHODS!*********************************
//TODO cleanup

//--------------------------In General, this class is a test. It WILL NOT BE IN THE FINAL-----------
//--------------------------RELEASE!! Methods labeled with KEEP will be in the final release.-------

//NOTE* Make sure methods with the label KEEP are properly commented. (This means you, McGee!)

public class MainApp extends JFrame implements Runnable{

	private static final long serialVersionUID = 5399019670779785323L;
	
	//Main Frame Declarations-------------------------------------
	public static MainApp frame_maximized;
	public static MainApp frame_minimized;
	//JFrame components-------------------------------------------
	private static JPanel contentPane;
	private static JPanel panel;
	private static JMenu mnDevice;
	private static JMenu mnAdd;
	private static JMenuItem mntmMaximize;
	private static JMenuItem mntmMinimize;
	private static JRadioButtonMenuItem showAllControllers;
	private static JComboBox<String> comboBox;
	
	//General variables-------------------------------------------
	private static boolean addItem = false;
	private static String maxedState = "minimized";
	private static String deviceSelected;
	private static int deviceSelectedIndex;
	private static boolean panelRunning = false;
	
	//JInput related variables------------------------------------
	private static Controller[] con;
	private static Component[][] com;

	/**
	 * Launch the application.
	 */


	
	public static void addDevice(String Label){
		
	}
	
	
	public static void main(String[] args) {
		(new Thread(new MainApp())).start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Starting the Frame
					frame_minimized = new MainApp();
					frame_minimized.setTitle("Pi Driver Station");
					frame_minimized.setVisible(true);
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
						JMenuItem controllerItem = new JMenuItem();
						controllerItem.setText(con[i].getName());
						mnDevice.add(controllerItem);
						//adds an action listener for each item in the list
						//TODO set a string to the name of the selected item so that it can
						//be transferred to displaying the components in the comboBox
						controllerItem.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								addItemPanel(true);

							}
							
						});
					}
				}
				for(int i = 0; i < con.length; i++){
					com[i] = con[i].getComponents();
				}
			//End of main thread. New thread starts (at the bottom) with while loop 
			//that checks for frame-related things.
			//TODO Create thread that will continually send information over wifi.
		
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		//------------------------| Begin the Main Frame |---------------------------
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//TODO create file that can be loaded with different channels and devices
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
				
			}
			
		});
		
		//TODO implement action listener for component menus
		
		//TODO ip camera implementation 
		
		//add devices that will go to channels
		mnAdd = new JMenu("Add");
		menuBar.add(mnAdd);
		
		mnDevice = new JMenu("Device");
		
		showAllControllers = new JRadioButtonMenuItem("Show All Controllers");
		showAllControllers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//keeps tab popped up when clicked
				mnAdd.doClick();
				//shows all controllers or those just with the tags of gamepad or keyboard
				if(showAllControllers.isSelected()){
					mnDevice.removeAll();
					for (int i = 0; i < con.length; i++){
						JMenuItem controllerItem = new JMenuItem();
						controllerItem.setText(con[i].getName());
						mnDevice.add(controllerItem);
						//adds an action listener for each item in the list
						//TODO set a string to the name of the selected item so that it can
						//be transferred to displaying the components in the comboBox
						controllerItem.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								addItemPanel(true);

							}
							
						});
					}
				}else {
					mnDevice.removeAll();
					for(int i = 0; i < con.length; i++){
						if(con[i].getType() == Controller.Type.GAMEPAD || con[i].getType() == Controller.Type.KEYBOARD){
							JMenuItem controllerItem = new JMenuItem();
							controllerItem.setText(con[i].getName());
							mnDevice.add(controllerItem);
							controllerItem.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent arg0) {
									deviceSelected = controllerItem.getName();
									addItemPanel(true);
									

								}
								
							});
						}
					}
					
				}
			}
			
		});
		
		mnAdd.add(mnDevice);
		
		mnAdd.add(showAllControllers);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		mntmMinimize = new JMenuItem("Minimize");
		mnView.add(mntmMinimize);
		mntmMinimize.setVisible(false);
		mntmMinimize.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//   same as below
				frame_maximized.dispose();
				frame_minimized = new MainApp();
				frame_minimized.setTitle("Pi Driver Station");
				frame_minimized.setUndecorated(false);
				frame_minimized.setVisible(true);
				frame_minimized.setExtendedState(Frame.NORMAL);
				maxedState = "minimized";
			}
			
		});
		
		mntmMaximize = new JMenuItem("Maximize");
		mnView.add(mntmMaximize);
		mntmMaximize.addActionListener(new ActionListener(){
			//action listener that disposes of the current frame and creates a new
			//one with an undecorated frame, as it can only be changed on startup
			public void actionPerformed(ActionEvent arg0) {
				frame_minimized.dispose();
				frame_maximized = new MainApp();
				frame_maximized.setTitle("Pi Driver Station");
				frame_maximized.setUndecorated(true);
				frame_maximized.setVisible(true);
				frame_maximized.setExtendedState(Frame.MAXIMIZED_BOTH);
				maxedState = "maximized";
			}
			
		});
		
		
		//TODO add preferences like customization(color and stuff) and wireless settings
		//as well as adding channels that will be mapped to components added
		JMenu mnSetup = new JMenu("Setup");
		menuBar.add(mnSetup);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//panel that houses the comboBox and buttons to add either an axis or button
		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 139, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		//TODO add listing of components based on the selection in Add tab
		//will house the list of components
		comboBox = new JComboBox<String>();
		comboBox.setBounds(0, 0, 139, 22);
		panel.add(comboBox);
		
		JButton btnAddAsButton = new JButton("Add As Button");
		btnAddAsButton.setBounds(0, 25, 127, 25);
		panel.add(btnAddAsButton);
		btnAddAsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				addItemPanel(false);
			}
			
		});
		//TODO add component implementation to add buttons
		//right now, it only makes the add item box disappear
		JButton btnAddAsAxis = new JButton("Add As Axis");
		btnAddAsAxis.setBounds(0, 50, 127, 25);
		panel.add(btnAddAsAxis);
		btnAddAsAxis.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				addItemPanel(false);
			}
			
		});
		
		//TODO add Help tab for displaying info on how to use
		
		//TODO make label visible....
		//JLabel to store the components being used
		JLabel components = new JLabel();
		//TODO teach how to use array lists/add component names to this list
		//**NOTE** we are using array lists because this list will change sizes
		//also when adding items to this list, always remember to end a name with \n
		ArrayList<String> listComponents = new ArrayList<String>();
		//concats all devices
		String displayComponents = new String("Devices:\n");
		//this adds all of the strings together
		for(int i = 0; i < listComponents.size(); i++)
			displayComponents.concat(listComponents.get(i));
		components.setText(displayComponents);
	}


	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true){
			
			if(maxedState == "maximized"){
				mntmMaximize.setVisible(false);
				mntmMinimize.setVisible(true);
			}else if(maxedState == "minimized"){
				mntmMinimize.setVisible(false);
				mntmMaximize.setVisible(true);
				
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//KEEP THIS
	public static boolean addItemPanel(boolean state){
		if(state == true){
			panel.setVisible(true);
			
			//get index of device selected
			for (int i = 0; i < con.length; i++){
				if(con[i].getName() == deviceSelected){
					deviceSelectedIndex = i;
					break;
				}
			}
			//clear comboBox if it has items
			if(comboBox.getItemCount() > 0){
				comboBox.removeAllItems();
			}
			
			
			//populate ComboBox
			for(int i = 0; i < com[deviceSelectedIndex].length; i++){
				comboBox.addItem(com[deviceSelectedIndex][i].getName());
			}
			
			
			return true;
		}else{
			panel.setVisible(false);
			
			return false;
		}
	}
}
