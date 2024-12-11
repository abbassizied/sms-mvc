package io.github.abbassizied.sms.controllers;
 
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.forms.RegisterForm; 
import io.github.abbassizied.sms.services.RoleService;
import io.github.abbassizied.sms.services.UserService;
import io.github.abbassizied.sms.utils.Alert;
import jakarta.validation.Valid;

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
		RegisterForm registerForm = new RegisterForm();
		model.addAttribute("registerForm", registerForm);
		return "frontoffice/register";
	}

	@PostMapping("/register")
	public String registerUserAccount( @Valid @ModelAttribute("registerForm") RegisterForm registerForm, 
			                           BindingResult bindingResult, 
			                           RedirectAttributes redirectAttributes) {

	    if (bindingResult.hasErrors()) { 
	        return "frontoffice/register"; // Return to the registration page if there are errors
	    }		
	    
	    User user = new User();
	    
	    // Retrieve the "USER" role from the database
        Role userRole = roleService.findRoleByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        // Assign the existing role to the user
	    Set<Role> roles = new HashSet<>(); 
	    roles.add(userRole); 
	    user.setRoles(roles);
	    
	    user.setFirstName(registerForm.getFirstName());
	    user.setLastName(registerForm.getLastName());
	    user.setEmail(registerForm.getEmail());
	    user.setPassword(registerForm.getPassword());
	    user.setActive(true);  // Set the user account to active, allowing login functionality

		// Save the user
		userService.createUser(user);
 
	    // Add success message to RedirectAttributes
        redirectAttributes.addFlashAttribute("alert", new Alert("success", "Registration successful! Your account has been created.")); 
 
		return "redirect:/register"; // Redirect to the register page after successful registration
	}

}
