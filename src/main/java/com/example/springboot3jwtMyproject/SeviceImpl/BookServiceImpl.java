package com.example.springboot3jwtMyproject.SeviceImpl;

import com.example.springboot3jwtMyproject.Entity.Books;
import com.example.springboot3jwtMyproject.Entity.Users;
import com.example.springboot3jwtMyproject.Repository.BookRepository;
import com.example.springboot3jwtMyproject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;


    @Override
    public Books saveBook(Books book, Users users) {

book.setUsers(users);

        return bookRepository.save(book);
    }

    @Override
    public List<Books> getAllBooks( Users users) {
        return bookRepository.getAllByUsers( users);
    }


}
