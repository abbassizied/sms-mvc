package io.github.abbassizied.sms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Message extends BaseEntity {

    @Column(name = "message_content", columnDefinition = "TEXT", updatable = false)
    protected String messageContent;

    @Column(nullable = false)
    protected boolean isSeen = false; // Default to false (unread)
    
    public Message() {
        super();
    } 

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    // Getters and Setters for the 'isSeen' field
    protected boolean getIsSeen() {
        return isSeen;
    }

    protected void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

	@Override
	public String toString() {
		return "Message [messageContent=" + messageContent + ", isSeen=" + isSeen + ", id=" + id + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
