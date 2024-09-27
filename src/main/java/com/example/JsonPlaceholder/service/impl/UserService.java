package com.example.JsonPlaceholder.service.impl;

import com.example.JsonPlaceholder.dto.UserResponseDTO;
import com.example.JsonPlaceholder.entity.User;
import com.example.JsonPlaceholder.exception.CustomException;
import com.example.JsonPlaceholder.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements IUserService {


    @Value("${spring.restTemplate.service.url}")
    private String basePath;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public UserService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UserResponseDTO> findAllUsers() {
        try {
            ResponseEntity<User[]> response = restTemplate.getForEntity(basePath + "/users", User[].class);
            return Optional.ofNullable(response.getBody())
                    .map(Arrays::stream)
                    .orElseGet(Stream::empty)
                    .map(this::maToDTO)
                    .collect(Collectors.toList());
        } catch (HttpClientErrorException e) {
            throw new CustomException("Error del cliente: " + e.getStatusCode(), e, HttpStatus.valueOf(e.getRawStatusCode()));
        } catch (HttpServerErrorException e) {
            throw new CustomException("Error del servidor: " + e.getStatusCode(), e, HttpStatus.valueOf(e.getRawStatusCode()));
        } catch (ResourceAccessException e) {
            throw new CustomException("Error de acceso al recurso: " + e.getMessage(), e, HttpStatus.GATEWAY_TIMEOUT);
        } catch (RestClientException e) {
            throw new CustomException("Error al conectar con la API externa", e, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }


    private UserResponseDTO maToDTO(User user){
        return objectMapper.convertValue(user, UserResponseDTO.class);
    }

    private User mapToEntity(UserResponseDTO userResponseDTO){
        return objectMapper.convertValue(userResponseDTO,User.class);
    }
}
