package com.ecommerce.project.service;

import com.ecommerce.project.model.User;
import com.ecommerce.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserImplementation implements UserService {

   @Autowired
   private UserRepository userRepository ;

    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "User Added Successfully";
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Exist"));
        userRepository.delete(user);
        return "User Deleted Successfully";
    }

    @Override
    public User updateUser(Long userId ,User user) {
        User updatedUser = userRepository.findById(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));

        updatedUser.setUserName(user.getUserName());
        updatedUser.setUserId(userId);

        userRepository.save(updatedUser);

        return updatedUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
