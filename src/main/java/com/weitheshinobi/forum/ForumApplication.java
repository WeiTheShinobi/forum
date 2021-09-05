package com.weitheshinobi.forum;


import com.weitheshinobi.forum.repository.RoleRepository;
import com.weitheshinobi.forum.repository.UserRepository;
import com.weitheshinobi.forum.service.BoardService;
import com.weitheshinobi.forum.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }

//    @Bean
//    CommandLineRunner test(UserRepository userRepository, RoleRepository roleRepository, UserService userService, BoardService boardService) {
//        return args -> {
//            test2(userRepository, roleRepository, userService, boardService);
//        };
//    }
//
//    void test2(UserRepository userRepository,RoleRepository roleRepository,UserService userService, BoardService boardService) throws Exception {
////        User user = new User();
////        user.setEmail("123");
////        userRepository.save(user);
////        Role role = new Role();
////        role.setName("test");
////        roleRepository.save(role);
//
////        userService.addRoleToUser("456","test");
//        boardService.createBoard("456");
//
//        boardService.updateBoard("456","123",true);
//    }


}
