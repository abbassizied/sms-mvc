package io.github.abbassizied.sms.controllers;
 
import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.forms.UserForm;
import io.github.abbassizied.sms.services.RoleService;
import io.github.abbassizied.sms.services.UserService;

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
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "frontoffice/register";
	}

	@PostMapping("/register")
	public String registerUserAccount(@Valid UserForm userForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

	    if (bindingResult.hasErrors()) {
	        // Print validation errors to the console
	        bindingResult.getAllErrors().forEach(error -> {
	            System.out.println("Error: " + error.getDefaultMessage());
	        });
	        return "frontoffice/register"; // Return to the registration page if there are errors
	    }		
	    
	    User user = new User();
	    
	    // Retrieve the "USER" role from the database
        Role userRole = roleService.findRoleByName("USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        // Assign the existing role to the user
	    Set<Role> roles = new HashSet<>(); 
	    roles.add(userRole); 
	    user.setRoles(roles);
	    
	    user.setFirstName(userForm.getFirstName());
	    user.setLastName(userForm.getLastName());
	    user.setEmail(userForm.getEmail());
	    user.setPassword(userForm.getPassword());

		// Save the user
		userService.createUser(user);
		
		// Add a success message to the model to display in the UI
	    // Add success message to RedirectAttributes
	    redirectAttributes.addFlashAttribute("successRegister", "Registration successful! Your account has been created.");
 
		return "redirect:/frontoffice/register"; // Redirect to the register page after successful registration
	}

}
