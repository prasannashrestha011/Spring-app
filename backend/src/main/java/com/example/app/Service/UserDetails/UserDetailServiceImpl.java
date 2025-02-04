package com.example.app.Service.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.app.Repositories.User.UserServiceRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired 
    UserServiceRepository userServiceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var findUser=userServiceRepository.findByUsername(username);
       if(findUser==null) throw new UsernameNotFoundException("User not found");
        
       String[] roles=findUser.getRoles().toArray(new String[0]);

       return org.springframework.security.core.userdetails.User.builder()
              .username(username)
              .password(findUser.getPassword())
              .roles(roles)
              .build();
    } 
    
}
