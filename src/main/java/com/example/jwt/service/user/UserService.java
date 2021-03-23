package com.example.jwt.service.user;

import com.example.jwt.model.User;
import com.example.jwt.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService extends IGeneralService<User>, UserDetailsService {
    public Optional<User> findByUsername(String username);
}
