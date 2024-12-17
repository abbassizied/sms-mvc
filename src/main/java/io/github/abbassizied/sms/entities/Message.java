package io.github.abbassizied.sms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Message extends BaseEntity {

    @Column(name = "message_content", columnDefinition = "TEXT", updatable = false)
    protected String messageContent;

    public Message() {
        super();
    } 

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public String toString() {
        return "Message [messageContent=" + messageContent + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt="
                + updatedAt + "]";
    }
}
