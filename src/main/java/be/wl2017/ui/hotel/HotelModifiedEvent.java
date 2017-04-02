package be.wl2017.ui.hotel;

import be.wl2017.backend.hotel.Hotel;

import java.io.Serializable;

/**
 * Created by M999CMI on 08/03/2017.
 */
public class HotelModifiedEvent implements Serializable {

    private final Hotel hotel;

    public HotelModifiedEvent(Hotel p) {
        this.hotel = p;
    }

    public Hotel getHotel() {
        return hotel;
    }

}