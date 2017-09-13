package com.technocracy.app.aavartan.Attraction.Model.Data;


import java.util.List;

public class AttractionData {
    private boolean success;
    private String message;
    private List<Attraction> attractionList;
    public AttractionData(boolean success, String message, List<Attraction> attractionList) {
        this.success = success;
        this.message = message;
        this.attractionList = attractionList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Attraction> getAttractionList() {
        return attractionList;
    }
}
