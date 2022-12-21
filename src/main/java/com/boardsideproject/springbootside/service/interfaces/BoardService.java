package com.boardsideproject.springbootside.service.interfaces;

import com.boardsideproject.springbootside.dto.DetailDTO;
import com.boardsideproject.springbootside.dto.ListDTO;
import com.boardsideproject.springbootside.dto.PostFormDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {

    ResponseEntity save(PostFormDTO postFormDTO);
    List<ListDTO> getAll();

    DetailDTO getDetail(Long id, String memberId);
}
