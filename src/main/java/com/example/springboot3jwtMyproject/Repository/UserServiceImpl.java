package com.example.springboot3jwtMyproject.Repository;

import com.example.springboot3jwtMyproject.Entity.Users;
import com.example.springboot3jwtMyproject.Error.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Users createUser(Users users) throws ResourceNotFoundException{
        // Check whether username exists or not
        boolean isExists = userRepository.existsByEmail(users.getUsername());
        if (isExists) {
            throw new ResourceNotFoundException("User already exists.");
        }

        users.setPassword(passwordEncoder.encode(users.getPassword()));

     return userRepository.save(users);
    }
}
