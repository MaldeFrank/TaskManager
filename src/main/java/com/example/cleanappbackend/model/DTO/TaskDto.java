package com.example.cleanappbackend.model.DTO;

import lombok.Data;

@Data
public class TaskDto {
    private String title;
    private String description;
    private Long points;
}
