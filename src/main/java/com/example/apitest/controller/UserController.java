package com.example.apitest.controller;

import com.example.apitest.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class UserController {
    private final ClientService clientService;

    public UserController(ClientService clientService) {
        this.clientService = clientService;
    }

    public ResponseEntity<List<User>> getInfoUsers() {
        List<User> users = clientService.getUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK)
        }
    }

    public ResponseEntity<User> getInfoUser(@PathVariable Integer id){
User user = clientService.getUser(id).orElseThrow(UserNotFoundException::new);
return new ResponseEntity<>(user,HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createUser(@RequestBody User user){
clientService.createUser(user);
return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user){
        clientService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer id){
        clientService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
