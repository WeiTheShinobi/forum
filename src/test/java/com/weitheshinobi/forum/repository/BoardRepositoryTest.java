package com.weitheshinobi.forum.repository;

import com.weitheshinobi.forum.entity.Article;
import com.weitheshinobi.forum.entity.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    private Board board1, board2;

    @BeforeEach
    void setUp() {
        board1 = new Board();
        board2 = new Board();
        board1.setBoradName("test1");
        board2.setBoradName("test2");
        boardRepository.saveAll(List.of(board1, board2));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByBoradName() {
        assertEquals(List.of(board1), boardRepository.findByBoradNameLike("test1"));
        assertEquals(List.of(board2), boardRepository.findByBoradNameLike("test2"));
        assertEquals(List.of(board1, board2), boardRepository.findByBoradNameLike("%test%"));
        assertNotEquals(List.of(board1, board2), boardRepository.findByBoradNameLike("test"));

        assertEquals(1, boardRepository.findByBoradNameLike("%test%").get(0).getId());
        assertEquals(2, boardRepository.findByBoradNameLike("%test%").get(1).getId());
    }

}