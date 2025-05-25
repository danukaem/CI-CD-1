package com.example.demo.service;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<User> getUser() {
        List<User> all = userRepo.findAll();
        all.forEach(user -> {
             user.setName(user.getName() + " - ***  ");
        });

        return all;
    }

    public Page<User> getUser(PageRequest pageRequest) {
        return userRepo.findAll(pageRequest);
    }

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public User updateUser(int id, User user) {
        User userByName = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userByName.setName(user.getName());
        userByName.setAge(user.getAge());
        return userRepo.save(userByName);
    }

    public boolean deleteUser(int id) {
        return userRepo.deleteUserById(id) != 0;
    }

    public User patchUser(User user) {
        return userRepo.save(user);
    }
}
