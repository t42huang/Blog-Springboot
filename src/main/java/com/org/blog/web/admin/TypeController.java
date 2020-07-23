package com.org.blog.web.admin;

import com.org.blog.po.Type;
import com.org.blog.service.TypeService;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public  String types(@PageableDefault(size =10, sort ={"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }


    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, RedirectAttributes attributes){
        Type t = typeService.saveType(type);
        if(t == null){
            attributes.addFlashAttribute("message","操作失败");
        }else{
            attributes.addFlashAttribute("message","操作成功");
        }
        return "redirect:/admin/types";
    }
}
