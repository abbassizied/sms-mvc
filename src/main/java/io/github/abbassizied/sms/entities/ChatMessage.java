package io.github.abbassizied.sms.entities;

import jakarta.persistence.*; 

import io.github.abbassizied.sms.enums.ChatMessageType;

@Entity
@Table(name = "chat_messages")
public class ChatMessage extends Message {
 
	@Enumerated(EnumType.STRING)
	@Column(name = "message_type")	
	private ChatMessageType messageType;

	@ManyToOne
	@JoinColumn(name = "from_user_id", nullable = true) // to use email 
	private User sender;

	@ManyToOne
	@JoinColumn(name = "to_user_id", nullable = true) // Nullable for broadcast or public messages
	private User receiver;

    // Method to return the sender's full name (you can adjust the name format if needed)
    public String getSenderName() {
        return sender != null ? sender.getFirstName() + " " + sender.getLastName() : "Unknown Sender";
    }	
 
	// Default constructor
	public ChatMessage() {
		// No-arg constructor
		super();
	}
 
	// Constructor for broadcast (public) messages
	public ChatMessage(User sender, String messageContent) {
		super();
		this.sender = sender;
		this.messageContent = messageContent;
		this.messageType = ChatMessageType.PUBLIC_CHAT;
	}

	// Constructor for private messages
	public ChatMessage(User sender, String messageContent, User receiver) {
		super();
		this.sender = sender; 
		this.receiver = receiver; 
		this.messageType = ChatMessageType.PRIVATE_CHAT;
	} 
 
	public User getSender() { return sender; } 
	public void setSender(User sender) { this.sender = sender; }

	public void setReceiver(User receiver) { this.receiver = receiver; } 
	public User getReceiver() { return receiver; }

	public ChatMessageType getMessageType() { return messageType; } 
	public void setMessageType(ChatMessageType messageType) { this.messageType = messageType; }

	// toString for easier logging/debugging

}
