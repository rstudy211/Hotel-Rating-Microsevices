package com.example.HotelService.services;

import com.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService  {
    List<Hotel> getAllHotels();
    Hotel getHotel(String id);
    Hotel addHotel(Hotel hotel);
    Hotel updateHotel(Hotel hotel);
    Hotel deleteHotel(String id);
}
