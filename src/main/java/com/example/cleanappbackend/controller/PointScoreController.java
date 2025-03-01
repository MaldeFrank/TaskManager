package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.PointScore;
import com.example.cleanappbackend.repository.PointScoreRepository;
import com.example.cleanappbackend.util.PointDateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pointScore")
public class PointScoreController {

    @Autowired
    private PointScoreRepository pointScoreRepository;


    @GetMapping("/totalPointsByProfileId/{profileId}/{taskId}")
    public Integer GetProfileTasklistScore(@PathVariable Long profileId, @PathVariable Long taskId){
        return pointScoreRepository.GetProfileTasklistScore(profileId, taskId);
    }

    @GetMapping("/totalPointsByProfileId/{profileId}")
    public Integer getTotalProfileScore(@PathVariable Long profileId){
        return pointScoreRepository.getTotalProfileScore(profileId);
    }

    @PostMapping("/addPoints")
    public void addPoints(@RequestBody PointScore pointScore){
        pointScoreRepository.save(pointScore);
    }

    @DeleteMapping("/delete/{pointScoreId}")
    public void deletePoints(@PathVariable Long pointScoreId){
        pointScoreRepository.deleteById(pointScoreId);
    }

    @DeleteMapping("/deleteWithTaskName/{taskName}/{taskId}/{profileId}")
    public void deletePoints(@PathVariable String taskName, @PathVariable Long taskId, @PathVariable Long profileId){
        List<PointScore> pointScores = pointScoreRepository.getProfileTasklistScores(profileId, taskId);

        PointDateFilter.latestPointScore(taskName,pointScores).forEach(pointScore -> {
            pointScoreRepository.deleteById(pointScore.getPointId());
        });
    }

}
