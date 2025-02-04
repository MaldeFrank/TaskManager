package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.AssignedTask;
import com.example.cleanappbackend.model.Profile;
import com.example.cleanappbackend.model.dto.AssignedTaskDto;
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
public class AssignedTaskController {
    private final AssignedTaskRepository assignedTaskRepository;

    public AssignedTaskController(AssignedTaskRepository assignedTaskRepository) {
        this.assignedTaskRepository = assignedTaskRepository;
    }

    @GetMapping("/assignedTask")
    List<AssignedTask> getAssignedTasks(){
       return assignedTaskRepository.findAll();
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
        Profile profile = assignedTaskRepository.findProfileById(newTask.getAssignedTo().getId()); //Solves problem of profile getting changed

        return assignedTaskRepository.findById(id)
                .map(existingTask -> {
                    if (newTask.getTask() != null) {
                        existingTask.setTask(newTask.getTask());
                    }
                    if (newTask.getAssignedTo() != null) {
                        existingTask.setAssignedTo(profile);
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

    @GetMapping("/assignedTasksByProfile/{id}")
    public List<AssignedTaskDto> getAssignedTasksByProfileId(@PathVariable Long id){

        List<AssignedTaskDto> foundAssingedTasks = assignedTaskRepository.getAssignedTaskByProfileId(id)
                .stream()
                .map(assTask-> new AssignedTaskDto(assTask))
                .toList();

        return foundAssingedTasks;
    }
}
