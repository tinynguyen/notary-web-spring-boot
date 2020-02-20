package com.congdat.notaryweb.controller;

import com.congdat.notaryweb.model.News;
import com.congdat.notaryweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public String home(Model model) {
        List<News> allNews = newsService.findAll();
        model.addAttribute("allNews", allNews);
        return "client/homepage";
    }

}
