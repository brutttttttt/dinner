package com.dinner.filter;

import com.dinner.impl.UserDetailsServiceImpl;
import com.dinner.security.TokenAuthenticationService;
import com.dinner.security.UserAuthentication;
import com.dinner.security.UsernamePasswordAuthenticationToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by brut on 2/22/16.
 */
//@Service
public class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {


    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private UserDetailsService userDetailsService;

    public StatelessLoginFilter(String urlMapping, AuthenticationManager authManager) {

        super(new AntPathRequestMatcher(urlMapping));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
                request.getParameter("username"), request.getParameter("password"));
        return getAuthenticationManager().authenticate(loginToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authentication) throws IOException, ServletException {

        // Lookup the complete User object from the database and create an Authentication for it
        final User authenticatedUser = (User) userDetailsService.loadUserByUsername(authentication.getName());
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

        // Add the custom token as HTTP header to the response
        tokenAuthenticationService.addAuthentication(response, userAuthentication);

        // Add the authentication to the Security context
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
