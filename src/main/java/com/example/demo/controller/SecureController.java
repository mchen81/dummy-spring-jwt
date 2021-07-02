package com.example.demo.controller;

import com.example.demo.controller.dto.AccountCredential;
import com.example.demo.jwt.JwtService;
import com.example.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secured")
public class SecureController {

    @Autowired
    private JwtService jwtService;

    @GetMapping
    public String secured() {

        AppUser user =
                (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return String.format("You are authenticated. \n Id: %d\n Username: %s\n",
                user.getId(),
                user.getUsername());
    }

    @PostMapping
    public String login(@RequestBody AccountCredential accountCredential) {
        return jwtService.generateUserToken(accountCredential);
    }
}
