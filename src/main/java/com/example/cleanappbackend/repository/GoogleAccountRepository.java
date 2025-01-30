package com.example.cleanappbackend.repository;

import com.example.cleanappbackend.model.GoogleAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleAccountRepository extends JpaRepository<GoogleAccount, String> {

}
