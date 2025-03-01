package com.example.cleanappbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class PointScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pointId;
    @ManyToOne
    private Profile profile;
    @CreationTimestamp
    private LocalDate dateTime;
    private int points;
    private String taskName;
    @ManyToOne
    private Tasklist tasklist;

    public PointScore(Profile profile, int points, Tasklist tasklist, String taskName) {
        this.profile = profile;
        this.points = points;
        this.tasklist = tasklist;
        this.taskName = taskName;
    }
}
