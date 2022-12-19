package com.boardsideproject.springbootside.controller;

import com.boardsideproject.springbootside.dto.LoginDTO;
import com.boardsideproject.springbootside.dto.PostFormDTO;
import com.boardsideproject.springbootside.dto.SignupFormDTO;
import com.boardsideproject.springbootside.service.interfaces.BoardService;
import com.boardsideproject.springbootside.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final MemberService memberService;
    private final BoardService boardService;

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignupFormDTO signupFormDTO) {
        return memberService.signup(signupFormDTO);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        return memberService.login(loginDTO);
    }

    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostFormDTO postFormDTO) {
        ResponseEntity responseEntity = boardService.save(postFormDTO);
        return responseEntity;
    }
}
