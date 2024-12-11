package io.github.abbassizied.sms.controllers;
 
import io.github.abbassizied.sms.entities.Message;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.MessageType;
import io.github.abbassizied.sms.services.MessageService;
import io.github.abbassizied.sms.services.UserService;
 
import org.springframework.messaging.handler.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List; 

@Controller
public class ChatController {

	private final MessageService messageService;
	private final UserService userService;

    public ChatController(MessageService messageService, UserService userService) {
		this.messageService = messageService;
		this.userService = userService;
	}

    
	@GetMapping("/public-chat")
    public String publicChat(Model model, Authentication authentication) {
		
		List<Message> messages = messageService.getMessagesByType(MessageType.PUBLIC_CHAT); 
		// System.out.println(messages);
		
		List<User> users = userService.getAllUsers();
		// System.out.println(users); 
		
	    // Get the current authenticated user
	    User currentUser = userService.findByEmail(authentication.getName());
	
		model.addAttribute("messages", messages);
		model.addAttribute("users", users);
		model.addAttribute("username", authentication.getName());
	    model.addAttribute("currentUser", currentUser);
 
		// System.out.println("********************************************************");
		// System.out.println(authentication.getName());
		// System.out.println("********************************************************");
 
	    return "chat/public-chat"; 
    }	    
    
	/**
     * Handles broadcast messages sent to all users.
     */

    @MessageMapping("/chat.broadcast")
    @SendTo("/topic/broadcast")
    public Message broadcastMessage(Message message, Principal principal) {

		User sender = userService.findByEmail(principal.getName());
        
        // Create and save a broadcast message
		Message newMessage = new Message(sender, message.getMessageContent()); 
        messageService.saveMessage(newMessage);
        
        return newMessage;
    }
 
    /**
     * Handles the display of the private chat page.
     */
    @GetMapping("/private-chat/{id}")
    public String privateChatPage(@PathVariable Long id, Model model, Principal principal, @DestinationVariable Long receiverId) {
        User currentUser = userService.findByEmail(principal.getName()); // Get the current user
        User receiver = userService.getUserById(id); // Get the receiver by ID 
        
        if (receiver == null) {
            return "error"; // Handle the case where the recipient does not exist
        }

        List<Message> messages = messageService.getPrivateMessages(currentUser, receiver);
        
		// System.out.println("********************************************************");
        // System.out.println(messages);
        // System.out.println("********************************************************");
        
        model.addAttribute("messages", messages);        
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("receiver", receiver);
        return "chat/private-chat"; // Return the private chat view
    }    
 
    /**
     * Handles private messages sent directly to a specific user.
     */
    @MessageMapping("/chat.private.{receiverId}")
    @SendTo("/topic/private")
    public Message privateMessage(Message message, Principal principal, @DestinationVariable Long receiverId) {
        User sender = userService.findByEmail(principal.getName()); // Get the sender
        User receiver = userService.getUserById(receiverId); // Get the receiver by ID

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or Receiver is null");
        }        
        
        // Create and save the private message
        Message newMessage = new Message(sender, message.getMessageContent(), receiver);
        messageService.saveMessage(newMessage);
        
        return newMessage; // Return the new message to be sent to the receiver
    }
	    
    
}


