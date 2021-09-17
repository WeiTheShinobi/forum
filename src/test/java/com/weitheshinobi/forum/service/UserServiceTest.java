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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private Role role;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        role = new Role();
        role.setName("role");
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
    }

    @Test
    void getUser() {
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        User testUser = userService.getUser("username");
        assertEquals(user, testUser);
    }

    @Test
    void getUser_Exception() {
        assertThrows(EntityNotFoundException.class, () -> userService.getUser(anyString()));
    }

    @Test
    void getUserList() {
        when(userRepository.findAll(PageRequest.of(10, 50, Sort.Direction.DESC, "createdDate"))).thenReturn(Page.empty());

        userService.getUserList(10);

        verify(userRepository).findAll(PageRequest.of(10, 50, Sort.Direction.DESC, "createdDate"));
    }

    @Test
    void saveUser() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodePassword");

        userService.saveUser(user);

        assertEquals("encodePassword", user.getPassword());
        verify(passwordEncoder).encode(anyString());
    }

    @Test
    void saveRole() {
        when(roleRepository.save(role)).thenReturn(role);
        assertEquals(role, userService.saveRole(role));
        verify(roleRepository).save(role);
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