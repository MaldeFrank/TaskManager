package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.GoogleAccount;
import lombok.Data;

@Data
public class GoogleAccountDto {
    private String id;
    private String name;
    private String email;

    public GoogleAccountDto(GoogleAccount googleAccount) {
        this.id = googleAccount.getId();
        this.name = googleAccount.getName();
        this.email = googleAccount.getEmail();
    }
}
