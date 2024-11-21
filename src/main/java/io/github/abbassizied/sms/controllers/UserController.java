package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/users")
public class UserController { 
	
	// Redirect root URL to the paginated list view
	@GetMapping()
	public String index() { 
		return "redirect:users/list";
	}	
	
    @GetMapping("list") 
    public String listUsers(Model model) {    
        return "user/listUsers";  // Return the view for user list
    }
    
}
