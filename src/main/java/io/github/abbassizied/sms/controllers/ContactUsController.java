package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.abbassizied.sms.entities.ContactMessage;
import io.github.abbassizied.sms.forms.ContactForm;
import io.github.abbassizied.sms.services.ContactMessageService;
import io.github.abbassizied.sms.services.SendEmailService;
import io.github.abbassizied.sms.utils.Alert;
import jakarta.validation.Valid;

@Controller
public class ContactUsController {
	
	private final SendEmailService sendEmailService; 
	public final ContactMessageService contactMessageService;

	ContactUsController( SendEmailService sendEmailService, ContactMessageService contactMessageService) { 
		this.sendEmailService = sendEmailService;
		this.contactMessageService = contactMessageService;
	}	

	@GetMapping("/contact")
	public String contactUsPage(Model model) {
		model.addAttribute("contactForm", new ContactForm());
		return "frontoffice/contact";
	}
	
	
    @PostMapping("/contact")
    public String submitContactForm(@Valid @ModelAttribute ContactForm contactForm,
                                    BindingResult result, 
			                       RedirectAttributes redirectAttributes) {
    	
        if (result.hasErrors()) {
            return "frontoffice/contact";
        }
        
        // System.out.println(contactForm);
        
        // Logic to handle successful submission (e.g., send an email)
        try {
        	 ContactMessage contactMessage = new ContactMessage();
        	 contactMessage.setFirstName(contactForm.getFirstName());
        	 contactMessage.setLastName(contactForm.getLastName());
        	 contactMessage.setEmail(contactForm.getEmail());
        	 contactMessage.setSubject(contactForm.getSubject());
        	 contactMessage.setMessageContent(contactForm.getMessageBody());
        	 
        	// Call the email service to send email to admin
        	sendEmailService.sendSimpleMail("abbassizied@outlook.fr", 
        		                                          contactForm.getSubject(), 
        		                                          contactForm.getMessageBody());
        	// save the contact message in database
        	contactMessageService.saveContactMessage(contactMessage);
        	
        	// Call the email service to send email to the sender 
        	String messageBody = String.format(
        		    "Dear %s,\n\nThank you for reaching out to us. We have received your message regarding \"%s\" and appreciate you taking the time to contact us.\n\n"
        		    + "Our team is currently reviewing your message, and we will process it as soon as possible. We will get back to you with further information or updates shortly.\n\n"
        		    + "If you have any additional questions or need further assistance in the meantime, please feel free to reply to this email.\n\n"
        		    + "Best regards,\n%s",
        		    contactForm.getFirstName(), contactForm.getSubject(), "Your Company/Team Name"
        		);        	
        	
        	sendEmailService.sendSimpleMail(contactForm.getEmail(), "Re: "+contactForm.getSubject(), messageBody);
            
        	// Add success message to RedirectAttributes
        	redirectAttributes.addFlashAttribute("alert", new Alert("success", "Message sent successfully!")); 
        	
        }catch(Exception e) {
        	// Add success message to RedirectAttributes
        	redirectAttributes.addFlashAttribute("alert", new Alert("danger", "We apologize, but we've encountered an error. Please try again later."));         	
        }
        
 
		return "redirect:/contact"; // Redirect to the register page after successful registration        
        
    }
	
}