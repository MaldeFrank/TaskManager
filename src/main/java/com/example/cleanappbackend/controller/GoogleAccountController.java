package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.AssignedTask;
import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.dto.AssignedTaskDto;
import com.example.cleanappbackend.model.dto.TasklistDto;
import com.example.cleanappbackend.repository.GoogleAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/googleAccount")
public class GoogleAccountController {
    @Autowired
    private GoogleAccountRepository googleAccountRepository;

    @PostMapping("/create")
    GoogleAccount createGoogleAccount(@RequestBody GoogleAccount googleAccount) {
        if (googleAccount.getId() == null || googleAccount.getId().isEmpty()) {
            throw new IllegalArgumentException("Google Account ID cannot be null or empty."); // Handle missing ID
        }
        return googleAccountRepository.save(googleAccount);
    }

    @GetMapping("/get/{id}")
    GoogleAccount getGoogleAccount(@PathVariable String id){
        return googleAccountRepository.getReferenceById(id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteGoogleAccount(@PathVariable String id){
        googleAccountRepository.deleteById(id);
    }

    @GetMapping("/getAll")
    List<GoogleAccount> getAll(){
        return googleAccountRepository.findAll();
    }

    @GetMapping("/checkIfExists/{id}")
    Boolean checkIfExists(@PathVariable String id){
        Optional<GoogleAccount> googleAccount = googleAccountRepository.findById(id);
        return googleAccount.isPresent();
    }

    @GetMapping("getAssignedTasks/{userId}")
    List<AssignedTaskDto> getAssignedTasks(@PathVariable String userId) {
        GoogleAccount googleAccount = googleAccountRepository.findById(userId).orElseThrow();
        List<AssignedTask> assignedTasks = googleAccount.getAssignedTasks();
        List<AssignedTaskDto> assignedTaskDTOs = assignedTasks.stream()
                .map(assignedTask -> new AssignedTaskDto(assignedTask))
                .collect(Collectors.toList());
        return assignedTaskDTOs;
    }

    @GetMapping("/tasklists/{userId}")
    List<TasklistDto> getTaskLists(@PathVariable String userId) {
        GoogleAccount googleAccount = googleAccountRepository.findById(userId).orElseThrow();

        List<TasklistDto> tasklistDtos = googleAccount.getTasklists().stream()
                .map(tasklist-> new TasklistDto(tasklist))
                .collect(Collectors.toList());

        return tasklistDtos;
    }

}
