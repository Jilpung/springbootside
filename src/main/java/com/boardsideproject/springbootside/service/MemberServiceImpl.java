package com.boardsideproject.springbootside.service;

import com.boardsideproject.springbootside.domain.member.Member;
import com.boardsideproject.springbootside.domain.member.MemberRole;
import com.boardsideproject.springbootside.dto.LoginDTO;
import com.boardsideproject.springbootside.dto.SignupFormDTO;
import com.boardsideproject.springbootside.repository.MemberRepository;
import com.boardsideproject.springbootside.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity signup(SignupFormDTO signupFormDTO) {
        Optional<Member> member = memberRepository.findById(signupFormDTO.getId());

        if (member.isEmpty()) {
            Member newMember = Member.builder()
                    .id(signupFormDTO.getId())
                    .password(signupFormDTO.getPassword())
                    .name(signupFormDTO.getName())
                    .role(MemberRole.USER)
                    .build();

            memberRepository.save(newMember);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

    public ResponseEntity login(LoginDTO loginDTO) {
        Optional<Member> member = memberRepository.findById(loginDTO.getId());
        Member memberEntity = member.orElse(null);

        if (member == null) {
            return new ResponseEntity("해당 아이디를 가진 회원이 존재하지 않습니다.", HttpStatus.OK);
        }
        if (memberEntity.getPassword().equals(loginDTO.getPassword())) {
            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("비밀번호가 일치하지 않습니다.", HttpStatus.OK);
        }
    }
}