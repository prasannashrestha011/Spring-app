package com.example.app.Service.UserService;

import java.util.Optional;

import com.example.app.Entities.UserEntity;

public interface UserService {

    Optional<UserEntity> getUserById(String userId);
    UserEntity getUserByUsername(String username);
    String registerUser(UserEntity userEntity);
}
