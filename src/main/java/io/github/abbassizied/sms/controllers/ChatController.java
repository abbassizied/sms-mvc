package io.github.abbassizied.sms.controllers;
 
import io.github.abbassizied.sms.entities.ChatMessage; 
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.enums.ChatMessageType;
import io.github.abbassizied.sms.services.ChatMessageService;
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

	private final ChatMessageService chatMessageService;
	private final UserService userService;

    public ChatController(ChatMessageService chatMessageService, UserService userService) {
		this.chatMessageService = chatMessageService;
		this.userService = userService;
	}

    
	@GetMapping("/public-chat")
    public String publicChat(Model model, Authentication authentication) {
		
		List<ChatMessage> messages = chatMessageService.getMessagesByType(ChatMessageType.PUBLIC_CHAT); 
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
    public ChatMessage broadcastMessage(ChatMessage message, Principal principal) {
        
    	// System.out.println(message.getMessageContent()); 
		User sender = userService.findByEmail(principal.getName());
        
        // Create and save a broadcast message
		ChatMessage newMessage = new ChatMessage(sender, message.getMessageContent());
    	// System.out.println(newMessage); 
		chatMessageService.saveMessage(newMessage);
        
        return newMessage;
    }
 
    /**
     * Handles the display of the private chat page.
     */
    @GetMapping("/private-chat/{id}")
    public String privateChatPage(@PathVariable Long id, Model model, Principal principal) {
        User currentUser = userService.findByEmail(principal.getName()); // Get the current user
        User receiver = userService.getUserById(id); // Get the receiver by ID 

        if (receiver == null) {
            return "error"; // Handle the case where the recipient does not exist
        }

        List<ChatMessage> messages = chatMessageService.getPrivateMessages(currentUser, receiver);

        model.addAttribute("messages", messages);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("receiver", receiver);

        
        System.out.println("Retrieved Messages: " + messages);
        // System.out.println(currentUser);
        System.out.println(receiver);
       
        
        return "chat/private-chat"; // Return the private chat view
    }
   
 
    /**
     * Handles private messages sent directly to a specific user.
     */
    @MessageMapping("/chat.private.{receiverId}")
    @SendTo("/topic/private")
    public ChatMessage privateMessage(ChatMessage message, Principal principal, @DestinationVariable Long receiverId) {
        User sender = userService.findByEmail(principal.getName()); // Get the sender
        User receiver = userService.getUserById(receiverId); // Get the receiver by ID

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Sender or Receiver is null");
        }        
        
        // Create and save the private message
        ChatMessage newMessage = new ChatMessage(sender, message.getMessageContent(), receiver);
        chatMessageService.saveMessage(newMessage);
        
        return newMessage; // Return the new message to be sent to the receiver
    }
	    
    
}

