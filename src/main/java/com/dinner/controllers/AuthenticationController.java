package com.dinner.controllers;

import com.dinner.dao.UsersDao;
import com.dinner.models.AuthenticationToken;
import com.dinner.models.UserDto;
import com.dinner.security.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brut on 16.02.2016.
 */
@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenAuthenticationService jwtToken;

   /* @Autowired
    public AuthenticationController(AuthenticationManager am, UserDetailsService userDetailsService, UsersDao usersDao, TokenAuthenticationService jwtToken) {
        this.authenticationManager = am;
        this.userDetailsService = userDetailsService;
        this.usersDao = usersDao;
        this.jwtToken = jwtToken;
    }*/

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public @ResponseBody
    UserDto create(@RequestParam(value="username") String login,
                   @RequestParam(value="email") String email,
                   @RequestParam(value="password") String password) {

        UserDto user = usersDao.save(new UserDto(login, email, password));
        return user;
    }

    @RequestMapping(value = "/auth", method = { RequestMethod.POST })
    public @ResponseBody
    AuthenticationToken login(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password,
                              HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        String jwt = jwtToken.addAuthentication(response, (User) details);

        final Map<String, Boolean> roles = new HashMap<String, Boolean>();

        for (GrantedAuthority authority : details.getAuthorities()) {
            roles.put(authority.toString(), Boolean.TRUE);
        }

        return new AuthenticationToken(details.getUsername(), roles, jwt);
    }
}
