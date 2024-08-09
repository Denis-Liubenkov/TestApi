package com.example.apitest.controller;

import com.example.apitest.domain.User;
import com.example.apitest.exceptions.UserNotFoundException;
import com.example.apitest.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getInfoUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            log.info("Users not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Users are found");
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getInfoUser(@PathVariable Integer id) {
        User user = userService.getUser(id).orElseThrow(UserNotFoundException::new);
        log.info("UserInfo :" + "\n" + user.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        log.info("User with surName : " + user.getSurName() + " is created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
        log.info("User with id : " + user.getId() + " is updated");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        log.info("User with id : " + id + "is deleted");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
