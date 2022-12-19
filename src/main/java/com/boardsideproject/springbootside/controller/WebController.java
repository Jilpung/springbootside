package com.boardsideproject.springbootside.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "board/home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "board/signup";
    }

    @GetMapping("/signin")
    public String login() {
        return "board/signin";
    }

    @GetMapping("/new")
    public String newPost() {
        return "board/new";
    }

}
