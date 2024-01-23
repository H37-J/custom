package com.hjk.custom.controller;

import com.hjk.custom.entity.User;
import com.hjk.custom.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @MutationMapping
    public User saveUser(@Argument String name) {
        return userService.save(name);
    }

    @QueryMapping
    public User getUser(@Argument String name) {
        return userService.getUser(name);
    }

    @QueryMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
