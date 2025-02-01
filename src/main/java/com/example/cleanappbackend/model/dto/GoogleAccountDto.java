package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.GoogleAccount;
import lombok.Data;

import java.util.List;

@Data
public class GoogleAccountDto {
    private String id;
    private String name;
    private String email;
    private List<ProfileDto> profiles;

    public GoogleAccountDto(GoogleAccount googleAccount) {
        this.id = googleAccount.getId();
        this.name = googleAccount.getName();
        this.email = googleAccount.getEmail();
        this.profiles = googleAccount.getProfiles().stream().map(profile -> new ProfileDto(profile)).toList();
    }
}
