package com.weitheshinobi.forum;


import com.weitheshinobi.forum.entity.Role;
import com.weitheshinobi.forum.entity.User;
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
//
//    @Bean
//    CommandLineRunner test(UserRepository userRepository, RoleRepository roleRepository, UserService userService, BoardService boardService) {
//        return args -> {
//            test2(userRepository, roleRepository, userService, boardService);
//        };
//    }
//
//    void test2(UserRepository userRepository,RoleRepository roleRepository,UserService userService, BoardService boardService) throws Exception {
//        User user = new User();
//        user.setUsername("admin3");
//        user.setPassword("123456");
//        userService.saveUser(user);
////        Role role = new Role();
////        role.setName("ROLE_ADMIN");
////        roleRepository.save(role);
//        userService.addRoleToUser("admin3","ROLE_ADMIN");
//
//    }


}
