package com.example.springboot3jwtMyproject.Service;

import com.example.springboot3jwtMyproject.Entity.Books;
import com.example.springboot3jwtMyproject.Entity.Users;

import java.util.List;

public interface BookService {
    Books saveBook(Books book, Users users);

    List<Books> getAllBooks( Users users);
}
