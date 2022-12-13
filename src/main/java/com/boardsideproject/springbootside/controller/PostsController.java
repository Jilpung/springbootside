package com.boardsideproject.springbootside.controller;

import com.boardsideproject.springbootside.dto.PostsDto;
import com.boardsideproject.springbootside.repository.PostsRepository;
import com.boardsideproject.springbootside.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;
    private final PostsRepository postsRepository;

//    @PostMapping("/posts")
//    public ResponseEntity save(@RequestBody PostsDto.Request dto) {
//        return ResponseEntity.ok(postsService.save(dto));
//    }

    @GetMapping("/posts/{id}")
    public ResponseEntity read(@PathVariable Long id) {
        return ResponseEntity.ok(postsRepository.findById(id));
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody PostsDto.Request dto) {
        postsService.update(id, dto);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        postsService.delete(id);
        return ResponseEntity.ok(id);
    }
}
