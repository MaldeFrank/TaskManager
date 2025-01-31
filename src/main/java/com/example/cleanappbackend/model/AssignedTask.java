package com.example.cleanappbackend.model;


import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "assignedTask")
public class AssignedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile assignedTo; // Assuming Profile class exists elsewhere

    private boolean completed;

    @CreationTimestamp
    private LocalDate dateTime;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private String userId;

    @ManyToOne()
    @JoinColumn(name = "task_list_id")
    private Tasklist tasklist;

    @ManyToMany()
    private List<GoogleAccount> googleAccount;

    @PostConstruct
    public void init() {
        completed = false;
    }

}