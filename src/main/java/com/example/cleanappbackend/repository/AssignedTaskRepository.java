package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.AssignedTask;
import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AssignedTaskRepository extends JpaRepository<AssignedTask, Long> {
    @Query("SELECT p FROM Profile p WHERE p.id = :id")
    Profile findProfileById(Long id);
}
