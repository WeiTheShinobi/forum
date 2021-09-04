package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    private Role role;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role();
        role.setName("role");
        user = new User();
        user.setEmail("email");
    }

    @Test
    void getUser() {
    }

    @Test
    void getUserList() {
    }

    @Test
    void saveUser() {

    }

    @Test
    void saveRole() {
    }

    @Test
    void addRoleToUser() {
        when(roleRepository.findByName("role")).thenReturn(Optional.of(role));
        when(userRepository.findByEmail("email")).thenReturn(Optional.of(user));

        userService.addRoleToUser("email", "role");

        assertIterableEquals(user.getRoles(), List.of(role));
    }

}