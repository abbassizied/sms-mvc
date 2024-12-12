package io.github.abbassizied.sms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Override	
	public boolean sendSimpleMail(String recipientEmail, String subject, String messageContent) {
		try {
			// Create a simple MimeMessage
			MimeMessage message = javaMailSender.createMimeMessage();

			// Set MimeMessageHelper with 'multipart' as false for simple text emails
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

			helper.setTo(recipientEmail); // Set recipient
			helper.setSubject(subject); // Set email title
			helper.setText(messageContent, false); // Set the content (false for plain text)

			// Send the email
			javaMailSender.send(message);

			return true; // Return true if email is successfully sent
		} catch (MessagingException e) {
			e.printStackTrace(); // Handle any exceptions during email sending
			return false; // Return false if there is an error
		}		
	}
	
	@Override
	public boolean sendSimpleTextMail(String recipientEmail, String subject, String messageContent, String template) {
		try {
			// Create a context for Thymeleaf template processing
			Context context = new Context();

			// Set variables for the template using data from the email object
			
			// Use recipient email or actual name if  available
			context.setVariable("recipientEmail", recipientEmail);  
			context.setVariable("messageContent", messageContent); // Email body/message
			context.setVariable("subject", subject); // Email subject

			// Process the email template using Thymeleaf (assuming "emails/welcome"
			// template exists)
			String processedContent = templateEngine.process("emails/" + template, context);

			// Create the email message
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			// Set email details
			helper.setTo(recipientEmail); // Recipient email
			helper.setSubject(subject); // Subject
			helper.setText(processedContent, true); // Processed HTML content

			// Send the email
			javaMailSender.send(mimeMessage);

			// Return true indicating the email was sent successfully
			return true;
		} catch (MessagingException e) {
			// Use proper logging for errors
			e.printStackTrace(); // Replace this with logging in production
			return false; // Return false if there is an error
		}
	}

}
