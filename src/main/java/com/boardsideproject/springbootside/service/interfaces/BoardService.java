package com.boardsideproject.springbootside.service.interfaces;

import com.boardsideproject.springbootside.dto.PostFormDTO;
import org.springframework.http.ResponseEntity;

public interface BoardService {

    ResponseEntity save(PostFormDTO postFormDTO);
}
