
package com.example.app.Service.Authentication;

import com.example.app.Dtos.Response.ResponseObj;
import com.example.app.Dtos.User.AuthDto;
import com.example.app.Entities.UserEntity;

public interface AuthService {
    
    public ResponseObj registerUser(UserEntity userEntity);
    public ResponseObj authenticateUser(AuthDto authDto);
}