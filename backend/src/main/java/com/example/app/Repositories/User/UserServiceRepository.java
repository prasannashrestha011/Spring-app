package com.example.app.Repositories.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.Entities.UserEntity;

@Repository
public interface UserServiceRepository extends JpaRepository<UserEntity,String>{

    UserEntity findByUsername(String username);
    Optional <UserEntity> findByEmail(String email);
}
