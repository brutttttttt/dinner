package com.dinner;

import com.dinner.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Brut on 15.02.2016.
 */
@Controller
public class Test {

    @RequestMapping("/index")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @RequestMapping("/test2")
    public @ResponseBody User testJson(@RequestParam(value="name", required=false) String name) {
        return new User("test","troldfgo");
    }
}
