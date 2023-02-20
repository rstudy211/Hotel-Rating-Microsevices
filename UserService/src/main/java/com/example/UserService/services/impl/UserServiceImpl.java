package com.example.UserService.services.impl;

import com.example.UserService.entities.User;
import com.example.UserService.exception.ResourceNotFoundException;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server:" + userId));
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User deleteUser(String userId){
        return userRepository.deleteByUserId(userId);
    }
}
