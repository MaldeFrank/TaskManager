package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.Task;
import dto.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}