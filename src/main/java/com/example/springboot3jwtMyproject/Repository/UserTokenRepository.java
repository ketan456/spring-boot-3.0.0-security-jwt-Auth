package com.example.springboot3jwtMyproject.Repository;

import com.example.springboot3jwtMyproject.Entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    UserToken findByUserId(Long id);
}
