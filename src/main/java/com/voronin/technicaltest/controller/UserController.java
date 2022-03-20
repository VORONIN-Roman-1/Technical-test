package com.voronin.technicaltest.controller;

import com.voronin.technicaltest.entity.User;
import com.voronin.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api/users")

public class UserController {

    private UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService instance of type {@link UserService}
     */
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets user by user name.
     *
     * @param userName the user name to search
     * @return the {@link User} from {@link UserService}
     */
    @GetMapping("{userName}")
    public User getUserByUserName(@PathVariable String userName) {
        return userService.findByUserName(userName);
    }

    /**
     * Add new user.
     *
     * @param user the new {@link User}
     * @return the added {@link User}
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public User addUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }
}
