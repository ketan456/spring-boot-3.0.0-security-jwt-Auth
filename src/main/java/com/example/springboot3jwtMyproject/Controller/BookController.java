package com.example.springboot3jwtMyproject.Controller;

import com.example.springboot3jwtMyproject.Entity.Books;
import com.example.springboot3jwtMyproject.Entity.Users;
import com.example.springboot3jwtMyproject.Service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
   public ResponseEntity<Books> saveBook(@RequestBody Books book , HttpServletRequest httpServletRequest){

       Users users = (Users) httpServletRequest.getAttribute(Users.LOGIN_USER);

       return new ResponseEntity<>(bookService.saveBook(book, users), HttpStatus.OK);
   }

@GetMapping("/books")
   public ResponseEntity<List<Books>> getAllBooks(HttpServletRequest httpServletRequest ){
       Users users = (Users) httpServletRequest.getAttribute(Users.LOGIN_USER);

       return new ResponseEntity<>(bookService.getAllBooks(users), HttpStatus.OK);
   }
}
