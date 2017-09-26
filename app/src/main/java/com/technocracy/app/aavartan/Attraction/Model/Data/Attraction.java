package com.technocracy.app.aavartan.Attraction.Model.Data;

public class Attraction {
    private int id;
    private String name, description, imgUrl;

    public Attraction(int id, String name, String description, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}