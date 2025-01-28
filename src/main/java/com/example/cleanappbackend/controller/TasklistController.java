package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.AssignedTask;
import com.example.cleanappbackend.model.Tasklist;
import com.example.cleanappbackend.repository.TasklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tasklist")
public class TasklistController {
    @Autowired
    private TasklistRepository tasklistRepository;

    @GetMapping("/getAll")
    public List<Tasklist> getAll(){
        return tasklistRepository.findAll();
    }

    @GetMapping("/assignedTasks/{id}")
    public List<AssignedTask> getAssignedTasksByTasklistId(@PathVariable Long id){
        Tasklist tasklist = tasklistRepository.getReferenceById(id);
        if(tasklist.getAssignedTaskList().isEmpty()){
            return null;
        }
        return tasklist.getAssignedTaskList();
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
