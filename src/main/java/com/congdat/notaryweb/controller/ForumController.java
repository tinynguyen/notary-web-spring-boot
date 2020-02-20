package com.congdat.notaryweb.controller;

import com.congdat.notaryweb.model.User;
import com.congdat.notaryweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(value = "/cong-dong")
public class ForumController {

    @Autowired
    private UserService userService;

    @RequestMapping
    String homeCommunity(Principal principal, Model model) {

        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            model.addAttribute("user", user);
        }
        return "client/forum/homepage";
    }
}
