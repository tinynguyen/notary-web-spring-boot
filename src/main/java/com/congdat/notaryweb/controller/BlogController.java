package com.congdat.notaryweb.controller;

import com.congdat.notaryweb.model.Category;
import com.congdat.notaryweb.model.News;
import com.congdat.notaryweb.service.CategoryService;
import com.congdat.notaryweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/blog")
public class BlogController {

    private String notaryLawCategoryId;
    private String civilLawCategoryId;
    private String enterpriseLawCategoryId;
    private String marriageLawCategoryId;
    private String landLawCategoryId;

    @Autowired
    private NewsService newsService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    String blogs(Model model) {
        getAllCategoryId();
        List<News> allNotaryLawNews = newsService.findAllByCategory(notaryLawCategoryId);
        List<News> allCivilLawNews = newsService.findAllByCategory(civilLawCategoryId);
        List<News> allEnterpriseLawNews = newsService.findAllByCategory(enterpriseLawCategoryId);
        List<News> allMarriedLawNews = newsService.findAllByCategory(marriageLawCategoryId);
        List<News> allLandLawNews = newsService.findAllByCategory(landLawCategoryId);
        model.addAttribute("allNotaryLawNews", allNotaryLawNews);
        model.addAttribute("allCivilLawNews", allCivilLawNews);
        model.addAttribute("allEnterpriseLawNews", allEnterpriseLawNews);
        model.addAttribute("allMarriedLawNews", allMarriedLawNews);
        model.addAttribute("allLandLawNews", allLandLawNews);
        return "client/blogs";
    }

    private void getAllCategoryId() {
        List<Category> categories = categoryService.findAll();
        for (int i = 0; i < categories.size(); i++) {
            switch (i) {
                case 0:
                    notaryLawCategoryId = categories.get(i).getId();
                    break;
                case 1:
                    civilLawCategoryId = categories.get(i).getId();
                    break;
                case 2:
                    enterpriseLawCategoryId = categories.get(i).getId();
                    break;
                case 3:
                    marriageLawCategoryId = categories.get(i).getId();
                    break;
                case 4:
                    landLawCategoryId = categories.get(i).getId();
                    break;
            }
        }
    }
}
