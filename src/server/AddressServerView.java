package server;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddressServerView {

	protected JFrame frmAddressServer;
	
	private JButton btnKill;

	public AddressServerView() {
		initializeComponents();
	}
	
	private void initializeComponents() {
		frmAddressServer = new JFrame();
		frmAddressServer.setResizable(false);
		frmAddressServer.setTitle("Address Server");
		frmAddressServer.setBounds(100, 100, 183, 147);
		frmAddressServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddressServer.getContentPane().setLayout(null);
		
		JLabel lblServerIsStarted = new JLabel("Server is started.");
		lblServerIsStarted.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblServerIsStarted.setBounds(12, 13, 257, 24);
		frmAddressServer.getContentPane().add(lblServerIsStarted);
		
		this.btnKill = new JButton("KILL address server");
		
		this.addEventListeners();
		
		btnKill.setBounds(12, 50, 154, 46);
		frmAddressServer.getContentPane().add(btnKill);
	}
	
	private void addEventListeners() {
		btnKill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
