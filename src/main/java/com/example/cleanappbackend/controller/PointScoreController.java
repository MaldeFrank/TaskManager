package com.example.cleanappbackend.controller;

import com.example.cleanappbackend.model.PointScore;
import com.example.cleanappbackend.repository.PointScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
