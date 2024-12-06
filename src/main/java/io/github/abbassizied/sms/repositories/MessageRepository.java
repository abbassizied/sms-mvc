package io.github.abbassizied.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import io.github.abbassizied.sms.entities.Message;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.MessageType;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
    // Query to get all messages of a specific type
    @Query("SELECT m FROM Message m WHERE m.messageType = :type")
    List<Message> findAllMessagesByType(MessageType type);
    
    // Custom query to get private messages between sender and receiver
    List<Message> findBySenderAndReceiver(User sender, User receiver);    
    
}
