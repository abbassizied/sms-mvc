package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Notification;
import io.github.abbassizied.sms.enums.NotificationType;
import io.github.abbassizied.sms.repositories.NotificationRepository;
import jakarta.transaction.Transactional;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private NotificationRepository notificationRepository;
    
    
    
    public void sendNotification(Notification notification) {
    	
    	System.out.println("******************* notification ********************");
    	try {
    	notificationRepository.save(notification);
    	}catch(Exception e) {
    		System.out.println("Notification Exception message: " + e.getMessage());
    	}
    	System.out.println("******************* notification ********************");
    	
        template.convertAndSend("/topic/notifications", notification);
    }
    
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    
    // Fetch only unseen notifications of type RECEIVED_CONTACT_MESSAGE
    public List<Notification> getUnseenContactMessageNotifications() {
        return notificationRepository.findByNotificationTypeAndIsSeenFalse(NotificationType.RECEIVED_CONTACT_MESSAGE);
    }

    // Mark a notification as seen
    @Transactional
    public void markAsSeen(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setSeen(true);
        notificationRepository.save(notification); // Save the updated notification
    }

     
    
}