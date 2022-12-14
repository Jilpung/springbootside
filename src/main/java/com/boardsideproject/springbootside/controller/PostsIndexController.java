package com.boardsideproject.springbootside.controller;

import com.boardsideproject.springbootside.domain.Posts;
import com.boardsideproject.springbootside.service.PostsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostsIndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Posts> list = postsService.pageList(pageable);

        model.addAttribute("posts", list);

        return "/posts/list";
    }
}
