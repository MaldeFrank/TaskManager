package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.PointScore;
import com.example.cleanappbackend.model.Profile;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private long id;
    private String name;
    private int points;
    private int assignedTaskCount;
    private List<PointScore> pointScores;

    public ProfileDto(Profile profile) {
        profile.updateTotalPoints();
        this.setId(profile.getId());
        this.setName(profile.getName());
        this.setPoints(profile.getPoints());
        this.setAssignedTaskCount(profile.getAssignedTasks().size());
    }

}



