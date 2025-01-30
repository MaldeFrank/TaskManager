package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.Profile;
import com.example.cleanappbackend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileRepository repository;

    @GetMapping("/profiles")
    List<Profile> getTasks(){
        return repository.findAll();
    }

    @PostMapping("/profile")
    Profile createProfile(@RequestBody Profile profile){
        return repository.save(profile);
    }

    @DeleteMapping("/profile/{id}")
    void deleteProfile(@PathVariable Long id){
        Profile profile = repository.getReferenceById(id);
        profile.getAssignedTasks().forEach(assignedTask ->{
            assignedTask.setAssignedTo(null);
        } );
        repository.deleteById(id);
    }
}
