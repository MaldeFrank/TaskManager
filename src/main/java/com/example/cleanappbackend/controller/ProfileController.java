package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.Profile;
import com.example.cleanappbackend.model.dto.ProfileDto;
import com.example.cleanappbackend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {
    @Autowired
    private ProfileRepository repository;

    @GetMapping("/profiles")
    List<ProfileDto> getTasks(){
        return repository.findAll().stream().map(profile -> new ProfileDto(profile)).toList();
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

    @PutMapping("/profile/addGoogleAcc/{id}/{googleAccountId}")
    Boolean addGoogleAccount(@PathVariable Long id, @PathVariable String googleAccountId){
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        GoogleAccount googleAccount = repository.findGoogleAccById(googleAccountId);
        if(googleAccount == null){
            return false;
        }

        profile.getGoogleAccounts().add(googleAccount);
        repository.save(profile);
        return true;
    }

    @PutMapping("/profile/addGoogleAccByEmail/{profileId}/{googleAccEmail}")
    Boolean addGoogleAccountByEmail(@PathVariable Long profileId, @PathVariable String googleAccEmail){
        Profile profile = repository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        GoogleAccount googleAccount = repository.findGoogleAccByEmail(googleAccEmail);
        if(googleAccount == null){
            return false;
        }
        profile.getGoogleAccounts().add(googleAccount);
        repository.save(profile);
        return true;
    }

    @GetMapping("/profile/getByName/{name}")
    Profile getProfileByName(@PathVariable String name){
        return repository.findProfileByName(name);
    }

}
