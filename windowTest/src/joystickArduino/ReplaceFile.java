package joystickArduino;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ReplaceFile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReplaceFile frame = new ReplaceFile();
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
	public ReplaceFile() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 166, 104);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFileAlreadyExists = new JLabel("File Already Exists!");
		lblFileAlreadyExists.setBounds(12, 0, 124, 16);
		contentPane.add(lblFileAlreadyExists);
		
		JLabel lblReplace = new JLabel("Replace?");
		lblReplace.setBounds(41, 13, 56, 16);
		contentPane.add(lblReplace);
		
		JButton btnYes = new JButton("Yes");
		btnYes.setBounds(0, 32, 63, 25);
		contentPane.add(btnYes);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(85, 32, 63, 25);
		contentPane.add(btnNo);
	}
}
