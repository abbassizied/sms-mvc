package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.ContactMessage;
import io.github.abbassizied.sms.repositories.ContactMessageRepository;

@Service
public class ContactMessageServiceImpl implements ContactMessageService {

	private final ContactMessageRepository contactMessageRepository;

	public ContactMessageServiceImpl(ContactMessageRepository contactMessageRepository) {
		this.contactMessageRepository = contactMessageRepository;
	}

	@Override
	public ContactMessage saveContactMessage(ContactMessage contactMessage) {
		return contactMessageRepository.save(contactMessage);
	}

	@Override
	public List<ContactMessage> getAllContactMessages() {
		return contactMessageRepository.findAll();
	}

}
