package com.weitheshinobi.forum.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    void listBoard() {
        when(boardRepository.findAll()).thenReturn(bList);

        assertEquals(bList, boardService.listBoard());
    }

//  測試：需要有模糊搜尋

    @Test
    void listBoardByBoardName() {
        String queryString = "test";
        when(boardRepository.findByBoradNameLike("%" + queryString + "%"))
                .thenReturn(bList);

        assertEquals(bList, boardService.listBoardByBoardName(queryString));

        verify(boardRepository).findByBoradNameLike("%" + queryString + "%");
        verify(boardRepository, never()).findByBoradNameLike(queryString);
    }

    @Test
    void createBoard() {
        when(boardRepository.save(b1)).thenReturn(b1);

        assertEquals(b1, boardService.createBoard("test1"));

        verify(boardRepository).save(b1);
    }

    @Test
    void updateBoard_updateBoardName() {
        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(b1));
        when(boardRepository.save(b1)).thenReturn(b1);

        Board updateName = boardService.updateBoard(anyLong(), "testUpdate", true);
        assertEquals("testUpdate", updateName.getBoradName());
    }

    @Test
    void updateBoard_updateIsOpen() {
        when(boardRepository.findById(anyLong())).thenReturn(Optional.of(b1));
        when(boardRepository.save(b1)).thenReturn(b1);

        Board updateFalse = boardService.updateBoard(anyLong(), "testUpdate", false);
        assertFalse(updateFalse.isOpen());
        Board updateTrue = boardService.updateBoard(anyLong(), "testUpdate", true);
        assertTrue(updateTrue.isOpen());
    }

}