package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT g FROM GoogleAccount g WHERE g.id = :id")
    GoogleAccount findGoogleAccById(String id);
}
