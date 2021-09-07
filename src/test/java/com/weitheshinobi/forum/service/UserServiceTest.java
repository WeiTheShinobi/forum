package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
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
        user.setUsername("username");
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
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        userService.addRoleToUser("username", "role");

        assertIterableEquals(user.getRoles(), List.of(role));
    }

    @Test
    void addRoleToUser_roleException() {
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        assertThrows(EntityNotFoundException.class, () -> userService.addRoleToUser("username", "role"));
    }

    @Test
    void addRoleToUser_userException() {
        when(roleRepository.findByName("role")).thenReturn(Optional.of(role));

        assertThrows(EntityNotFoundException.class, () -> userService.addRoleToUser("username", "role"));
    }

}