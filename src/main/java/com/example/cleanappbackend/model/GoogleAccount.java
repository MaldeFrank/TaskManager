package com.example.cleanappbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class GoogleAccount {
    @Id
    private String id;

    private String name;


    @OneToMany(mappedBy = "googleAccount")
    @Column(nullable = true)
    private List<Tasklist> tasklists;


    @OneToMany(mappedBy = "googleAccount")
    @Column(nullable = true)
    private List<AssignedTask> assignedTasks;


    @OneToMany(mappedBy = "googleAccount")
    @Column(nullable = true)
    private List<Task> task;

}
