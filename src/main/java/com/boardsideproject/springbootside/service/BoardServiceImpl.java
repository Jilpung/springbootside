package com.boardsideproject.springbootside.service;

import com.boardsideproject.springbootside.domain.board.Board;
import com.boardsideproject.springbootside.domain.member.Member;
import com.boardsideproject.springbootside.domain.member.MemberRole;
import com.boardsideproject.springbootside.dto.DetailDTO;
import com.boardsideproject.springbootside.dto.ListDTO;
import com.boardsideproject.springbootside.dto.PostFormDTO;
import com.boardsideproject.springbootside.repository.BoardRepository;
import com.boardsideproject.springbootside.repository.MemberRepository;
import com.boardsideproject.springbootside.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity save(PostFormDTO postFormDTO) {
        Optional<Member> member = memberRepository.findById(postFormDTO.getMemberId());
        if(member.isPresent()){
            Member memberEntity = member.get();

            Board post = Board.builder()
                    .title(postFormDTO.getTitle())
                    .content(postFormDTO.getContent())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .adminViews(0)
                    .userViews(0)
                    .likes(0)
                    .member(memberEntity)
                    .build();

            boardRepository.save(post);

            return new ResponseEntity("success", HttpStatus.OK);
        }else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }

    @Override
    public List<ListDTO> getAll() {
        List<Board> posts = boardRepository.findAll();
        List<ListDTO> list = new ArrayList<>();

        for(Board post : posts) {
            Member member = post.getMember();

            ListDTO dto = ListDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreatedAt())
                    .userViews(post.getUserViews())
                    .adminViews(post.getAdminViews())
                    .memberName(member.getName())
                    .build();

            list.add(dto);
        }
        return list;
    }

    @Override
    public DetailDTO getDetail(Long id, String memberId) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        Member member = boardEntity.getMember();

        if(!memberId.equals(member.getId())){
            if(member.getRole().equals(MemberRole.ADMIN)){
                boardEntity.countAdmin();
            }else{
                boardEntity.countUser();
            }
        }

        DetailDTO detailDTO = DetailDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt())
                .userViews(boardEntity.getUserViews())
                .adminViews(boardEntity.getAdminViews())
                .memberName(member.getName())
                .build();

        return detailDTO;
    }
}
