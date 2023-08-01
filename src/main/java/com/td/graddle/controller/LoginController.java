package com.td.graddle.controller;

import com.td.graddle.model.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

//    @GetMapping("/login")
//    public String showLoginPage() {
//        return "login";
//    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logOut() {
        return "redirect:/login";
    }

}