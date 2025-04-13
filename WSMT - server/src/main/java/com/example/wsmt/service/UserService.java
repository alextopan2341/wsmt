package com.example.wsmt.service;

import com.example.wsmt.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.wsmt.model.Task;
import com.example.wsmt.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TaskService taskService;

    public UserService(UserRepository userRepository, TaskService taskService) {
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    public List<Task> getUserTasks(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return taskService.getTaskByUser(user.get().getId());
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }
}
