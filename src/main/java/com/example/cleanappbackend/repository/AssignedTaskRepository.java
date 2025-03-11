package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.AssignedTask;
import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignedTaskRepository extends JpaRepository<AssignedTask, Long> {
    @Query("SELECT p FROM Profile p WHERE p.id = :id")
    Profile findProfileById(Long id);

    @Query("select a FROM AssignedTask a WHERE a.assignedTo.id = :id")
    List<AssignedTask> getAssignedTaskByProfileId(Long id);
}
