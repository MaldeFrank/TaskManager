package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.DTO.AssignedTaskDto;
import com.example.cleanappbackend.model.Tasklist;
import com.example.cleanappbackend.repository.TasklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("tasklist")
public class TasklistController {
    @Autowired
    private TasklistRepository tasklistRepository;

    @GetMapping("/getAll")
    public List<Tasklist> getAll(){
        List<Tasklist> tasklists = tasklistRepository.findAll();
        for (Tasklist tasklist : tasklists) {
            tasklist.setAssignedTaskList(null);
        }
        return tasklists;
    }

    @GetMapping("/assignedTasks/{id}")
    public List<AssignedTaskDto> getAssignedTasksByTasklistId(@PathVariable Long id){
        Tasklist tasklist = tasklistRepository.getReferenceById(id);
        List<AssignedTaskDto> assignedTaskDtos = new ArrayList<>();

        tasklist.getAssignedTaskList().stream().forEach(task->{
            AssignedTaskDto assignedTaskDto = new AssignedTaskDto(task);
            assignedTaskDtos.add(assignedTaskDto);
        });

        return assignedTaskDtos;
    }

    @GetMapping("/getTasklist/{id}")
    public Tasklist getTasklist(@PathVariable Long id){
       return tasklistRepository.getReferenceById(id);
    }

    @DeleteMapping("/deleteTasklist/{id}")
    public void deleteTasklist(@PathVariable Long id){
        tasklistRepository.deleteById(id);
    }

    @PostMapping("/createTasklist")
    public Tasklist createTasklist(@RequestBody Tasklist tasklist){
        return tasklistRepository.save(tasklist);
    }
}
