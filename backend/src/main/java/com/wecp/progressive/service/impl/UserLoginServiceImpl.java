package com.wecp.progressive.service.impl;

import com.wecp.progressive.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public class UserLoginServiceImpl implements UserDetailsService {

    public List<User> getAllUsers() {
        return null;
    }

    public Optional<User> getUserById(Integer userId) {
        return null;
    }

    public User createUser(User user) {
        return null;
    }

    public User updateUser(User user) {
        return null;
    }

    public void deleteUser(Integer id) {
    }

    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}