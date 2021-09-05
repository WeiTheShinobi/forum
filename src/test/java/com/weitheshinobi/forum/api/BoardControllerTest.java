package com.weitheshinobi.forum.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = {BoardController.class})
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BoardService boardService;

    private Board b1, b2;
    private List<Board> bList;

    @BeforeEach
    void setUp() {
        b1 = new Board("b1", true);
        b2 = new Board("b2", true);
        b1.setId(1L);
        b2.setId(2L);
        bList = List.of(b1, b2);
    }

    @Test
    void listBoard() throws Exception {
        when(boardService.getBoardList()).thenReturn(bList);
        String bListJson = objectMapper.writeValueAsString(bList);

        mockMvc.perform(get("/boardlist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(bListJson));
    }

}