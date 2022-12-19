package com.boardsideproject.springbootside.service.interfaces;

import com.boardsideproject.springbootside.dto.LoginDTO;
import com.boardsideproject.springbootside.dto.SignupFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignupFormDTO signupFormDTO);

    ResponseEntity login(LoginDTO loginDTO);
}
