package com.mephisto.service;

import com.mephisto.model.User;
import com.mephisto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
}