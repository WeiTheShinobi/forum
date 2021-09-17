package com.weitheshinobi.forum.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    private User u1, u2;

    @BeforeEach
    void setUp() {
        u1 = new User();
        u2 = new User();
        u1.setUsername("u1");
        u2.setUsername("u2");
    }

    @Test
    void getUserList() throws Exception {
        when(userService.getUserList(0)).thenReturn(List.of(u1, u2));
        String listJson = objectMapper.writeValueAsString(List.of(u1, u2));

        mockMvc.perform(get("/api/userlist/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().json(listJson));
    }

    @Test
    void saveUser() throws Exception {
        when(userService.saveUser(u1)).thenReturn(u1);
        String userJson = objectMapper.writeValueAsString(u1);

        mockMvc.perform(post("/api/user/save")
                        .contentType(APPLICATION_JSON)
                        .content(userJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}