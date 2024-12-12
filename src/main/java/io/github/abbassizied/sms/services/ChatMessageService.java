package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.ChatMessage;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.ChatMessageType;

public interface ChatMessageService {
	
    // Create a new message
	public ChatMessage saveMessage(ChatMessage chatMessage);	
	
	 // Retrieve all messages by message type (Reusable method)
	public List<ChatMessage> getMessagesByType(ChatMessageType messageType);
	
	// Retrieve all private messages between two users
	public List<ChatMessage> getPrivateMessages(User sender, User receiver); 
}
