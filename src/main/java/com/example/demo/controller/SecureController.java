package com.example.demo.controller;

import com.example.demo.controller.dto.AccountCredential;
import com.example.demo.jwt.JwtService;
import com.example.demo.security.authtication.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("secured")
public class SecureController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<Map<?, ?>> secured() {

        UserPrinciple user =
                (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "roles", user.getAuthorities().toString()
        ));
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> login(@RequestBody AccountCredential loginUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                ));

        return ResponseEntity.ok(Map.of("token", jwtService.generateUserToken(authentication)));
    }
}
