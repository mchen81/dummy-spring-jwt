package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.controller.dto.AccountCredential;
import com.example.demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String KEY = "j;kladsjk;xmlsdfjie38881232";

    public String generateUserToken(AccountCredential accountCredential) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(accountCredential.getUsername(), accountCredential.getPassword());

        authentication = authenticationManager.authenticate(authentication);

        assert authentication.getPrincipal() instanceof AppUser;
        AppUser user = (AppUser) authentication.getPrincipal();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 1);

        Algorithm algorithmHS = Algorithm.HMAC256(KEY);

        return JWT.create()
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .withArrayClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withIssuer("Jerry")
                .withExpiresAt(calendar.getTime())
                .sign(algorithmHS);

    }

    public Map<String, Object> parseToken(String token) {
        return Map.of("username", "Fake", "token", token);
    }

}
