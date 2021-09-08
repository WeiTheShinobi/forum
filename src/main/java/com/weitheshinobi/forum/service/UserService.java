package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    private @Autowired RoleRepository roleRepository;
    private @Autowired UserRepository userRepository;
    private @Autowired PasswordEncoder passwordEncoder;

    public Optional<User> getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUserList(int page) {
        return userRepository.findAll(PageRequest.of(page, 50, Sort.Direction.DESC, "createdDate")).getContent();
    }

    @Transactional
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void addRoleToUser(String username, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found in database , role name ： " + roleName));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found in database , username ： " + username));

        user.getRoles().add(role);
    }

}
