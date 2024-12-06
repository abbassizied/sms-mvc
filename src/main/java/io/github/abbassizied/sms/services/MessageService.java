package io.github.abbassizied.sms.services;

import io.github.abbassizied.sms.entities.Message;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.MessageType;
import io.github.abbassizied.sms.repositories.MessageRepository;

import java.util.List;

import org.springframework.stereotype.Service;
  
@Service
public class MessageService {
    private final MessageRepository messageRepository;


    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Create a new message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
    
    // Retrieve all messages by message type (Reusable method)
    public List<Message> getMessagesByType(MessageType messageType) {
        return messageRepository.findAllMessagesByType(messageType);
    }
 
    // Retrieve all private messages between two users
    public List<Message> getPrivateMessages(User sender, User receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }    
}
