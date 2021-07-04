package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping
    public ResponseEntity<String> sayHello() {
        logger.info("Someone says hello");
        return ResponseEntity.ok("Hello, every one can view this page.");
    }

    @PostMapping()
    public ResponseEntity<String> makeAPost() {
        logger.info("Someone makes a post");
        return ResponseEntity.ok("You made a post, but nothing changed.");
    }

}
