package io.github.abbassizied.sms.entities;

import jakarta.persistence.*; 

import io.github.abbassizied.sms.enums.MessageType;

@Entity
@Table(name = "messages")
public class Message extends BaseEntity{
 
	@Column(name = "message_content")
	private String messageContent;

	@Enumerated(EnumType.STRING)
	@Column(name = "message_type")	
	private MessageType messageType;

	@ManyToOne
	@JoinColumn(name = "from_user_id")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "to_user_id", nullable = true) // Nullable for broadcast or public messages
	private User receiver;

    // Method to return the sender's full name (you can adjust the name format if needed)
    public String getSenderName() {
        return sender != null ? sender.getFirstName() + " " + sender.getLastName() : "Unknown Sender";
    }	
	
	
	// Default constructor
	public Message() {
		// No-arg constructor
		super();
	}

	// Constructor for broadcast (public) messages
	public Message(User sender, String messageContent) {
		this.sender = sender;
		this.messageContent = messageContent; 
		this.messageType = MessageType.PUBLIC_CHAT;
	}

	// Constructor for private messages
	public Message(User sender, String messageContent, User receiver) {
		this.sender = sender;
		this.messageContent = messageContent;
		this.receiver = receiver; 
		this.messageType = MessageType.PRIVATE_CHAT;
	} 

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
  
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getReceiver() {
		return receiver;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	 
	// toString for easier logging/debugging
	@Override
	public String toString() {
		return "Message [messageContent=" + messageContent + ", messageType=" + messageType + ", sender=" + sender
				+ ", receiver=" + receiver + ", id=" + id + ", createdAt=" + createdAt + "]";
	} 
}
