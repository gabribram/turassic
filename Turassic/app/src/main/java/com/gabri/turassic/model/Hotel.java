package com.gabri.turassic.model;

/**
 * Created by gabri-dev on 5/26/17.
 */

public class Hotel {
    public String hotelname;
    public String address;
    public int imageId;
    public String carcount;


    public String getHotelname() {
        return hotelname;
    }

    public void setHotelname(String hotelname) {
        this.hotelname = hotelname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCarcount() {
        return carcount;
    }

    public void setCarcount(String carcount) {
        this.carcount = carcount;
    }

    public Hotel(String hotelname, String address, int imageId, String carcount) {

        this.hotelname = hotelname;
        this.address = address;
        this.imageId = imageId;
        this.carcount = carcount;
    }
    public Hotel() {

        this.hotelname = hotelname;
        this.address = address;
        this.imageId = imageId;
        this.carcount = carcount;
    }
}
