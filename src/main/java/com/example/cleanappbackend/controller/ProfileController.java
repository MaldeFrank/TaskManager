package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.Profile;
import com.example.cleanappbackend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    ProfileRepository repository;

    @GetMapping("/profiles")
    List<Profile> getTasks(){
        return repository.findAll();
    }

    @PostMapping("/profile")
    Profile createProfile(@RequestBody Profile profile){
        return repository.save(profile);
    }
}
