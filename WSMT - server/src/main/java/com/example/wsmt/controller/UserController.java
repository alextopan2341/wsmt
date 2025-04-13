package com.example.wsmt.controller;

import com.example.wsmt.model.Task;
import com.example.wsmt.model.User;
import com.example.wsmt.model.dto.UserDTO;
import com.example.wsmt.model.dto.UserLoginDTO;
import com.example.wsmt.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{email}/tasks")
    public List<Task> getUserTasks(@PathVariable String email) {
        return userService.getUserTasks(email);
    }

    @PostMapping("/register")
    public User register(@RequestBody UserDTO userDTO) {
        User user = User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .fullName(userDTO.getFullName())
                .build();
        return userService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginDTO userDTO) {
        return userService.login(userDTO.getEmail(), userDTO.getPassword());
    }
}
