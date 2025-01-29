package com.example.cleanappbackend.model.DTO;

import com.example.cleanappbackend.model.Profile;
import lombok.Data;

@Data
public class ProfileDto {
    private long id;
    private String name;
    private long points;
    private int assignedTaskCount;

    public ProfileDto() {

    }

    public ProfileDto(long id, String name, long points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    private ProfileDto convertToProfileDto(Profile profile) {
        ProfileDto dto = new ProfileDto();
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setPoints(profile.getPoints());
        dto.setAssignedTaskCount(profile.getAssignedTasks().size());

        return dto;
    }
}



