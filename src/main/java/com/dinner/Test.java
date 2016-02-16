package com.dinner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Brut on 15.02.2016.
 */
@RestController
public class Test {
    @RequestMapping("/test")
    public String index() {
        System.out.println("sdfs");
        return "Greetings from Spring Boot!";
    }
}
