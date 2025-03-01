package com.example.cleanappbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Data
public class PointScore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pointId;
    @ManyToOne
    private Profile profile;
    @CreationTimestamp
    private LocalDate dateTime;
    private int points;
    @ManyToOne
    private Tasklist tasklist;

}
