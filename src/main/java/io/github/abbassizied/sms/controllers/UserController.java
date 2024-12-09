package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.forms.OnCreate;
import io.github.abbassizied.sms.forms.OnUpdate;
import io.github.abbassizied.sms.forms.UserForm;
import io.github.abbassizied.sms.entities.Role;
// import io.github.abbassizied.sms.services.SecurityUser;
import io.github.abbassizied.sms.services.StorageService;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.services.RoleService;
import io.github.abbassizied.sms.services.UserService;
  
// import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final RoleService roleService;
	private final StorageService storageService;

	public UserController(UserService userService, RoleService roleService, StorageService storageService) {
		this.userService = userService;
		this.roleService = roleService;
		this.storageService = storageService;
	}

	@GetMapping 
	public String index() {
		return "redirect:/users/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		List<User> users = userService.getAllUsers();

		if (users.size() == 0)
			users = null;

		model.addAttribute("users", users);
		return "user/listUsers";
	}

	@GetMapping("/add")
	public String showAddUserForm(Model model) {
		List<Role> allRoles = new ArrayList<Role>(roleService.getAllRoles());

		int nbreRoles = allRoles.size();
		model.addAttribute("nbreRoles", nbreRoles);
		model.addAttribute("roles", allRoles);
		model.addAttribute("userForm", new UserForm());
		return "user/addUser";
	}

	@PostMapping("/add")
	public String addUser( @ModelAttribute("userForm") @Validated(OnCreate.class) UserForm userForm, 
			               BindingResult bindingResult, 
			               Model model) {
		
		if (bindingResult.hasErrors()) {
			List<Role> allRoles = new ArrayList<Role>(roleService.getAllRoles());
			model.addAttribute("roles", allRoles);
			return "user/addUser";
		} 
        
		String fileName = "";
		User user = new User();
		// be carefull
        if( ! userForm.getPhotoUrl().isEmpty()) {
        	fileName = storageService.storeSingleFile(userForm.getPhotoUrl());
    		user.setPhotoUrl(fileName); 
        }
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setActive(userForm.getActive()); 
		user.setRoles(userForm.getRoles());
		
		try {
			user = userService.createUser(user);
		} catch (Exception e) {
			if( !fileName.isEmpty() || !fileName.isBlank())
				storageService.delete(fileName);
		}
		
		return "redirect:/users/list"; // Redirect to user list
	}

	@GetMapping("/edit/{id}")
	public String showUserFormToUpdate(@PathVariable Long id, Model model) {

		List<Role> allRoles = new ArrayList<Role>(roleService.getAllRoles());
		model.addAttribute("roles", allRoles);

		User user = userService.getUserById(id); 
		// Convert User entity to UserForm
		UserForm userForm = new UserForm();
		userForm.setId(id);
		userForm.setFirstName(user.getFirstName());
		userForm.setLastName(user.getLastName());
		userForm.setEmail(user.getEmail()); 
		userForm.setActive(user.getActive()); 
		// You don't need to set the photoUrl from the user here as it's a file input in the form
		// XXXX userForm.setPhotoUrl(null); XXXX
		userForm.setRoles(user.getRoles()); 
		 
		// Add userForm to model
		model.addAttribute("userForm", userForm);
		
		return "user/updateUser";
	}

	@PostMapping("/update")
	public String updateUser( @Validated(OnUpdate.class) @ModelAttribute("userForm") UserForm userForm, 
			                  BindingResult bindingResult, 
			                  Model model) {

		if (bindingResult.hasErrors()) {
			List<Role> allRoles = new ArrayList<Role>(roleService.getAllRoles());
			model.addAttribute("roles", allRoles);
			return "user/updateUser";
		}

		// Fetch the existing User entity
		User user = userService.getUserById(userForm.getId());
        
		String fileName = "";
		// Handle the user photo file (only update if a new photo is uploaded)
		if (!userForm.getPhotoUrl().isEmpty()) {
			// delete old existing image from storage
			storageService.delete(user.getPhotoUrl());
			// upload the new image
			fileName = storageService.storeSingleFile(userForm.getPhotoUrl());
			user.setPhotoUrl(fileName); // Update logo
		}

		// Update other fields
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setActive(userForm.getActive());
		user.setRoles(userForm.getRoles());

		try {
			// Save the updated user
			user = userService.updateUser(user);
		} catch (Exception e) {
			if (!fileName.isEmpty() || !fileName.isBlank())
				storageService.delete(fileName);
		}

		return "redirect:/users/list"; // Redirect to user list
	}

	
	@GetMapping("/show/{id}")
	public String showUser(@PathVariable Long id, Model model) {
		User user = this.userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/showUser";
	}

	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return "redirect:../list";
	}
		
}
