package io.github.abbassizied.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.abbassizied.sms.entities.ContactMessage;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

}
