package com.technocracy.app.aavartan.api;

/**
 * Created by MOHIT on 18-Sep-16.
 */

public class Event {
    private String event_name, event_type, event_description, event_image_url;
    private int event_id;

    public Event() {
    }

    public Event(int id, String event, String type, String description, String url) {
        this.event_name = event;
        this.event_id = id;
        this.event_description = description;
        this.event_type = type;
        this.event_image_url = url;
    }

    public String getEventName() {
        return this.event_name;
    }

    public String getEventType() {
        return this.event_type;
    }

    public String getEventDescription() {
        return this.event_description;
    }

    public int getEventId() {
        return this.event_id;
    }

    public String getEventImgUrl() {
        return this.event_image_url;
    }
}
