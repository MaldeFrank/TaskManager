package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.Task;
import com.example.cleanappbackend.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/tasks")
    List<Task> getTasks(){
        return taskRepository.findAll();
    }

    @GetMapping("/tasks/{id}")
    Task getTaskById(Long id){
        return taskRepository.getById(id);
    }

    @PostMapping("/tasks")
    Task createTask(@RequestBody Task task){
        System.out.println("Google id: " + task.getGoogleAccount().getId());
        return taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task newTask) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(newTask.getTitle());
                    existingTask.setDescription(newTask.getDescription());
                    existingTask.setPoints(newTask.getPoints());
                    return taskRepository.save(existingTask);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + id));
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }
}
