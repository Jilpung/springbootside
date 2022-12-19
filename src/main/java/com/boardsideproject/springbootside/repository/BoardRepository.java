package com.boardsideproject.springbootside.repository;

import com.boardsideproject.springbootside.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
