package com.example.blog.controller;

import com.example.blog.model.Blog;
import com.example.blog.model.Category;
import com.example.blog.service.BlogService;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CategoryService categoryService;

    @ModelAttribute("category")

    public List<Category> category() {
        return categoryService.getAll();
    }

    @GetMapping("/blog")
    public ModelAndView showBlog(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("showAllBlog");
        modelAndView.addObject("blogs", blogService.getAll(PageRequest.of(page,1, Sort.by("id"))));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createBlog() {
        ModelAndView modelAndView = new ModelAndView("createBlog");
        return modelAndView;
    }

    @PostMapping("/create")
    public String saveCreateBlog(Blog blog, @RequestParam MultipartFile upImg) {

        String nameFile = upImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(upImg.getBytes(), new File("I:/CodeGym/Module4/Bai_6/Demo_Blog/blog/src/main/webapp/WEB-INF/img/" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        blog.setImg("/img/" + nameFile);
        blogService.create(blog);
        return "redirect:/blog";
    }

    @GetMapping("/edit")
    public ModelAndView showFormEdit(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("editBlog");
        modelAndView.addObject("edit", blogService.findBlogById(id));
        return modelAndView;
    }
    @PostMapping("/edit")
    public String edit(@ModelAttribute Blog blog,@RequestParam MultipartFile upImg){
        if (!upImg.isEmpty()){
            try {
                FileCopyUtils.copy(upImg.getBytes(),
                        new File("I:\\CodeGym\\Module4\\Bai_6\\Demo_Blog\\blog\\src\\main\\webapp\\WEB-INF" + blog.getImg()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        blogService.create(blog);
        return "redirect:/blog";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        blogService.deleteById(id);
        return "redirect:/blog";
    }

    @GetMapping("/blogDetail")
    public ModelAndView showOneBlog(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("showBlogDetail");
        modelAndView.addObject("oneBlog", blogService.findBlogById(id));
        return modelAndView;
    }


}
