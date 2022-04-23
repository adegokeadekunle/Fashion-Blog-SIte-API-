package com.example.delecouture.service.serviceImpl;


import com.example.delecouture.dto.UserRegistrationDto;
import com.example.delecouture.entity.User;
import com.example.delecouture.exceptions.InvalidRecordException;
import com.example.delecouture.exceptions.UserRegistrationException;
import com.example.delecouture.repository.UserRepository;
import com.example.delecouture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public User saveNewUser(UserRegistrationDto userRegistrationDto) {
        User user  = userRepository.findByUsernameAndEmail(userRegistrationDto.getUsername(),userRegistrationDto.getEmail());
        if (user == null) {
            user = new User();
            Date date = Date.valueOf(LocalDate.now());
            user.setFirstName(userRegistrationDto.getFirstName());
            user.setLastName(userRegistrationDto.getLastName());
            user.setUsername(userRegistrationDto.getUsername());
            user.setEmail(userRegistrationDto.getEmail());
            user.setPassword(userRegistrationDto.getPassword());
            user.setRegistrationDate(date);
            userRepository.save(user);
        }
        else{
            throw  new UserRegistrationException("This account "+user.getEmail()+" already exist!");
        }
        return user;
    }
    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email,password);
        if (user == null) {
            throw new InvalidRecordException("Invalid user credentials please try again!");
        }
        return user;
    }

    @Override
    public User editUser(Long id, UserRegistrationDto userRegistrationDto) {
        User user  = userRepository.getById(id);
        if (user == null) {
            throw new InvalidRecordException("user not found!");
        }
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setUsername(userRegistrationDto.getUsername());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(userRegistrationDto.getPassword());
        userRepository.save(user);
        return user;

    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user == null) {
            throw new InvalidRecordException("user not found!");
        }
        userRepository.deleteById(id);

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }


}
