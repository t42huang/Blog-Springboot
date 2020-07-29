package com.org.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutmeShowController {

    @GetMapping("/about")
    public String about(){
        return "aboutme";
    }
}
