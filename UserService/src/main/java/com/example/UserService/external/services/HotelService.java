package com.example.UserService.external.services;

import com.example.UserService.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotel/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}