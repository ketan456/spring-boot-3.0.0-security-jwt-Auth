package com.example.springboot3jwtMyproject.Repository;

import com.example.springboot3jwtMyproject.Entity.Books;
import com.example.springboot3jwtMyproject.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books,Long> {
    List<Books> getAllByUsers(Users users);



}
