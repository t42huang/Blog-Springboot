package com.org.blog.web.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class TagController {

    @GetMapping("/tags")
    public String list(){
        return "admin/tags";
    }

}
