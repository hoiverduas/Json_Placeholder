package com.example.JsonPlaceholder.entity;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String userName;
    private String email;
    private String address;
}
