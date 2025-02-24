package com.mephisto.controller;

import com.mephisto.model.User;
import com.mephisto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling user-related requests.
 * 
 * @author Prashant Singh
 *         on February 24, 2025.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user.
     * 
     * @param user the user to register
     * @return the registered user
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("User");
        }
        return userService.saveUser(user);
    }

    /**
     * Retrieves a user by username.
     * 
     * @param username the username of the user to retrieve
     * @return the user with the specified username
     */
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    /**
     * Adds a friend to the user's friend list.
     * 
     * @param username       the username of the user
     * @param friendUsername the username of the friend to add
     * @return the updated user
     */
    @PostMapping("/{username}/addFriend")
    public ResponseEntity<User> addFriend(@PathVariable String username, @RequestBody String friendUsername) {
        return userService.addFriend(username, friendUsername);
    }

    /**
     * Retrieves all users.
     * 
     * @return a list of all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }
}