package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getUserList(int page) {
        return userRepository.findAll(PageRequest.of(page, 30)).getContent();
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void addRoleToUser(String email, String roleName) {
        roleRepository.findByName(roleName).ifPresent(role -> {
            userRepository.findByEmail(email)
                    .ifPresent(user -> user.getRoles().add(role));
        });
    }

}
