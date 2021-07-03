package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.security.authtication.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Map;

@Service
public class JwtService {



    private final static String KEY = "j;kladsjk;xmlsdfjie38881232";

    private final static String JWT_CLAIM_USERID = "userId";
    private final static String JWT_CLAIM_USERNAME = "username";
    private final static String JWT_CLAIM_ROLES = "roles";
    private final static String JWT_CLAIM_EMAIL = "email";
    private final static String ISSUER = "Jerry";

    // 5 hours
    private final static Integer JWT_DURATION = 60 * 5;


    /**
     * Generate User's token by userPrinciple without authentication
     *
     * @param userPrinciple this user principle will not be authenticated
     * @return token in String
     */
    public String generateUserToken(UserPrinciple userPrinciple) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, JWT_DURATION);

        Algorithm algorithmHS = Algorithm.HMAC256(KEY);

        return JWT.create()
                .withClaim(JWT_CLAIM_USERID, userPrinciple.getId())
                .withClaim(JWT_CLAIM_USERNAME, userPrinciple.getUsername())
                .withClaim(JWT_CLAIM_EMAIL, userPrinciple.getEmail())
                .withArrayClaim(JWT_CLAIM_ROLES, userPrinciple.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
                .withIssuer(ISSUER)
                .withExpiresAt(calendar.getTime())
                .sign(algorithmHS);
    }

    /**
     * Generate User's token by authenticated user principle
     *
     * @param authentication with authenticated user principle
     * @return token in string
     */
    public String generateUserToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return generateUserToken(userPrinciple);
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            System.out.println("Exception in verifying " + e.toString());
            return false;
        }
    }

    public Map<String, Object> parseToken(String token) {

        DecodedJWT jwt = JWT.decode(token);
        Long id = jwt.getClaim(JWT_CLAIM_USERID).asLong();
        String username = jwt.getClaim(JWT_CLAIM_USERNAME).asString();
        String[] roles = jwt.getClaim(JWT_CLAIM_ROLES).asArray(String.class);
        return Map.of(JWT_CLAIM_USERID, id,
                JWT_CLAIM_USERNAME, username,
                JWT_CLAIM_ROLES, roles);
    }

}
