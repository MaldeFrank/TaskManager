package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.Task;
import com.example.cleanappbackend.repository.TaskRepository;
import dto.TaskDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    Task createTask(@RequestBody TaskDto task){
        Task newTask = Task.builder()
                .id(null)
                .title(task.getTitle())
                .description(task.getDescription())
                .points(task.getPoints())
                .build();
        return taskRepository.save(newTask);
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
