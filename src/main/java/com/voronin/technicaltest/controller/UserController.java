package com.voronin.technicaltest.controller;

import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.findById(id);
    }
    @PostMapping("")
    public User addUser(@RequestBody User user){
        userService.save(user);
        return user;
    }
}
