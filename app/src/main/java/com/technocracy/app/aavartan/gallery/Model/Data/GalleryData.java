package com.technocracy.app.aavartan.gallery.Model.Data;


import java.util.List;

public class GalleryData {
    private boolean success;
    private String message;
    private List<Image> imageList;

    public GalleryData(boolean success, String message, List<Image> imageList) {
        this.success = success;
        this.message = message;
        this.imageList = imageList;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<Image> getImageList() {
        return imageList;
    }
}
