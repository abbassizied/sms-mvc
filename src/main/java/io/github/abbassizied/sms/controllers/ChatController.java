package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @GetMapping
    public String manageChats(Model model) {    
        return "chat/chat";  // Return the view for chat management
    }
    
}

