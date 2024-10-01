package com.example.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private String username;
    private String role;
    private String token;


}