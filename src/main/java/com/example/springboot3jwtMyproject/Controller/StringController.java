package com.example.springboot3jwtMyproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StringController {

    @GetMapping("/hello")
    public String hello() {

        return "Hello World";
    }

    @GetMapping("/abs")
    public String getapi() {
        return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    }
}
