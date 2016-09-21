package com.technocracy.app.aavartan.api;

public class Attraction {
    public String name,id,description,imgUrl;
    Attraction(){}
    public Attraction(String name, String id, String description, String url){
        this.name=name;
        this.id=id;
        this.description=description;
        this.imgUrl=url;
    }
}