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
        return chatMessageRepository.findBySenderAndReceiver(sender, receiver);
    }    
}
