package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.responseType.Response;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String redirectToUser(){
        System.out.println("test print statement");
        return "redirect:/user";

    }

    @GetMapping("user")
    public ResponseEntity<Response<List<User>>> getUser( ) {
        Response<List<User>> userResponse = new Response<>("success", userService.getUser(), HttpStatus.OK);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

//    @GetMapping("user")
//    public ResponseEntity<Response<Page<User>>> getUserPage(@RequestParam("page") int page,
//                                                            @RequestParam("size") int size,
//                                                            @RequestParam(value = "sort" , defaultValue = "name") String sort) {
//
//        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sort));
//        Response<Page<User>> userResponse = new Response<>("success", userService.getUser(pageRequest), HttpStatus.OK);
//        return new ResponseEntity<>(userResponse, HttpStatus.OK);
//    }

    @PostMapping("user")
    public ResponseEntity<Response<User>> saveUser(@Valid @RequestBody User user) {
        Response<User> userResponse = new Response<>("user save successfully", userService.saveUser(user), HttpStatus.OK);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("user/{uid}")
    public ResponseEntity<Response<User>> updateUser(@Valid @RequestBody User user, @PathVariable("uid") int id) {
        try {
            Response<User> userResponse = new Response<>("user save successfully", userService.updateUser(id, user), HttpStatus.OK);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating user with id {}:{}", id, e.getMessage());
//            logger.error("Error updating user with id {}:{}",id,e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        return new ResponseEntity<>(userService.updateUser(id,user), HttpStatus.OK);
    }

    @DeleteMapping("user/{uid}")
    public ResponseEntity<Response<String>> deleteUser(@PathVariable("uid") int id) {
        if (userService.deleteUser(id)) {
            return new ResponseEntity<>(new Response("User deleted successfully", null, HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Response("User deletion failed", null, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("user")
    public ResponseEntity<User> patchUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.patchUser(user), HttpStatus.OK);
    }



}
