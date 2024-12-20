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
    
    // Custom query to retrieve messages between two users, regardless of who sent the message.
    // It checks for messages where 'sender' is the current user and 'receiver' is the recipient,
    // or vice versa (to include messages sent by either user in the conversation).
    List<ChatMessage> findBySenderAndReceiverOrSenderAndReceiver(User sender1, User receiver1, User sender2, User receiver2);
}
