package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*; 
import ch.qos.logback.core.model.Model;

@Controller
public class RegistrationController {

	@GetMapping("/register")
	public String showRegistrationForm(Model model) { 
		return "frontoffice/register";
	}
	
}
