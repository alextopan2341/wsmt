package com.example.wsmt.service;

import com.example.wsmt.model.User;
import com.example.wsmt.model.dto.TaskDTO;
import com.example.wsmt.repository.TaskRepository;
import com.example.wsmt.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.wsmt.model.Task;
import java.util.List;

@Transactional
@Service
public class TaskService {
    private final TaskRepository repository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository repository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getTaskByUser(Long userID) {
        return repository.findTasksByUserId(userID);
    }

    public Task createTask(TaskDTO taskDTO) {
        User user = userRepository.findByEmail(taskDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task(user, taskDTO.getTitle(), taskDTO.getDescription(), taskDTO.isCompleted());
        return repository.save(task);
    }


    public Task updateTask(Long id, Task taskDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You can only update your own tasks");
        }

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.isCompleted());

        return repository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        repository.delete(id);
    }

}