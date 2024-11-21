package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {

	// Redirect root URL to the paginated list view
    @GetMapping
    public String index() {  
        return "redirect:roles/list";
    } 	
	
    @GetMapping("list")
    public String listRoles(Model model) {    
        return "role/listRoles";  // Return the view for role list
    }

    @GetMapping("/add")
    public String addRole(Model model) {
        return "role/addRole";  // Return the view for adding a new role
    }
    
    @GetMapping("/update/{id}")
    public String updateRole(@PathVariable Long id, Model model) {
        model.addAttribute("roleId", id);
        return "role/updateRole";  // Return the view for updating an existing role
    }
    
}
