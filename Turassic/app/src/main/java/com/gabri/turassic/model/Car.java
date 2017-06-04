package com.gabri.turassic.model;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Car {
    public String type;
    public String model;
    public int imageId;
    public String hourly;
    public String year;

    public Car(String type, String model, int imageId, String hourly, String year) {
        this.type = type;
        this.model = model;
        this.imageId = imageId;
        this.hourly = hourly;
        this.year = year;
    }
    public Car() {
        this.type = type;
        this.model = model;
        this.imageId = imageId;
        this.hourly = hourly;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
