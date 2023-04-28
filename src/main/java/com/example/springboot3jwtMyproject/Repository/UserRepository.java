package com.example.springboot3jwtMyproject.Repository;


import com.example.springboot3jwtMyproject.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByEmail(String email);

    Boolean existsByEmail(String username);
}
