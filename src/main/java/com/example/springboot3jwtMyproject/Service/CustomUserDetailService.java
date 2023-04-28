package com.example.springboot3jwtMyproject.Service;

import com.example.springboot3jwtMyproject.Entity.Users;
import com.example.springboot3jwtMyproject.Error.ResourceNotFoundException;
import com.example.springboot3jwtMyproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println(username);
        Users users =userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        System.out.println(users.getUsername());
        return users;

    }
}
