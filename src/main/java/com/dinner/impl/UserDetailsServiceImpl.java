package com.dinner.impl;

import com.dinner.dao.UsersDao;
import com.dinner.models.UserDto;
import com.dinner.models.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersDao usersDao;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {

        UserDto user = usersDao.findOne(login);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));

        User userDetails = new User(user.getLogin(), user.getPassword(), roles);

        return userDetails;
    }
}
