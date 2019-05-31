package boundary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.StationControl;
import control.UsageControl;
import control.UserControl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserControl.read();
					StationControl.read();
					UsageControl.read();
					Welcome welFrame = new Welcome();
					welFrame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Welcome() {
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnScooterStation = new JButton("Scooter Station");
		btnScooterStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new StationPage();
				UserControl.write();
				StationControl.write();
				UsageControl.write();
			}
		});
		btnScooterStation.setBounds(25, 110, 180, 41);
		contentPane.add(btnScooterStation);
		
		JButton btnManagementSystem = new JButton("Management System");
		btnManagementSystem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManagementSys();
				UserControl.write();
				StationControl.write();
				UsageControl.write();
			}
		});
		btnManagementSystem.setBounds(225, 110, 180, 41);
		contentPane.add(btnManagementSystem);
	}


}
