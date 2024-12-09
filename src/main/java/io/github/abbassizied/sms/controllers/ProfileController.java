package io.github.abbassizied.sms.controllers;
  
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.abbassizied.sms.entities.User; 
import io.github.abbassizied.sms.forms.UpdatePasswordForm; 
import io.github.abbassizied.sms.forms.UserProfileForm;
import io.github.abbassizied.sms.services.SecurityUser;
import io.github.abbassizied.sms.services.StorageService;
import io.github.abbassizied.sms.services.UserService;
import io.github.abbassizied.sms.utils.Alert;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {


	private final UserService userService; 
	private final StorageService storageService;

	public ProfileController(UserService userService, StorageService storageService) {
		this.userService = userService; 
		this.storageService = storageService;
	}	
 
	@GetMapping 
	public String currentUserProfile(Model model, Authentication authentication) {
		
	    SecurityUser currentUser = (SecurityUser) authentication.getPrincipal(); 
	    
	    User user = userService.getUserById(currentUser.getId());
	    
	    model.addAttribute("currentUser", user); 	   
	    
		return "user/profile"; 
	}	
 	
	
	@GetMapping("/update-profile")
	public String showUpdateProfileForm(Model model, Authentication authentication) {
		
	    SecurityUser currentUser = (SecurityUser) authentication.getPrincipal();  
		
	    UserProfileForm userProfileForm = new UserProfileForm();
	    userProfileForm.setId(currentUser.getId()); 
	    userProfileForm.setEmail(currentUser.getUsername());   
	    userProfileForm.setFirstName(currentUser.getFirstName());
	    userProfileForm.setLastName(currentUser.getLastName());
		
	    model.addAttribute("userProfileForm", userProfileForm);	   	   
	    
		return "user/update-profile"; 		
	}	

	@PostMapping("/update-profile")
	public String updateUserProfile( @Valid @ModelAttribute("userProfileForm") UserProfileForm userProfileForm, 
			                  BindingResult bindingResult, 
			                  Model model,
                              RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {   
		    // model.addAttribute("userProfileForm", userProfileForm);	// just return the existing object 	
			return "user/update-profile";
		}

		// Fetch the existing User entity
		User user = userService.getUserById(userProfileForm.getId());
        
		String fileName = "";
		// Handle the user photo file (only update if a new photo is uploaded)
		if (userProfileForm.getPhotoUrl() != null && !userProfileForm.getPhotoUrl().isEmpty()) {
		    String oldPhotoFileName = user.getPhotoUrl();
		    if (oldPhotoFileName != null) {
				// delete old existing image from storage
		    	storageService.delete(oldPhotoFileName);
		    }
			// upload the new image
			fileName = storageService.storeSingleFile(userProfileForm.getPhotoUrl());
			user.setPhotoUrl(fileName); // Update logo
		}
		
		// Update other fields
		user.setFirstName(userProfileForm.getFirstName());
		user.setLastName(userProfileForm.getLastName()); 

		try {
			// Save the updated user
			user = userService.updateUser(user);
		} catch (Exception e) {
			if (!fileName.isEmpty() || !fileName.isBlank())
				storageService.delete(fileName);
		}

        redirectAttributes.addFlashAttribute("alert", new Alert("success", "Your profile has been updated successfully!"));		
		return "redirect:/profile"; // Redirect to user profile
	}
 
	
	@GetMapping("/update-password")
	public String showUpdatePasswordForm(Model model, Authentication authentication) {
		
	    SecurityUser currentUser = (SecurityUser) authentication.getPrincipal();  
		 
	    UpdatePasswordForm updatePasswordForm = new UpdatePasswordForm();
	    updatePasswordForm.setId(currentUser.getId());
	    updatePasswordForm.setPassword(currentUser.getPassword());
		
	    model.addAttribute("updatePasswordForm", updatePasswordForm);	   
	    
		return "user/update-password"; 		
	}	
    
	@PostMapping("/update-password")
    public String updatePassword( @Valid @ModelAttribute("updatePasswordForm") UpdatePasswordForm updatePasswordForm, 
                                  BindingResult bindingResult, 
                                  Model model,
                                  RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {   
		    model.addAttribute("updatePasswordForm", updatePasswordForm); // just return the existing object  
		    return "user/update-password"; 
		}
		
		// Fetch the existing User entity
		User user = userService.getUserById(updatePasswordForm.getId());
		
		user.setPassword(updatePasswordForm.getPassword()); 
        // Update the password
        userService.updatePassword(user);
 
        redirectAttributes.addFlashAttribute("alert", new Alert("success", "Your password has been updated successfully!"));
		return "redirect:/profile"; // Redirect to user profile
    }	    
	
}
