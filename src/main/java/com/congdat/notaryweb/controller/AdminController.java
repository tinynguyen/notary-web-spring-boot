package com.congdat.notaryweb.controller;

import com.congdat.notaryweb.model.Category;
import com.congdat.notaryweb.model.News;
import com.congdat.notaryweb.model.User;
import com.congdat.notaryweb.service.CategoryService;
import com.congdat.notaryweb.service.NewsService;
import com.congdat.notaryweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @RequestMapping
    String home(Model model) {
        model.addAttribute("allNews", newsService.findAll());
        return "admin/homepage";
    }

    @GetMapping(value = "/create-category")
    String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/createCategory";
    }

    @PostMapping(value = "/create-category")
    String processCreateCategory(@ModelAttribute(name = "category") Category category, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        categoryService.save(category, user.getId());
        return "admin/createCategory";
    }
}
