package com.mephisto.service;

import com.mephisto.model.User;
import com.mephisto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling user-related operations.
 * 
 * @author Prashant Singh
 *         on February 24, 2025.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a new user.
     * 
     * @param user the user to save
     * @return the saved user
     */
    public ResponseEntity<User> saveUser(User user) {
        try {
            User savedUser = userRepository.save(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            if (e.getCause() != null && e.getCause().getMessage().contains("Duplicate key")) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Finds a user by username.
     * 
     * @param username the username of the user to find
     * @return the user with the specified username
     */
    public ResponseEntity<User> findByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Adds a friend to the user's friend list.
     * 
     * @param username       the username of the user
     * @param friendUsername the username of the friend to add
     * @return the updated user
     */
    public ResponseEntity<User> addFriend(String username, String friendUsername) {
        try {
            User user = userRepository.findByUsername(username);
            User friend = userRepository.findByUsername(friendUsername);

            if (user != null && friend != null && "User".equals(user.getRole()) && "User".equals(friend.getRole())) {
                user.getFriends().add(friend);
                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves all users.
     * 
     * @return a list of all users
     */
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}