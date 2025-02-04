package com.example.app.Entities;

import jakarta.persistence.FetchType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")

public class UserEntity   {

    @Id
    @Column(name="userId")
    private String userId;

    @Column(unique = true)
    private String username; 

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"))
    @Column(name ="role")
    private List<String> roles;

    public Date createdAt;

    public Date updatedAt;
    
    @PrePersist 
    private void OnCreate(){
        userId=UUID.randomUUID().toString().substring(0,16);
        createdAt=new Date();
        updatedAt=createdAt;    
    }
    
    @PreUpdate 
    private void OnUpdate(){
        updatedAt=new Date();
    }
}
