package com.technocracy.app.aavartan.api;


public class Notifications {

    int id;
    String title;
    String message;
    String imageUrl;
    String createdAt;
    String type;
    int eventId;


    public Notifications() {

    }

    public Notifications(int id, String type, int eventId, String title, String message,
                         String imageUrl, String createdAt) {
        this.id = id;
        this.type = type;
        this.eventId = eventId;
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}

