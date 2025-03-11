package com.example.cleanappbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class GoogleAccount {
    @Id
    private String id;

    private String name;

    private String email;

    @ManyToMany(mappedBy = "googleAccounts")
    @Column(nullable = true)
    private List<Profile> profiles;

    @ManyToMany(mappedBy = "googleAccount")
    @Column(nullable = true)
    private List<Tasklist> tasklists;


    @ManyToMany(mappedBy = "googleAccount")
    @Column(nullable = true)
    private List<AssignedTask> assignedTasks;


    @OneToMany(mappedBy = "googleAccount")
    @Column(nullable = true)
    private List<Task> task;

}
