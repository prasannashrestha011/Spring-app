package com.example.app.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.Dtos.User.AuthDto;
import com.example.app.Entities.UserEntity;

import com.example.app.Service.UserService.UserService;

@RestController
@RequestMapping("/account")
public class AuthController {
    
   @Autowired 
   UserService userService;

   @Autowired 
   AuthenticationManager authenticationManager;
   
   @PostMapping("/register")
   public ResponseEntity<Object> registerUser(@RequestBody UserEntity userEntity){
    var responseMessage=userService.registerUser(userEntity);
        return ResponseEntity.ok().body(Map.of("message",responseMessage));
   }

   @PostMapping("/login")
   public ResponseEntity<Object> authenticateUser(@RequestBody AuthDto authDto){
      try{
         authenticationManager.authenticate(new 
         UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        return ResponseEntity.ok(Map.of("message","authenticated"));
      }catch(BadCredentialsException e){
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
      }catch(Exception e){
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
      }
   }

}
