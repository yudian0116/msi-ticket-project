package com.mercury.usersercive.controller;

import com.mercury.usersercive.bean.User;
import com.mercury.usersercive.http.Response;
import com.mercury.usersercive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {return userService.getUserById(id);}

    @PostMapping
    public Response addUser(@RequestBody User user) {return userService.register(user);}

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable int id) {return userService.deleteUser(id);}
}
