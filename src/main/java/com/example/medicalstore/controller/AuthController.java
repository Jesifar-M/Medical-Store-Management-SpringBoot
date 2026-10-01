package com.example.medicalstore.controller;

import com.example.medicalstore.entity.User;
import com.example.medicalstore.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user,
                         Model model) {

        User savedUser = userService.signup(user);

        if (savedUser == null) {
            model.addAttribute("error",
                    "Email already registered!");
            return "signup";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.login(email, password);

        if (user == null) {
            model.addAttribute("error",
                    "Invalid email or password!");
            return "login";
        }

        session.setAttribute("loggedUser", user);

        return "redirect:/medicines";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}