package com.example.demo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.user.model.User;
import com.example.demo.user.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database using the repository
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert your custom User entity to Spring Security's UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user) // Use getAuthorities to convert roles to GrantedAuthority
        );
    }

    // Define the getAuthorities method
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // Assuming that User has a single role. If user has multiple roles, adapt the method accordingly
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }
}
