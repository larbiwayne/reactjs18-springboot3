package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.security.JwtService;
import com.example.demo.user.dto.LoginRequest;
import com.example.demo.user.dto.LoginResponse;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;
      @Autowired
    private AuthenticationManager authenticationManager; // Inject AuthenticationManager

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        // Assuming you have a method to get the user by username
        User user = userService.findByUsername(loginRequest.getUsername());
        
        // Create response object including role
        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        response.setToken(token); // Set the token

        return ResponseEntity.ok(response);
    }
    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String username = auth.getName();
        User user = userService.findByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

}

