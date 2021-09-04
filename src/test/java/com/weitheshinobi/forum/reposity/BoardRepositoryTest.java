package com.weitheshinobi.forum.reposity;

import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.repository.BoardRepository;
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

    private Board b1, b2;

    @BeforeEach
    void setUp() {
        b1 = new Board("test1", true);
        b2 = new Board("test2", true);
        boardRepository.saveAll(List.of(b1, b2));
    }

    @Test
    void findByBoradName() {
        assertEquals(List.of(b1), boardRepository.findByBoardNameLike("test1"));
        assertEquals(List.of(b2), boardRepository.findByBoardNameLike("test2"));

        assertEquals(List.of(b1, b2), boardRepository.findByBoardNameLike("%test%"));
        assertNotEquals(List.of(b1, b2), boardRepository.findByBoardNameLike("test"));

        assertEquals(1, boardRepository.findByBoardNameLike("%test%").get(0).getId());
        assertEquals(2, boardRepository.findByBoardNameLike("%test%").get(1).getId());
    }

}