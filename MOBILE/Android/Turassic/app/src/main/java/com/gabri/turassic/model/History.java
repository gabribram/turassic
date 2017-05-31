package com.gabri.turassic.model;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class History {
    public String title;
    public String description;
    public int imageId;
    public String price;
    public String time;

    public History(String title, String description, String price,String time,int imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
        this.price=price;
        this.time=time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
