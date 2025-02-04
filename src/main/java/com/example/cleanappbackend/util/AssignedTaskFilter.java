package com.example.cleanappbackend.util;

import com.example.cleanappbackend.model.AssignedTask;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

public class AssignedTaskFilter {

    public List<AssignedTask> filterWeekly(List<AssignedTask> assignedTasks){
        LocalDate today = LocalDate.now();
        LocalDate monday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return assignedTasks.stream()
                .filter(task -> {
                    LocalDate taskDate = task.getDateTime();
                    return !taskDate.isBefore(monday) && !taskDate.isAfter(sunday);
                })
                .collect(Collectors.toList());
    }

    public List<AssignedTask> filterMonthly(List<AssignedTask> assignedTasks){
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

        return assignedTasks.stream()
                .filter(task -> {
                    LocalDate taskDate = task.getDateTime();
                    return !taskDate.isBefore(firstDayOfMonth) && !taskDate.isAfter(lastDayOfMonth);
                })
                .collect(Collectors.toList());
    }
}
