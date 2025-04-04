package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.GoogleAccount;
import com.example.cleanappbackend.model.Profile;
import com.example.cleanappbackend.model.dto.ProfileDto;
import com.example.cleanappbackend.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        if(profile.getGoogleAccounts().contains(googleAccount)){
            return true;
        }
        profile.getGoogleAccounts().add(googleAccount);
        repository.save(profile);
        return true;
    }

    @GetMapping("/profile/getByName/{name}")
    ProfileDto getProfileByName(@PathVariable String name){
        Profile profile = repository.findProfileByName(name);
        return new ProfileDto(profile);
    }

    @GetMapping("/getProfileByGoogleEmail/{email}")
    ProfileDto getProfileByGoogleEmail(@PathVariable String email) {
        GoogleAccount googleAccount = repository.findGoogleAccByEmail(email);

        if (googleAccount == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Google account not found for email: " + email);
        }

        //There should only be one profile with the google acc name.
        List<ProfileDto> profileDtos =  googleAccount.getProfiles()
                .stream()
                .filter(profile -> profile.getName().equals(googleAccount.getName()))
                .map(foundProfile->new ProfileDto(foundProfile))
                .toList();


        return profileDtos.getFirst();
    }

}
