package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/messages")
public class MessageController {

    @GetMapping
    public String manageMessages(Model model) {    
        return "message/message";  // Return the view for messages management
    }
    
}
