package com.example.demo.user.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.user.model.User;
import com.example.demo.user.service.UserService;

import java.util.List;

    @RestController
    @RequestMapping("/api/admin")
    public class AdminController {
    
        @Autowired
        private UserService userService;
    
        @PostMapping("/addUser")
        public User addUser(@RequestBody User user) {
            return userService.createUser(user);
        }
    
        @PutMapping("/updateUser")
        public User updateUser(@RequestBody User user) {
            return userService.updateUser(user);
        }
    
   
    
        @GetMapping("/users")
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }
            @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/customquery/{CNAS}") 
    public List<User> getUsersByRole(@PathVariable String CNAS) {
        return userService.getUsersByRole(CNAS);
    }
    }
    

