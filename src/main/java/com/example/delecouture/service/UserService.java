package com.example.delecouture.service;

import com.example.delecouture.dto.UserRegistrationDto;
import com.example.delecouture.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveNewUser(UserRegistrationDto userRegistrationDto);
    User editUser(Long id, UserRegistrationDto userRegistrationDto);
    boolean deleteUser(Long id);
    List<User> getAllUsers();
    User getUser(Long id);

    User loginUser(String email,String password);

}
