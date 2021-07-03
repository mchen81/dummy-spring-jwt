package com.example.demo.controller;

import com.example.demo.controller.dto.AccountCredential;
import com.example.demo.security.authtication.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hello")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserDetailsManager userDetailsManager;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("signup")
    public ResponseEntity<String> createAccount(@RequestBody AccountCredential accountCredential) {

        UserPrinciple newUser = new UserPrinciple(
                null,
                accountCredential.getUsername(),
                null,
                accountCredential.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );

        userDetailsManager.createUser(newUser);

        return ResponseEntity.ok("OK");
    }


    @GetMapping("/gg")
    public String gg() {
        return "GG";
    }

}
