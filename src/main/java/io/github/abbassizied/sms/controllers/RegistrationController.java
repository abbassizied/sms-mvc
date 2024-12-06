package io.github.abbassizied.sms.controllers;
 
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.abbassizied.sms.forms.OnCreate;
import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.services.RoleService;
import io.github.abbassizied.sms.services.UserService;
 

@Controller
public class RegistrationController {

	private final UserService userService;
	private RoleService roleService;
	
	
	public RegistrationController(UserService userService, RoleService roleService) {
		this.userService = userService;
		this.roleService=roleService;
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "frontoffice/register";
	}

	@PostMapping("/register")
	public String registerUserAccount(@Validated(OnCreate.class) User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

	    // Retrieve the "USER" role from the database
	    Role userRole = roleService.findRoleByName("ROLE_USER");
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));

	    // Set user account to active
	    if (user.getActive() == null) {
	        user.setActive(true); // Ensure active status is not null
	    }

	    // Check for binding errors
	    if (bindingResult.hasErrors()) {
	        System.out.println("User: " + user.toString());
	        System.out.println("*****************************************");
	        bindingResult.getAllErrors().forEach(error -> {
	            System.out.println("Error: " + error.getDefaultMessage());
	        });
	        return "frontoffice/register";
	    }		

	    // Save the user
	    userService.createUser(user);

	    // Add success message to RedirectAttributes
	    redirectAttributes.addFlashAttribute("successRegister", "Registration successful! Your account has been created.");

	    return "redirect:/frontoffice/register";
	}


}
