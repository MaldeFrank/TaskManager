package com.example.cleanappbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Tasklist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;

    @Column(name = "list-name")  // Changed to match database column name with hyphen
    private String listName;

    @OneToMany
    @JoinColumn(name = "Tasklist")
    private List<AssignedTask> assignedTaskList;
}
