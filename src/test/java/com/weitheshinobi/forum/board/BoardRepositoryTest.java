package com.weitheshinobi.forum.board;

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
        assertEquals(List.of(b1), boardRepository.findByBoradNameLike("test1"));
        assertEquals(List.of(b2), boardRepository.findByBoradNameLike("test2"));

        assertEquals(List.of(b1, b2), boardRepository.findByBoradNameLike("%test%"));
        assertNotEquals(List.of(b1, b2), boardRepository.findByBoradNameLike("test"));

        assertEquals(1, boardRepository.findByBoradNameLike("%test%").get(0).getId());
        assertEquals(2, boardRepository.findByBoradNameLike("%test%").get(1).getId());
    }

}