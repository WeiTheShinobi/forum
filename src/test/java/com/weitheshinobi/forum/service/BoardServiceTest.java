package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.repository.BoardRepository;
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

    @InjectMocks
    private BoardService boardService;
    @Mock
    private BoardRepository boardRepository;

    private Board b1, b2;
    private List<Board> bList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        b1 = new Board("test1", true);
        b2 = new Board("test2", true);
        bList = List.of(b1, b2);
    }

    @Test
    void getBoardList() {
        when(boardRepository.findAll()).thenReturn(bList);

        assertEquals(bList, boardService.getBoardList());
    }

//  測試：需要有模糊搜尋

    @Test
    void getBoardListByBoardName() {
        String queryString = "test";
        when(boardRepository.findByBoardNameLike("%" + queryString + "%"))
                .thenReturn(bList);

        assertEquals(bList, boardService.getBoardListByBoardName(queryString));

        verify(boardRepository).findByBoardNameLike("%" + queryString + "%");
        verify(boardRepository, never()).findByBoardNameLike(queryString);
    }

    @Test
    void saveBoard() {
        when(boardRepository.save(b1)).thenReturn(b1);

        assertEquals(b1, boardService.saveBoard(b1));

        verify(boardRepository).save(b1);
    }

    @Test
    void updateBoard() {
        when(boardRepository.findByBoardName("test1")).thenReturn(Optional.of(b1));

        boardService.updateBoard("test1", "test3", false);
        assertEquals("test3", b1.getBoardName());
        assertFalse(b1.isOpen());

        verify(boardRepository).findByBoardName("test1");
    }

    @Test
    void updateBoard_exception() {
        when(boardRepository.findByBoardName("test1")).thenReturn(Optional.ofNullable(null));

        assertThrows(EntityNotFoundException.class, () -> boardService.updateBoard("test","tttt",true));
    }

}