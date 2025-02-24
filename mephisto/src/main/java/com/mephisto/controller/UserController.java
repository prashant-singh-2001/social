package com.mephisto.controller;

import com.mephisto.model.User;
import com.mephisto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("User");
        }
        return userService.saveUser(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping("/{username}/addFriend")
    public ResponseEntity<User> addFriend(@PathVariable String username, @RequestBody String friendUsername) {
        return userService.addFriend(username, friendUsername);
    }
}