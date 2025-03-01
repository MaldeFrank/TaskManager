package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.PointScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointScoreRepository extends JpaRepository<PointScore, Long> {

    @Query("SELECT SUM(p.points) FROM PointScore p WHERE p.profile.id = :profileId AND p.tasklist.taskId = :taskId")
    Integer GetProfileTasklistScore(Long profileId, Long taskId);

    @Query("SELECT SUM(p.points) FROM PointScore p WHERE p.profile.id = :profileId")
    Integer getTotalProfileScore(Long profileId);
}
