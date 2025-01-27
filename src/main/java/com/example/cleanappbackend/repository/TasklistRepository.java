package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.Tasklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasklistRepository extends JpaRepository<Tasklist, Long> {
}
