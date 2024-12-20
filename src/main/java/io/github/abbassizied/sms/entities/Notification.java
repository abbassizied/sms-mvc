package io.github.abbassizied.sms.entities;

import io.github.abbassizied.sms.enums.NotificationType;
import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity {

    @Column(nullable = false)
    private boolean isSeen = false; // Default to false (unread)	

	@Enumerated(EnumType.STRING)
	@Column(name = "notification_type")	
	private NotificationType notificationType;	
	
    @Column(columnDefinition = "TEXT", updatable = false)
    private String body;

    // Many-to-one relationship with User
    @ManyToOne(optional = true) // Allow the user to be null
    @JoinColumn(name = "user_id", nullable = true) // Ensure the column allows nulls 
    private User user;   

    // Default constructor    
	public Notification() {
		super();
	}

    // Getters and Setters	
	
	public boolean isSeen() {
		return isSeen;
	}

	public void setSeen(boolean isSeen) {
		this.isSeen = isSeen;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "Notification [isSeen=" + isSeen + ", notificationType=" + notificationType + ", body=" + body
				+ ", user=" + user + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}	
}
