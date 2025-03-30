package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.Tasklist;
import lombok.Data;

@Data
public class TasklistDto {
    private long taskId;
    private String listName;
    private String periodFilter;


    public TasklistDto(Tasklist tasklist) {
        this.taskId = tasklist.getTaskId();
        this.listName = tasklist.getListName();
        this.periodFilter = tasklist.getPeriodFilter();
    }
}
