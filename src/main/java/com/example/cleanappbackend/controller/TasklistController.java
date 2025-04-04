package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.dto.AssignedTaskDto;
import com.example.cleanappbackend.model.Tasklist;
import com.example.cleanappbackend.model.dto.GoogleAccountDto;
import com.example.cleanappbackend.model.dto.TasklistDto;
import com.example.cleanappbackend.repository.TasklistRepository;
import com.example.cleanappbackend.util.AssignedTaskFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("tasklist")
public class TasklistController {
    @Autowired
    private TasklistRepository tasklistRepository;

    @PutMapping("/updateTasklist/{id}")
    public Tasklist updateTasklist(@PathVariable Long id, @RequestBody Tasklist updatedTasklist) {
        Tasklist tasklistToUpdate = tasklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find tasklist with id: " + id));

        if (updatedTasklist.getListName() != null) {
            tasklistToUpdate.setListName(updatedTasklist.getListName());
        }
        if (updatedTasklist.getAssignedTaskList() != null) {
            tasklistToUpdate.setAssignedTaskList(updatedTasklist.getAssignedTaskList());
        }
        if (updatedTasklist.getGoogleAccount() != null) {
            tasklistToUpdate.setGoogleAccount(updatedTasklist.getGoogleAccount());
        }
        if (updatedTasklist.getPeriodFilter() != null) {
            tasklistToUpdate.setPeriodFilter(updatedTasklist.getPeriodFilter());
        }

        return tasklistRepository.save(tasklistToUpdate);
    }

    @GetMapping("/getAll")
    public List<Tasklist> getAll() {
        List<Tasklist> tasklists = tasklistRepository.findAll();
        for (Tasklist tasklist : tasklists) {
            tasklist.setAssignedTaskList(null);
        }
        return tasklists;
    }

    @GetMapping("/assignedTasks/{id}")
    public List<AssignedTaskDto> getAssignedTasksByTasklistId(@PathVariable Long id) {
        Tasklist tasklist = tasklistRepository.getReferenceById(id);
        List<AssignedTaskDto> assignedTaskDtos = new ArrayList<>();

        tasklist.getAssignedTaskList().stream().forEach(task -> {
            AssignedTaskDto assignedTaskDto = new AssignedTaskDto(task);
            assignedTaskDtos.add(assignedTaskDto);
        });

        return assignedTaskDtos;
    }

    @GetMapping("/assignedTasksWeekly/{id}")
    public List<AssignedTaskDto> getAssignedTasksWeekly(@PathVariable Long id) {
        Tasklist tasklist = tasklistRepository.getReferenceById(id);

        List<AssignedTaskDto> assignedTaskDtos = new ArrayList<>();

        tasklist.getAssignedTaskList().stream().forEach(task -> {
            AssignedTaskDto assignedTaskDto = new AssignedTaskDto(task);
            assignedTaskDtos.add(assignedTaskDto);
        });

        return AssignedTaskFilter.filterWeekly(assignedTaskDtos);
    }

    @GetMapping("/assignedTasksMonthly/{id}")
    public List<AssignedTaskDto> getAssignedTasksMonthly(@PathVariable Long id) {
        Tasklist tasklist = tasklistRepository.getReferenceById(id);

        List<AssignedTaskDto> assignedTaskDtos = new ArrayList<>();

        tasklist.getAssignedTaskList().stream().forEach(task -> {
            AssignedTaskDto assignedTaskDto = new AssignedTaskDto(task);
            assignedTaskDtos.add(assignedTaskDto);
        });

        return AssignedTaskFilter.filterMonthly(assignedTaskDtos);
    }

    @GetMapping("/getTasklist/{id}")
    public TasklistDto getTasklist(@PathVariable Long id) {
        Tasklist tasklist = tasklistRepository.getReferenceById(id);
        return new TasklistDto(tasklist);
    }

    @DeleteMapping("/deleteTasklist/{id}")
    public void deleteTasklist(@PathVariable Long id) {
        Tasklist tasklist = tasklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find tasklist with id: " + id));
        tasklist.getPointScores().forEach(pointScore -> pointScore.setTasklist(null));
        tasklistRepository.save(tasklist);
        tasklistRepository.deleteById(id);
    }

    @PostMapping("/createTasklist")
    public Tasklist createTasklist(@RequestBody Tasklist tasklist) {
        return tasklistRepository.save(tasklist);
    }

    @PutMapping("/addUser/{email}/{tasklistId}")
    public Boolean addUserToTasklist(@PathVariable String email, @PathVariable Long tasklistId) {
        Tasklist tasklist = tasklistRepository.getReferenceById(tasklistId);
        GoogleAccount googleAccount = tasklistRepository.findGoogleAccByEmail(email);

        if (googleAccount == null) {
            return false;
        }

        if (tasklist.getGoogleAccount().contains(googleAccount)) {
            return true;
        }

        tasklist.getGoogleAccount().add(googleAccount);


        tasklist.getAssignedTaskList().forEach(task -> {
            if (!task.getGoogleAccount().contains(googleAccount)) { // Check for each task
                task.getGoogleAccount().add(googleAccount);
            }
        });


        tasklistRepository.save(tasklist);
        return true;
    }

    @PutMapping("/setPeriodFilter/{id}/{period}")
    public Boolean setPeriodFilter(@PathVariable Long id, @PathVariable String period) {
        Tasklist tasklist = tasklistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find tasklist with id: " + id));

        switch (period) {
            case "All":
                tasklist.setPeriodFilter("All");
                break;
            case "Monthly":
                tasklist.setPeriodFilter("Monthly");
                break;
            case "Weekly":
                tasklist.setPeriodFilter("Weekly");
                break;
            default:
                return false;
        }
        tasklistRepository.save(tasklist);
        return true;
    }

    @GetMapping("/getAllUsers/{id}")
    public List<GoogleAccountDto> getAllUser(@PathVariable Long id) {
        Tasklist tasklist = tasklistRepository.getReferenceById(id);
        List<GoogleAccountDto> googleAccountDtos = tasklist.getGoogleAccount().stream().map(GoogleAccountDto::new).toList();
        return googleAccountDtos;
    }
}
