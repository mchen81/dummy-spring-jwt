package com.example.demo.controller;

import com.example.demo.controller.dto.AccountCredential;
import com.example.demo.security.authtication.UserPrinciple;
import com.example.demo.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("account")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private UserDetailsManager userDetailsManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("signup")
    public ResponseEntity<String> createAccount(@RequestBody AccountCredential accountCredential) {

        // check if user exists
        if (userDetailsManager.userExists(accountCredential.getUsername())) {
            ResponseEntity.status(409).body("username is exited");
        }

        // In this example, the userDetailsManager.createUser will allocate the new user as a ROLE_USER
        // You can re-implement this in what you want
        UserPrinciple newUser = new UserPrinciple(
                null, // db will allocate a id, not to specify here
                accountCredential.getUsername(),
                null, // email is nullable
                accountCredential.getPassword(),
                null
        );

        userDetailsManager.createUser(newUser);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AccountCredential loginUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                ));

        return ResponseEntity.ok(Map.of("token", jwtService.generateUserToken(authentication)));
    }

}
