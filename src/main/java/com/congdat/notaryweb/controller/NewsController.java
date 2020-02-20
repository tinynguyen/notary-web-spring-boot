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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class NewsController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/create-post")
    String createPost(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("news", new News());
        return "admin/createPost";
    }

    @PostMapping(value = "/create-post")
    String processCreatePost(@ModelAttribute(name = "news") News news, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        newsService.save(news, user.getId());
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit-post")
    String editPost(@RequestParam(name = "id") String newsId, Model model) {
        List<Category> categories = categoryService.findAll();
        News news = newsService.findById(newsId);
        model.addAttribute("categories", categories);
        model.addAttribute("news", news);
        return "admin/editPost";
    }

    @PostMapping(value = "/edit-post")
    String processEditPost(@ModelAttribute(name = "news") News news, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        newsService.update(news, user.getId());
        return "redirect:/admin";
    }

    @GetMapping(value = "delete-post")
    String deletePost(@RequestParam(name = "id") String newsId) {
        newsService.delete(newsId);
        return "redirect:/admin";
    }
}
