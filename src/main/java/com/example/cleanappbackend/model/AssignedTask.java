package com.example.cleanappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "assignedTask")
public class AssignedTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    @JsonIgnore
    private Profile assignedTo;
    private boolean completed;
    @CreationTimestamp
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @PostConstruct
    public void init() {
        completed = false;
    }
}
