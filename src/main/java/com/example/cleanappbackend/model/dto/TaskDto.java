package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.Task;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long points;

    public TaskDto(Task task) {
        this.setId(task.getId());
        this.setTitle(task.getTitle());
        this.setDescription(task.getDescription());
        this.setPoints(task.getPoints());
    }
}
