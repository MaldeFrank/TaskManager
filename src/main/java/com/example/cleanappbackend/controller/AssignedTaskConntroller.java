package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.AssignedTask;
import com.example.cleanappbackend.repository.AssignedTaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AssignedTaskConntroller {
    private final AssignedTaskRepository assignedTaskRepository;

    public AssignedTaskConntroller(AssignedTaskRepository assignedTaskRepository) {
        this.assignedTaskRepository = assignedTaskRepository;
    }

    @GetMapping("/assignedTask")
    List<AssignedTask> getAssignedTasks(){
       return assignedTaskRepository.findAll();
    }

    @GetMapping("/assignedTask/weekly/{userId}")
    public List<AssignedTask> getAssignedTasksWeekly(@PathVariable String userId) {
        List<AssignedTask> assignedTasks = assignedTaskRepository.findAll();

        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        return assignedTasks.stream()
                .filter(task -> {
                    LocalDate taskDate = task.getDateTime();
                    return !taskDate.isBefore(monday) && !taskDate.isAfter(sunday) && task.getUserId().equals(userId);
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/assignedTask/{id}")
    AssignedTask getAssignedTask(@PathVariable long id){
        return assignedTaskRepository.getReferenceById(id);
    }

    @PostMapping("/assignedTask")
    AssignedTask createAssignedTask(@RequestBody AssignedTask task){
        return assignedTaskRepository.save(task);
    }

    @PutMapping("/assignedTask/{id}")
    public AssignedTask updateAssignedTask(@PathVariable Long id, @RequestBody AssignedTask newTask) {
        return assignedTaskRepository.findById(id)
                .map(existingTask -> {
                    if (newTask.getTask() != null) {
                        existingTask.setTask(newTask.getTask());
                    }
                    if (newTask.getAssignedTo() != null) {
                        existingTask.setAssignedTo(newTask.getAssignedTo());
                    }
                    if (newTask.isCompleted() != existingTask.isCompleted()) {
                        existingTask.setCompleted(newTask.isCompleted());
                    }
                    if (newTask.getDateTime() != null) {
                        existingTask.setDateTime(newTask.getDateTime());
                    }
                    return assignedTaskRepository.save(existingTask);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assigned task not found with id: " + id));
    }
}
