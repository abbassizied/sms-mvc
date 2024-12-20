package io.github.abbassizied.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.abbassizied.sms.entities.Notification;
import io.github.abbassizied.sms.enums.NotificationType;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
    // Fetch unseen notifications of type NotificationType
    List<Notification> findByNotificationTypeAndIsSeenFalse(NotificationType notificationType);	
}