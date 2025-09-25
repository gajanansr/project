package com.wecp.progressive.controller;

import com.wecp.progressive.dto.LoginRequest;
import com.wecp.progressive.dto.LoginResponse;
import com.wecp.progressive.entity.User;
import com.wecp.progressive.service.impl.UserLoginServiceImpl;
import com.wecp.progressive.jwt.JwtUtil;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    UserLoginServiceImpl userLoginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try{
            return ResponseEntity.ok(userLoginService.createUser(user));
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage() , HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequest loginRequest) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch(AuthenticationException e) {
             throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "Invalid username or password" ,e);
        }
        final UserDetails userDetails = userLoginService.loadUserByUsername(loginRequest.getUsername());
        User foundUser = userLoginService.getUserByUsername(loginRequest.getUsername());
        final String token = jwtUtil.generateToken(loginRequest.getUsername());
        String role = foundUser.getRole();
        Integer userId = foundUser.getUserId();
        System.out.println("User Roles: " + role);
        return ResponseEntity.ok(new LoginResponse(token, role, userId));
    }
}
