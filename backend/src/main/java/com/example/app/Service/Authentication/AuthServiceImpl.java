package com.example.app.Service.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.example.app.Dtos.Response.ResponseObj;
import com.example.app.Dtos.User.AuthDto;
import com.example.app.Entities.UserEntity;
import com.example.app.Repositories.User.UserServiceRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired 
    UserServiceRepository userServiceRepo; 

    @Autowired 
    AuthenticationManager authenticationManager;
    @Override
    public ResponseObj registerUser(UserEntity userEntity) {
      try{
        // to be done 
        return new ResponseObj();
      }catch(Exception e){
        return null;
      }
    }

    @Override
    public ResponseObj authenticateUser(AuthDto authDto) {
           // to be done 
           return new ResponseObj();
    }
    
}
