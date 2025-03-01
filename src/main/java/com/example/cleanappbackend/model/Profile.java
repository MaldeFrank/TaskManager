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

    private int points;

    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL, orphanRemoval = true)
    List<PointScore> pointScores;

    @ManyToMany
    private List<GoogleAccount> googleAccounts;

    @OneToMany(mappedBy = "assignedTo",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<AssignedTask> assignedTasks;

    public void updateTotalPoints() {
        if (pointScores == null || pointScores.isEmpty()) {
            this.points = 0;
        } else {
            this.points = pointScores.stream()
                    .mapToInt(PointScore::getPoints)
                    .sum();
        }
    }
}
