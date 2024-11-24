package io.github.abbassizied.sms.utils;

public class Alert {
    private String type; // e.g., 'success', 'danger'
    private String message; // The alert message

    public Alert(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
