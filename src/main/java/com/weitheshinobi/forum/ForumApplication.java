package com.weitheshinobi.forum;


import com.weitheshinobi.forum.entity.Board;
import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
import com.weitheshinobi.forum.repository.BoardRepository;
import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import com.weitheshinobi.forum.service.BoardService;
import com.weitheshinobi.forum.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaAuditing
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository, RoleRepository roleRepository, UserService userService, BoardService boardService) {
//        return args -> {
//            testData(userRepository, roleRepository, userService, boardService);
//        };
//    }
//
//    void testData(UserRepository userRepository,RoleRepository roleRepository,UserService userService, BoardService boardService) throws Exception {
////        User user = new User();
////        user.setUsername("admin5");
////        user.setPassword("123456");
////        user.setFollowingBoard(boardService.getBoardList());
////        userService.saveUser(user);
////        Role role = new Role();
////        role.setName("ROLE_ADMIN");
////        roleRepository.save(role);
////        Role role2 = new Role();
////        role2.setName("ROLE_USER");
////        roleRepository.save(role2);
////        userService.addRoleToUser("admin3","ROLE_ADMIN");
////        boardService.saveBoard(new Board("test1", true));
////        boardService.saveBoard(new Board("test2", true));
////        boardService.saveBoard(new Board("test3", true));
////        boardService.saveBoard(new Board("test4", false));
////        boardService.saveBoard(new Board("test5", false));
////        boardService.saveBoard(new Board("test6", true));
//    }


}
