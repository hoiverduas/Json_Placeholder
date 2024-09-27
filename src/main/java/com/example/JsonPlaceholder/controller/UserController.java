package com.example.JsonPlaceholder.controller;

import com.example.JsonPlaceholder.dto.UserRequestDTO;
import com.example.JsonPlaceholder.dto.UserResponseDTO;
import com.example.JsonPlaceholder.entity.User;
import com.example.JsonPlaceholder.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    final private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserResponseDTO>>getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.findAllUsers());
    }


}
