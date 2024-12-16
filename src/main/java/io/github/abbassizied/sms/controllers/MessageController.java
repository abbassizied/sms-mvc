package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {

	
	MessageController(){
		
	}
	
    @GetMapping
    public String showMessagesInBox(Model model) {    
        return "message/message-inbox";  // Return the view for messages management
    }

	
    @GetMapping("messages-sent")
    public String showSentMessages(Model model) {    
        return "message/messages-sent";  // Return the view for messages management
    }    
    
    @GetMapping("message-read")
    public String readMessage(Model model) {    
        return "message/message-read";  // Return the view for messages management
    }    
    
    @GetMapping("message-compose")
    public String showMessageComposeForm(Model model) {    
        return "message/message-compose";  // Return the view for messages management
    }    
    
    
}
