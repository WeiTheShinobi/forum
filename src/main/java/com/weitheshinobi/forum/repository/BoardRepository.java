package com.weitheshinobi.forum.repository;

import com.weitheshinobi.forum.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByBoardNameContains (String boardName);

    Optional<Board> findByBoardName(String boardName);

}
