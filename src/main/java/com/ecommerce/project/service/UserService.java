package com.ecommerce.project.service;

import com.ecommerce.project.model.User;

import java.util.List;

public interface UserService {
     String createUser(User user);

     String deleteUser(Long userId);

     User updateUser(Long userId ,User user);


    List<User>getAllUsers() ;

}
