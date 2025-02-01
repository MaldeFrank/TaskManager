package com.example.cleanappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private long points;

    @ManyToMany
    private List<GoogleAccount> googleAccounts;

    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnore
    private List<AssignedTask> assignedTasks;
}
