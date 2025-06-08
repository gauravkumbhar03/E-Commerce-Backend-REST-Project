package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/api/public/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/api/public/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User Created Successfully", HttpStatus.OK);
    }

    @PutMapping("/api/admin/users/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId,@RequestBody User user){

        User updatedUser = userService.updateUser(userId,user);
        return  new ResponseEntity<>("User has been updated",HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId)
    {
         userService.deleteUser(userId);
         return new ResponseEntity<>("user Deleted Succesfully",HttpStatus.OK);
    }

}
