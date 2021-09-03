package com.weitheshinobi.forum.board;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    @MockBean
    private BoardService boardService;

    private ObjectMapper objectMapper;
    private Board b1, b2;
    private List<Board> bList;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        b1 = new Board("b1", true);
        b2 = new Board("b2", true);
        b1.setId(1L);
        b2.setId(2L);
        bList = List.of(b1, b2);
    }

    @Test
    void listBoard() throws Exception {
        when(boardService.listBoard()).thenReturn(bList);
        String jsonString = objectMapper.writeValueAsString(bList);

        mockMvc.perform(get("/listBoard"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonString));

    }

}