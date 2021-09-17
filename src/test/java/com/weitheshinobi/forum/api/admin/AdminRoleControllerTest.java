package com.weitheshinobi.forum.api.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminRoleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    private User u1, u2;
    private Role role;
    private RoleToUser roleToUser;

    @BeforeEach
    void setUp() {
        roleToUser = new RoleToUser();
        roleToUser.setRoleName("role");
        roleToUser.setUserName("user");
        role = new Role();
        role.setName("role1");
        u1 = new User();
        u2 = new User();
        u1.setUsername("u1");
        u2.setUsername("u2");
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void saveRole() throws Exception {
        when(userService.saveRole(role)).thenReturn(role);
        String roleJson = objectMapper.writeValueAsString(role);

        mockMvc.perform(post("/api/admin/role/save")
                        .contentType(APPLICATION_JSON)
                        .content(roleJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    void addRoleToUser() throws Exception {
        String roleToUserJson = objectMapper.writeValueAsString(roleToUser);

        mockMvc.perform(post("/api/admin/role/addtouser")
                        .contentType(APPLICATION_JSON)
                        .content(roleToUserJson))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService).addRoleToUser("user", "role");
    }

}