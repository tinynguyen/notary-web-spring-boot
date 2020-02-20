package com.congdat.notaryweb.controller;

import com.congdat.notaryweb.model.User;
import com.congdat.notaryweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping(value = "/cong-dong/tai-khoan")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/thong-tin")
    String info(@RequestParam(name = "id") String id, Model model, Principal principal) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "client/forum/info";
    }

    @PostMapping(value = "/thong-tin/cap-nhat-thong-tin")
    String updateInfo(@Valid @ModelAttribute(name = "user") User user,
                      BindingResult result,
                      @RequestParam(name = "id") String id,
                      @RequestParam(name = "new-password") String newPassword,
                      Model model) {
        User foundUser = userService.findById(id);
        if (result.hasErrors()) {
            return "client/forum/info";
        }
        boolean matchPassword = passwordEncoder.matches(user.getPassword(), foundUser.getPassword());
        if (!matchPassword) {
            model.addAttribute("wrongPassword", messageSource.getMessage("currentPassword.matches.false", null, null));
            return "client/forum/info";
        }
        User updatedUser = null;
        if (newPassword == null) {
            updatedUser = userService.updateInfoAndDontChangePassword(user, id);
            if (updatedUser != null) {
                model.addAttribute("user", updatedUser);
                model.addAttribute("updateSuccess", messageSource.getMessage("user.update.success", null, null));
            }
            return "client/forum/info";
        }
        updatedUser = userService.updateInfoAndChangePassword(user, newPassword, id);
        if (updatedUser != null) {
            model.addAttribute("user", updatedUser);
            model.addAttribute("updateSuccess", messageSource.getMessage("user.update.success", null, null));
        }
        return "client/forum/info";
    }
//
//    @PostMapping(value = "/thong-tin/cap-nhat-mat-khau")
//    String updatePassword(@Valid @ModelAttribute(name = "user") User user,
//                          BindingResult result,
//                          @RequestParam(name = "id") String id,
//                          @RequestParam(name = "current-password") String currentPassword,
//                          Model model) {
//        User foundUser = userService.findById(id);
//        boolean matchPassword = passwordEncoder.matches(currentPassword, user.getPassword());
//        user.setUsername(foundUser.getUsername());
//        user.setEmail(foundUser.getEmail());
//        user.setAvatarPath(foundUser.getAvatarPath());
//        user.setAvatarName(foundUser.getAvatarName());
//        user.setRoles(foundUser.getRoles());
//        user.setEnabled(foundUser.getEnabled());
//        user.setCreatedBy(foundUser.getCreatedBy());
//        user.setCreatedDate(foundUser.getCreatedDate());
//        if (result.hasErrors()) {
//            return "client/forum/info";
//        }
//        if (!matchPassword) {
//            model.addAttribute("wrongPassword", messageSource.getMessage("matches.currentPassword.false", null, null));
//            return "client/forum/info";
//        }
//        if (!currentPassword.equals(user.getPassword())) {
//            model.addAttribute("matchedTwoPassword", messageSource.getMessage("matches.newPassword.false", null, null));
//            return "client/forum/info";
//        }
//        return null;
//    }

    @PostMapping(value = "/thong-tin/hinh-dai-dien")
    String updateAvatar(@RequestParam(name = "id") String id,
                        @RequestParam(name = "avatar") MultipartFile avatar) {
        try {
            userService.updateAvatar(avatar, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/cong-dong/tai-khoan/thong-tin?id=" + id;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
