package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.AssignedTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignedTaskRepository extends JpaRepository<AssignedTask, Long> {

}
