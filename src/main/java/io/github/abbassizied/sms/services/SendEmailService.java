package io.github.abbassizied.sms.services;

public interface SendEmailService {

	public boolean sendSimpleMail(String recipientEmail, String subject, String messageContent);

	public boolean sendSimpleTextMail(String recipientEmail, String subject, String messageContent, String template);
}
