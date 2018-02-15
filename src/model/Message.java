package model;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String message;
	private Peer sender, recipient;
	
	public Message(String message, Peer sender, Peer recipient) {
		this.message = message;
		this.sender = sender;
		this.recipient = recipient;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Peer getSender() {
		return sender;
	}
	
	public Peer getRecipient() {
		return recipient;
	}
}
