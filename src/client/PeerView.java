package client;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class PeerView {

	protected JFrame frmChat;
	private JTextField txtYourAlias;
	private JTextField txtRecipientAlias;
	private JTextField txtMessage;
	private JTextArea txtArea;
	private JButton btnRegister,
					btnSend;
	
	private PeerController peer;

	public PeerView() {
		registerComponents();
	}
	
	protected void printMessage(String message) {
		txtArea.append(message + "\n");
		txtArea.setCaretPosition(txtArea.getText().length());
	}
	
	private void instantiatePeer() {
		try {
			peer = new PeerController(txtYourAlias.getText());
			
			peer.setView(this);
			
			txtMessage.setVisible(true);
			btnSend.setVisible(true);
			txtArea.setVisible(true);
		} catch (RemoteException e1) { }
	}
	
	private void registerComponents() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.setBounds(100, 100, 466, 424);
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().setLayout(null);
		
		JLabel lblYourAlias = new JLabel("Your alias:");
		lblYourAlias.setBounds(12, 13, 88, 16);
		frmChat.getContentPane().add(lblYourAlias);
		
		JLabel lblRecipientAlias = new JLabel("Recipient alias:");
		lblRecipientAlias.setBounds(12, 42, 88, 16);
		frmChat.getContentPane().add(lblRecipientAlias);
		
		txtYourAlias = new JTextField();
		txtYourAlias.setBounds(119, 10, 218, 22);
		frmChat.getContentPane().add(txtYourAlias);
		txtYourAlias.setColumns(10);
		
		txtRecipientAlias = new JTextField();
		txtRecipientAlias.setBounds(112, 39, 225, 22);
		frmChat.getContentPane().add(txtRecipientAlias);
		txtRecipientAlias.setColumns(10);
		
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setBounds(12, 107, 423, 263);
		frmChat.getContentPane().add(txtArea);
		
		txtMessage = new JTextField();
		txtMessage.setBounds(12, 71, 325, 22);
		frmChat.getContentPane().add(txtMessage);
		txtMessage.setColumns(10);
		
		btnSend = new JButton("Send");
		
		btnSend.setBounds(350, 70, 85, 25);
		frmChat.getContentPane().add(btnSend);
		
		btnRegister = new JButton("Register");
		
		btnRegister.setBounds(350, 9, 85, 49);
		frmChat.getContentPane().add(btnRegister);
		
		txtMessage.setVisible(false);
		btnSend.setVisible(false);
		txtArea.setVisible(false);
		
		this.addActionListeners();
	}
	
	private void addActionListeners() {
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtMessage.getText().isEmpty()) return;
				
	            try {
	            	printMessage(peer.getAlias() + ": " + txtMessage.getText());
	            	
					peer.send(txtRecipientAlias.getText(), txtMessage.getText());
	            	
	            	txtMessage.setText("");
				} catch (RemoteException e1) { }
			}
		});
		
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				instantiatePeer();
			}
		});
	}
}
