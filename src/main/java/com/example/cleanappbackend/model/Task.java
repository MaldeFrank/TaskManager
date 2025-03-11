package com.example.cleanappbackend.model;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "task")
@Builder
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Long points;
    @ManyToOne
    private GoogleAccount googleAccount;

    public Task() {

    }
}
