package com.congdat.notaryweb.controller;

import com.congdat.notaryweb.model.User;
import com.congdat.notaryweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/dang-nhap")
    String formLogin() {
        return "client/login";
    }

    @GetMapping(value = "/dang-ky")
    String formRegister(Model model) {
        model.addAttribute("user", new User());
        return "client/register";
    }

    @PostMapping(value = "/dang-ky")
    String register(@Valid @ModelAttribute(name = "user") User user,
                    BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "client/register";
        }
        User u = null;
        try {
            u = userService.save(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (u != null) {
            model.addAttribute("user", u);
        }
        return "client/register-success";

    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
