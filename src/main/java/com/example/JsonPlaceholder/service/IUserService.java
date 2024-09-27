package com.example.JsonPlaceholder.service;

import com.example.JsonPlaceholder.dto.UserResponseDTO;
import com.example.JsonPlaceholder.entity.User;

import java.util.List;

public interface IUserService {

    List<UserResponseDTO> findAllUsers();
}
