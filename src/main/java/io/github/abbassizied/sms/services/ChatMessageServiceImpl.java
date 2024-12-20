package io.github.abbassizied.sms.services;

import io.github.abbassizied.sms.entities.ChatMessage;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.ChatMessageType;
import io.github.abbassizied.sms.repositories.ChatMessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
  
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	
    @Autowired
    private ChatMessageRepository chatMessageRepository;
 
    @Override
    public ChatMessage saveMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }
 
    @Override
    public List<ChatMessage> getMessagesByType(ChatMessageType messageType) {
        return chatMessageRepository.findAllMessagesByType(messageType);
    }
 
    @Override
    public List<ChatMessage> getPrivateMessages(User sender, User receiver) {
        System.out.println("Sender: " + sender.getEmail());
        System.out.println("Receiver: " + receiver.getEmail());
        // This query retrieves messages between two users, regardless of who sent the message.
        // It checks for messages where 'sender' is the current user and 'receiver' is the recipient,
        // or vice versa (to include messages sent by either user in the conversation).        
        return chatMessageRepository.findBySenderAndReceiverOrSenderAndReceiver(sender, receiver, receiver, sender);
    }    
}
