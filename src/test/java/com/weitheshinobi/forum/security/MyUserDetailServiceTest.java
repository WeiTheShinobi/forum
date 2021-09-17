package com.weitheshinobi.forum.security;

import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MyUserDetailServiceTest {

    @InjectMocks
    private MyUserDetailService myUserDetailService;
    @Mock
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUsername("username");
        user.setPassword("password");
        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();
        role1.setName("1");
        role2.setName("2");
        role3.setName("3");
        user.setRoles(List.of(role1, role2, role3));
    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = myUserDetailService.loadUserByUsername("username");

        assertEquals(3, userDetails.getAuthorities().size());
        assertEquals("username", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());

    }

    @Test
    void loadUserByUsername_NotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> myUserDetailService.loadUserByUsername(anyString()));
    }

}