package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.repository.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;
    @InjectMocks
    private BoardService boardService;

    private Board testBoard1, testBoard2;
    private List<Board> testBoardList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBoard1 = new Board();
        testBoard2 = new Board();
        testBoard1.setBoradName("test1");
        testBoard2.setBoradName("test2");
        testBoardList = List.of(testBoard1, testBoard2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listBoard() {
        when(boardRepository.findAll())
                .thenReturn(testBoardList);
        assertEquals(List.of(testBoard1, testBoard2), boardService.listBoard());
    }

//  測試：需要有模糊搜尋

    @Test
    void listBoardByBoardName() {
        String queryString = "test";
        when(boardRepository.findByBoradNameLike("%" + queryString + "%"))
                .thenReturn(testBoardList);

        assertEquals(testBoardList, boardService.listBoardByBoardName(queryString));

        verify(boardRepository).findByBoradNameLike("%" + queryString + "%");
        verify(boardRepository, never()).findByBoradNameLike(queryString);
    }

    @Test
    void createBoard() {
        when(boardRepository.save(testBoard1)).thenReturn(testBoard1);

        assertEquals(testBoard1, boardService.createBoard("test1"));

        verify(boardRepository).save(testBoard1);
    }

    @Test
    void updateBoard() {
        Long testlong = 1L;
        when(boardRepository.findById(testlong)).thenReturn(Optional.of(testBoard1));
        when(boardRepository.save(testBoard1)).thenReturn(testBoard1);

        assertEquals("testUpdate", boardService.updateBoard(testlong, "testUpdate").getBoradName());

        verify(boardRepository).findById(testlong);
    }
}