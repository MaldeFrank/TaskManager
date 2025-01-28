package com.example.cleanappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Tasklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_list_id")
    private long taskId;

    private String listName;

    @OneToMany(mappedBy = "tasklist")
    @JsonIgnore
    private List<AssignedTask> assignedTaskList;
}