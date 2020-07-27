package com.org.blog.web.admin;

import com.org.blog.po.Blog;
import com.org.blog.service.BlogService;
import com.org.blog.service.TagService;
import com.org.blog.service.TypeService;
import com.org.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    private static final String INPUT= "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";


    @GetMapping("/blogs")
    public String list(@PageableDefault(size=3, sort={"updateTime"}, direction =Sort.Direction.DESC)
                       Pageable pageable,
                       BlogQuery blog,
                       Model model){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        model.addAttribute("types", typeService.listType());

        return LIST;
    }
    //局部刷新
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size=3, sort={"updateTime"}, direction =Sort.Direction.DESC)
                               Pageable pageable,
                       BlogQuery blog,
                       Model model){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        //局部刷新
        return "admin/blogs :: blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    
}
