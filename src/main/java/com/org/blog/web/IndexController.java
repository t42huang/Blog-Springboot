package com.org.blog.web;

import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
//
//    @GetMapping("/blog")
//    public String blog(){
//        return "blog";
//    }
//
//    @GetMapping("/types")
//    public String types(){
//        return "types";
//    }
//
//    @GetMapping("/archives")
//    public String archives(){
//        return "archives";
//    }
//
//    @GetMapping("/aboutme")
//    public String aboutme(){
//        return "aboutme";
//    }
//
//    @GetMapping("/{id}/{name}")
//    public String index(@PathVariable Integer id, @PathVariable String name){
////        int i = 9/0;
//        System.out.println("------test------");
//        return "test";
//    }
//    @GetMapping("/test")
//    public String test(){
//        String blog = null;
//        if(blog == null){
//            try {
//                throw new NotFoundException("Blog is not exist");
//            } catch (NotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return "test";
//    }
}
