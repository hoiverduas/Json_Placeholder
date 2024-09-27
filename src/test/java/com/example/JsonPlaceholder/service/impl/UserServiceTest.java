package com.example.JsonPlaceholder.service.impl;

import com.example.JsonPlaceholder.dto.UserResponseDTO;
import com.example.JsonPlaceholder.entity.User;
import com.example.JsonPlaceholder.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllUsers_ShouldReturnUserResponseDTOList_WhenResponseIsSuccessful() {
        // Arrange
        User user1 = new User(); // Asume que User tiene un constructor sin argumentos
        user1.setId(1L);
        user1.setName("User 1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User 2");

        User[] users = {user1, user2};
        ResponseEntity<User[]> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(User[].class))).thenReturn(responseEntity);
        when(objectMapper.convertValue(user1, UserResponseDTO.class)).thenReturn(new UserResponseDTO());
        when(objectMapper.convertValue(user2, UserResponseDTO.class)).thenReturn(new UserResponseDTO());

        // Act
        List<UserResponseDTO> result = userService.findAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(User[].class));
    }

    @Test
    public void findAllUsers_ShouldThrowCustomException_WhenHttpClientErrorExceptionOccurs() {
        // Arrange
        when(restTemplate.getForEntity(anyString(), eq(User[].class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> userService.findAllUsers());
        assertEquals("Error del cliente: 404 NOT_FOUND", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void findAllUsers_ShouldThrowCustomException_WhenHttpServerErrorExceptionOccurs() {
        // Arrange
        when(restTemplate.getForEntity(anyString(), eq(User[].class)))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> userService.findAllUsers());
        assertEquals("Error del servidor: 500 INTERNAL_SERVER_ERROR", exception.getMessage());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

    @Test
    public void findAllUsers_ShouldThrowCustomException_WhenResourceAccessExceptionOccurs() {
        // Arrange
        when(restTemplate.getForEntity(anyString(), eq(User[].class)))
                .thenThrow(new ResourceAccessException("Timeout"));

        // Act & Assert
        CustomException exception = assertThrows(CustomException.class, () -> userService.findAllUsers());
        assertEquals("Error de acceso al recurso: Timeout", exception.getMessage());
        assertEquals(HttpStatus.GATEWAY_TIMEOUT, exception.getStatus());
    }
}
