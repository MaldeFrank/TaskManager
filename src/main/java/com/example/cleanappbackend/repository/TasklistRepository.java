package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.Tasklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TasklistRepository extends JpaRepository<Tasklist, Long> {
    @Query("SELECT g FROM GoogleAccount g WHERE g.email = :email")
    GoogleAccount findGoogleAccByEmail(String email);
}
