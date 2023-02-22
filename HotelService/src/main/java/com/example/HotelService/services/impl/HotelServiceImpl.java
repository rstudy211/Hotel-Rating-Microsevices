package com.example.HotelService.services.impl;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.exceptions.ResourceNotFoundException;
import com.example.HotelService.repository.HotelRepository;
import com.example.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public List<Hotel> getAllHotels() {
        return new ArrayList<>(hotelRepository.findAll());
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("No hotel with given id"));
    }

    @Override
    public Hotel addHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setId(randomHotelId);
        hotelRepository.save(hotel);
        return hotel;
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
        return hotel;
    }

    @Override
    public Hotel deleteHotel(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No hotel with given id"));
        hotelRepository.delete(hotel);
        return hotel;
    }
}
