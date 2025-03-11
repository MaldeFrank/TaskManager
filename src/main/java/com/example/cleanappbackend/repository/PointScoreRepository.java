package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.PointScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointScoreRepository extends JpaRepository<PointScore, Long> {

    @Query("SELECT SUM(p.points) FROM PointScore p WHERE p.profile.id = :profileId AND p.tasklist.taskId = :taskId")
    Integer GetProfileTasklistScore(Long profileId, Long taskId);

    @Query("SELECT p FROM PointScore p WHERE p.profile.id = :profileId AND p.tasklist.taskId = :taskId")
    List<PointScore> getProfileTasklistScores(Long profileId, Long taskId);

    @Query("SELECT SUM(p.points) FROM PointScore p WHERE p.profile.id = :profileId")
    Integer getTotalProfileScore(Long profileId);

    @Query("DELETE FROM PointScore p WHERE p.taskName = :taskName AND p.dateTime = (SELECT MAX(p2.dateTime) FROM PointScore p2 WHERE p2.taskName = :taskName)")
    void deleteByTaskName(String taskName);
}
