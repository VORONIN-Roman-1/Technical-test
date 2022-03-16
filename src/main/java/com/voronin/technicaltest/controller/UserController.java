package com.voronin.technicaltest.controller;

import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public User getUserByUserName(@RequestParam String userName) {
        return userService.findByUserName(userName);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User addUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }
}
