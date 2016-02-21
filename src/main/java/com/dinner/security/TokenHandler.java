package com.dinner.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by Brut on 21.02.2016.
 */
@Component
public class TokenHandler {

    @Autowired
    private UserDetailsService userService;

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey("secrets")
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (User) userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, "secrets")
                .compact();
    }
}
