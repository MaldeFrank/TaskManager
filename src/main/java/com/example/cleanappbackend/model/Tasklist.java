package com.example.cleanappbackend.model;

import com.example.cleanappbackend.model.enums.PeriodFilter;
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

    private String periodFilter;

    @ManyToMany()
    private List<GoogleAccount> googleAccount;

    @OneToMany(mappedBy = "tasklist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AssignedTask> assignedTaskList;
}