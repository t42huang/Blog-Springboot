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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public  String types(@PageableDefault(size =3, sort ={"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model){
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }

//   新增标签 加入model, new type,这样在添加后台校验的时候才能获得object /name
    @GetMapping("/types/input")
    public String input(Model model) {
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

//    编辑type-跳转到修改的界面
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){
//        重复校验
        Type t1 = typeService.getTypeByName(type.getName());
        if(t1 != null){
            result.rejectValue("name", "nameError","不能添加重复分类");
        }

        //后台校验
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.saveType(type);

        if(t == null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

//  编写修改方法
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes){
//        重复校验. 注意 type 和Binding 一定要在向邻近的校验才有效果
        Type t2 = typeService.getTypeByName(type.getName());
        if(t2 != null){
            result.rejectValue("name", "nameError","不能添加重复分类");
        }

        //后台校验
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type t = typeService.updateType(id, type);

        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }


}
