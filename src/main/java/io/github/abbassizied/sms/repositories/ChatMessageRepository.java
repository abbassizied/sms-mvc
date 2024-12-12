package io.github.abbassizied.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import io.github.abbassizied.sms.entities.ChatMessage;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.ChatMessageType;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	
    // Query to get all messages of a specific type
    @Query("SELECT m FROM ChatMessage m WHERE m.messageType = :type")
    List<ChatMessage> findAllMessagesByType(ChatMessageType type);
    
    // Custom query to get private messages between sender and receiver
    List<ChatMessage> findBySenderAndReceiver(User sender, User receiver);   
}
