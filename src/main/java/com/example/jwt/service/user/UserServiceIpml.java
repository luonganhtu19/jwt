package com.example.jwt.service.user;

import com.example.jwt.model.User;
import com.example.jwt.model.UserPrinciple;
import com.example.jwt.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceIpml implements UserService{
    @Autowired
    private IUserRepository userRepository;
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(User user) {
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByUsername(username).get();
        return  UserPrinciple.build(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }
}
