package com.org.blog.web.admin;

import com.org.blog.po.Tag;
import com.org.blog.service.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public  String tags(@PageableDefault(size =3, sort ={"id"}, direction = Sort.Direction.DESC)
                                 Pageable pageable, Model model){
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }
//   新增标签 加入model, new type,这样在添加后台校验的时候才能获得object /name
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }
// 调入界面修改标签
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){
//        重复验证
        Tag t0 = tagService.getTagByName(tag.getName());
        if (t0 != null){
            result.rejectValue("name", "nameError", "不能重复添加");
        }

        if(result.hasErrors()){
            return "admin/tags-input";
        }

        Tag t = tagService.saveTag(tag);
        if(t != null){
            attributes.addFlashAttribute("message"," 新增成功");
        }else {
            attributes.addFlashAttribute("message"," 新增失败");
        }
        return "redirect:/admin/tags";
    }
//修改标签
    @PostMapping("/tags/{id}")
    public String editTag(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes){
//        重复校验
        Tag t0 = tagService.getTagByName(tag.getName());
        if(t0 != null){
            result.rejectValue("name", "nameError","不能添加重复标签");
        }

//         后台校验
        if(result.hasErrors()){
            return "/admin/tags-input";
        }
        Tag t = tagService.updateTag(id, tag);
        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

//    删除标签
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}
