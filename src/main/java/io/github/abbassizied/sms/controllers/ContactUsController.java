package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactUsController {

	@GetMapping("/contact")
	public String contactUsPage() {
		return "frontoffice/contact";
	}
}