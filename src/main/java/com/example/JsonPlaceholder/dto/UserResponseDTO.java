package com.example.JsonPlaceholder.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDTO {

    private Long id;
    private String name;
    private String userName;
    private String email;
    private String address;
}
