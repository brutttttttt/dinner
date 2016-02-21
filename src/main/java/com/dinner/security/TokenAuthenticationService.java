package com.dinner.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Brut on 21.02.2016.
 */
@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;

    public String addAuthentication(HttpServletResponse response, User user) {
        String token = tokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);
        return token;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
