package com.weitheshinobi.forum.service;

import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(useremail).orElseThrow(
                () -> new EntityNotFoundException("user not found in database")
        );
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

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
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Role not found in database：" + roleName));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found in database：" + roleName));

        user.getRoles().add(role);
    }

}
