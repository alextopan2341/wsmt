package com.example.wsmt.controller;

import com.example.wsmt.model.dto.TaskDTO;
import com.example.wsmt.repository.UserRepository;
import com.example.wsmt.service.TaskService;
import com.example.wsmt.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.example.wsmt.model.Task;

import java.util.List;

@RestController
@RequestMapping("/tasks")
class TaskController {
    private final TaskService service;

    public TaskController(TaskService service, UserService userService) {
        this.service = service;
    }

    @GetMapping("/{userID}")
    public List<Task> getAllTasks(@PathVariable Long userID) {
        return service.getTaskByUser(userID);
    }

    @PostMapping()
    public Task createTask(@RequestBody TaskDTO taskDTO) {
        return service.createTask(taskDTO);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDTO) {
        return service.updateTask(id, taskDTO, taskDTO.getUser().getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);

    }

}
