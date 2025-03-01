package com.example.cleanappbackend.util;

import com.example.cleanappbackend.model.PointScore;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@UtilityClass
public class PointDateFilter {

    public static List<PointScore> latestPointScore(String taskName, List<PointScore> pointScores) {

        List<PointScore> filteredList = pointScores.stream()
                .filter(pointScore -> pointScore.getTaskName().equals(taskName))
                .max(Comparator.comparing(PointScore::getDateTime))
                .map(Collections::singletonList)
                .orElse(Collections.emptyList());

        return filteredList;
    }
}
