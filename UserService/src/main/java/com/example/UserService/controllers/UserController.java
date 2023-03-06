package com.example.UserService.controllers;
import com.example.UserService.entities.Hotel;
import com.example.UserService.entities.Ratings;
import com.example.UserService.entities.User;
import com.example.UserService.external.services.HotelService;
import com.example.UserService.repository.UserRepository;

import com.example.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//import static org.springframework.aop.scope.logger;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    // create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    // get a user
    @GetMapping("/{userId}")
    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingFallbackMethod")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        Ratings[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+userId, Ratings[].class);
//        System.out.println();
        List<Ratings> ratings =    Arrays.stream(ratingsOfUser).toList();
        List<Ratings> ratingsList = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            System.out.println(hotel);
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingsList);
        return ResponseEntity.ok(user);
    }
    // creating fallback method
    public ResponseEntity<User> ratingFallbackMethod(String userId,Exception ex){
//        logger.info("fallback is created becauz rating service is down:",ex.getMessage());
        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("this is dummy data").userId("12345").build();
        return new ResponseEntity<>(user,HttpStatus.OK);

    }

    // get all user
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
