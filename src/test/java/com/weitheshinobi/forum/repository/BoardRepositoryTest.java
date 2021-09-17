package com.weitheshinobi.forum.repository;

import com.weitheshinobi.forum.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    private Board b1, b2, b3;

    @BeforeEach
    void setUp() {
        b1 = new Board("test1", true);
        b2 = new Board("test2", false);
        b3 = new Board("333te", true);
        boardRepository.saveAll(List.of(b1, b2, b3));
    }

    @Test
    void findByBoradName() {
        assertEquals(List.of(b1, b2), boardRepository.findByBoardNameContains("test"));
        assertEquals(List.of(b3), boardRepository.findByBoardNameContains("3"));
        assertEquals(List.of(b1, b2, b3), boardRepository.findByBoardNameContains("t"));
        assertEquals(List.of(b1, b2, b3), boardRepository.findByBoardNameContains(""));

        assertEquals(Collections.emptyList(), boardRepository.findByBoardNameContains(" "));

        assertEquals(1, boardRepository.findByBoardNameContains("").get(0).getId());
        assertEquals(2, boardRepository.findByBoardNameContains("").get(1).getId());
    }

}