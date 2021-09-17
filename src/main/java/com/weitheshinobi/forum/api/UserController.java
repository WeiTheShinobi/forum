package com.weitheshinobi.forum.api;


import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userlist/{page}")
    public ResponseEntity<List<User>> getUserList(@PathVariable int page) {
        return ResponseEntity.ok().body(userService.getUserList(page));
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

}


