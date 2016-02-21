package com.dinner.controllers;

import com.dinner.dao.UsersDao;
import com.dinner.models.AuthenticationToken;
import com.dinner.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brut on 16.02.2016.
 */
@Controller
public class AuthenticationController {

    private final UsersDao usersDao;

    @Autowired
    public MainController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

   /* @Autowired
    private UserDetailsService UserDetailsService;
*/
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager am, UserDetailsService userDetailsService) {
        this.authenticationManager = am;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/create")
    public @ResponseBody
    UserDto create(@RequestParam(value="login") String login,
                   @RequestParam(value="email") String email,
                   @RequestParam(value="password") String password) {

        UserDto user = usersDao.save(new UserDto(login, email, password));
        return user;
    }

    @RequestMapping(value = "/auth", method = { RequestMethod.POST })
    public @ResponseBody
    AuthenticationToken login(@RequestParam(value="login") String username,
                 @RequestParam(value="password") String password,
                 HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        UserDetails details = this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final Map<String, Boolean> roles = new HashMap<String, Boolean>();

        for (GrantedAuthority authority : details.getAuthorities()) {
            roles.put(authority.toString(), Boolean.TRUE);
        }

        return new AuthenticationToken(details.getUsername(), roles, session.getId());
    }

}
