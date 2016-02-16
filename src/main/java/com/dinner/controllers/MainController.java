package com.dinner.controllers;

import com.dinner.dao.UsersDao;
import com.dinner.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Brut on 16.02.2016.
 */
@Controller
public class MainController {

    private final UsersDao usersDao;

    @Autowired
    public MainController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @RequestMapping("/create")
    public @ResponseBody User create(@RequestParam(value="login") String login,
                                     @RequestParam(value="email") String email,
                                     @RequestParam(value="password") String password) {

        User user = usersDao.save(new User(login, email, password));
        return user;
    }

}
