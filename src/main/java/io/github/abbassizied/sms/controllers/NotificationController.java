package io.github.abbassizied.sms.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.github.abbassizied.sms.entities.Notification; 
import io.github.abbassizied.sms.services.NotificationService;

@Controller
public class NotificationController {


    @Autowired
    private NotificationService notificationService;	
	
	
	@MessageMapping("/send")
	@SendTo("/topic/notifications")
	public Notification sendNotification(Notification notification) {
		return notification;
	}
	
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
	
	
	
	@GetMapping("/notifications/contact-messages/unseen")
	public ResponseEntity<List<Notification>> getUnseenContactMessageNotifications() {
	    List<Notification> unseenContactMessages = notificationService.getUnseenContactMessageNotifications();
	    return ResponseEntity.ok(unseenContactMessages);
	}

	@PostMapping("/notifications/mark-seen/{id}")
	public ResponseEntity<Void> markNotificationAsSeen(@PathVariable Long id) {
	    notificationService.markAsSeen(id);
	    return ResponseEntity.ok().build();
	}

	
	@GetMapping("/notifications/contact-messages/unseen/count")
	@ResponseBody
	public ResponseEntity<Map<String, Integer>> getUnseenNotificationsCount() {
	    int unseenCount = notificationService.getUnseenContactMessageNotifications().size();
	    Map<String, Integer> response = new HashMap<>();
	    response.put("count", unseenCount);
	    return ResponseEntity.ok(response);
	}
	
	
// unused	
/*
 @GetMapping("/notifications/contact-messages")
public ResponseEntity<List<Notification>> getContactMessageNotifications() {
    List<Notification> contactNotifications = notificationService.getContactMessageNotifications();
    return ResponseEntity.ok(contactNotifications);
}
 */
	
	
}