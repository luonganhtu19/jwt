package com.example.jwt;

import com.example.jwt.model.User;
import com.example.jwt.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class JwtApplication {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init(){
        List<User> users =(List<User>) userService.findAll();
        if (users.isEmpty()){
            User user =new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            userService.save(user);
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

}
