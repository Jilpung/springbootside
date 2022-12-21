package com.boardsideproject.springbootside.controller;

import com.boardsideproject.springbootside.dto.DetailDTO;
import com.boardsideproject.springbootside.dto.ListDTO;
import com.boardsideproject.springbootside.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardServiceImpl boardService;
    @GetMapping("/")
    public String index(Model model) {
        List<ListDTO> posts = boardService.getAll();
        model.addAttribute("posts", posts);
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

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, @CookieValue("id") String memberId) {
        DetailDTO post = boardService.getDetail(id, memberId);
        model.addAttribute("post", post);
        return "board/detail";
    }
}
