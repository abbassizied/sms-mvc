package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.ContactMessage;

public interface ContactMessageService {

	public ContactMessage saveContactMessage(ContactMessage contactMessage);

	public List<ContactMessage> getAllContactMessages();

}
