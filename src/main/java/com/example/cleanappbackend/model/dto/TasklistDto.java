package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.Tasklist;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TasklistDto {
    private long taskId;
    private String listName;


    public TasklistDto(Tasklist tasklist) {
        this.taskId = tasklist.getTaskId();
        this.listName = tasklist.getListName();
    }
}
