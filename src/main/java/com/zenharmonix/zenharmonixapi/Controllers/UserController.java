package com.zenharmonix.zenharmonixapi.Controllers;

import com.zenharmonix.zenharmonixapi.Models.User;
import com.zenharmonix.zenharmonixapi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.zenharmonix.zenharmonixapi.Services.JwtUtil;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signInUser(@RequestBody User user) {
        Optional<User> authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
        if (authenticatedUser.isPresent()) {
            String token = jwtUtil.generateToken(authenticatedUser.get());
            return ResponseEntity.ok().body(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

}
