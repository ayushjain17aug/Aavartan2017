package com.technocracy.app.aavartan.Event.Model.Data;


public class Event {
    private String eventId,type,name,description,date,time,venue,image_url;

    public Event(String eventId,  String name,String type, String description, String image_url, String date, String time, String venue) {
        this.eventId = eventId;
        this.type = type;
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.image_url = image_url;
    }

    public String getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getType() {
        return type;
    }

    public String getImage_url() {
        return image_url;
    }
}