package com.example.app.Service.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.Entities.UserEntity;
import com.example.app.Repositories.User.UserServiceRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired 
    UserServiceRepository userServiceRepo;

    @Autowired 
    PasswordEncoder passwordEncoder;
    @Override
    public Optional<UserEntity> getUserById(String userId) {
       return userServiceRepo.findById(userId);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
      return userServiceRepo.findByUsername(username);
    }

    @Override
    public String registerUser(UserEntity userEntity) {
        try{
            if(userEntity==null) throw new InsufficientAuthenticationException("insufficent details");
           userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userServiceRepo.save(userEntity);
            return "User has been registered";
        }catch(Exception e){
            return e.getMessage();
        }
    }
    
}
