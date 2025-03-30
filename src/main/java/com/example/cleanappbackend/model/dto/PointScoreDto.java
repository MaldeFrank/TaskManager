package com.example.cleanappbackend.model.dto;

import com.example.cleanappbackend.model.PointScore;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PointScoreDto {
    private long pointId;
    private ProfileDto profile;
    private LocalDate dateTime;
    private int points;
    private String taskName;
    private TasklistDto TasklistDto;

    public PointScoreDto(PointScore pointScore) {
        this.pointId = pointScore.getPointId();
        this.profile = new ProfileDto(pointScore.getProfile());
        this.dateTime = pointScore.getDateTime();
        this.points = pointScore.getPoints();
        this.taskName = pointScore.getTaskName();
        this.TasklistDto = new TasklistDto(pointScore.getTasklist());
    }
}
