package com.example.HotelService.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourceNotFoundException extends RuntimeException {

        public ResourceNotFoundException(){
            super(" Resource no found !!");
        }
        public ResourceNotFoundException(String message){
            super(message);
        }
}
