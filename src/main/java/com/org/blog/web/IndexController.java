package com.org.blog.web;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name){
//        int i = 9/0;
        System.out.println("------index------");
        return "index";
    }
    @GetMapping("/test")
    public String test(){
        String blog = null;
        if(blog == null){
            try {
                throw new NotFoundException("Blog is not exist");
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        return "index";
    }
}
