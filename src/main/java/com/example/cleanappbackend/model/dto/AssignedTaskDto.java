package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.AssignedTask;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AssignedTaskDto {
    private long id;
    private ProfileDto assignedTo;
    private boolean completed;
    private LocalDate dateTime;
    private TaskDto task;
    private String userId;
    private long tasklistId;


    public AssignedTaskDto(AssignedTask assignedTask) {
        this.setId(assignedTask.getId());
        this.setCompleted(assignedTask.isCompleted());
        this.setDateTime(assignedTask.getDateTime());
        this.setUserId(assignedTask.getUserId());
        this.setTasklistId(assignedTask.getTasklist().getTaskId());
        this.setTask(new TaskDto(assignedTask.getTask()));
        this.setAssignedTo(assignedTask.getAssignedTo() == null ? null : new ProfileDto(assignedTask.getAssignedTo()));
    }
}

